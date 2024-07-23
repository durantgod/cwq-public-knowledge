package org.java.juc.future;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.runtime.clusterframework.BootstrapTools;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.function.Function;

/**
 * @description: 需要线程结果做分析，在事务要求比较高且并发较多的场景下使用completableFuture是比较合适的
 * @author: Weichuan.chen
 * @date: 2021/12/14
 *
 * runAsync 无返回值
 * supplyAsync 有返回值
 */
@Slf4j
public class CompletableFutureTest {

    /**
     * 大部分内置的线程池辅助类，用的都是LinkedBlockingQueue,
     * 优缺点是：存取是不同的锁,无边界队列，性能优于ArrayListBlockingQueue
     * 因为LinkedBlockingQueue是无边界的，所有线程池执行的时候一般显式指定队列大小，防止OOM
     *
     * 创建线程池的时候最好能自定义一下线程工厂ThreadFactory, 这样可以命名线程，线程组，日志输出时方便定位！！！
     */
    Executor wQExecutor = new ThreadPoolExecutor(1, 3, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(256));
    Executor wQExecutor2 = Executors.newSingleThreadExecutor();

    @Test
    public void listenExFromFuture() {
        CompletableFuture<String> zs = CompletableFuture.supplyAsync(() -> "张三");
        zs.thenAccept(unused -> System.out.println(unused + "完成了任务。"));
    }

    /**
     * A任务和B任务都完成的情况下才能执行C任务，A,B是并行执行线程 a & b -> c
     *
     * a,b 是and关系，completableFuture.thenCombine  taskA.thenCombine(taskB, (a,b) -> taskC)
     */
    @Test
    public void twoTaskCompleteCanDoTask() {
        CompletableFuture<String> taskA = getTask("任务A执行中....", 1000L);

        CompletableFuture<String> taskB = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务B执行中....");
            try {
                Thread.sleep(50L);
                System.out.println("任务B结束....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "B";
        });

        CompletableFuture<String> taskC = taskB.thenCombine(taskA, (a, b) -> {
            System.out.println("任务c执行中....， 接收到taskA信息为：" + a + ", 接收到taskB信息为：" + b);
            try {
                Thread.sleep(20L);
                System.out.println("任务C结束....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "C";
        });

        taskC.join();
    }

    /**
     * A任务和B任务，只要有其中一个任务完成就能执行C任务，A,B是并行执行， a || b -> c
     *
     * a,b是或的关系，taskA.applyToEither(taskB, (a) -> taskC);
     */
    @Test
    public void twoTaskOfOneCompleteCanDoTask() {
        CompletableFuture<String> taskA = getTask("任务Aa执行中....", 500L);

        CompletableFuture<String> taskB = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务Bb执行中....");
            try {
                Thread.sleep(1000L);
                System.out.println("任务B结束....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "B";
        });


        CompletableFuture<String> taskC = taskB.applyToEither(taskA, (a) -> {
            System.out.println("ddd"+a);
            System.out.println("任务Ca执行中....");
            try {
                Thread.sleep(1000L);
                System.out.println("任务c结束....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "C";
        });

        taskC.join();
    }

    /**
     * 一堆并行任务，只有全部任务完成就可以进行下一个任务
     *
     * CompletableFuture.allof(...).thenAccept(task); 注意：allof 没有返回值，因为这是多个线程执行的结果，如果要返回值没什么必要
     */
    @Test
    public void manyTaskCompleteCanDoTask() {
        CompletableFuture<String> taskA = getTask("A1", 10L);
        CompletableFuture<String> taskB = getTask("B1", 10L);
        CompletableFuture<String> taskC = getTask("C1", 2000L);
        CompletableFuture<Void> taskD = getTaskWithoutResult("D", 20L);

        CompletableFuture<Void> ss = CompletableFuture.allOf(taskA, taskB, taskC, taskD).thenAccept(v -> System.out.println("全部任务执行完毕！" + v));
        ss.join();
    }

    /**
     * 一堆并行任务，只要有其中一个完成就可以进行下一个任务
     *
     * CompletableFuture.anyof(...).thenAccept(r -> task); 注意：anyof 有返回值，最快完成的那个线程的返回值
     */
    @Test
    public void manyOfOneTaskCompleteCanDoTask() {
        CompletableFuture<String> taskA = getTask("A2", 100L);
        CompletableFuture<String> taskB = getTask("B2", 50L);
        CompletableFuture<String> taskC = getTask("C2", 200L);
        CompletableFuture<Void> taskD = getTaskWithoutResult("D2", 8000L);

        CompletableFuture<Void> ss = CompletableFuture.anyOf(taskA, taskB, taskC, taskD).thenAccept(System.out::println);
        ss.join();
    }

    /**
     * 一堆并行任务，
     *
     * ex
     */
    @Test
    public void manyOfOneTaskRaiseException() {
        CompletableFuture<String> taskA = getTask("A3", 100L);
        CompletableFuture<String> taskB = getTask("B3", 50L);
        CompletableFuture<String> taskC = getTask("C3", 200L);
        CompletableFuture<Void> taskD = getTaskWithoutResult("D3", 8000L);

        CompletableFuture<Object> ss = CompletableFuture.anyOf(taskA, taskB, taskC, taskD).exceptionally(v -> {
            System.out.println(".....");
            return null;
        });
        ss.join();
    }

    /**
     * A, B, C有序执行 D并行
     */
    @Test
    public void test3() {
        CompletableFuture<String> ca = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A");
            return "A";
        }, wQExecutor);

        CompletableFuture<String> cb = ca.thenApplyAsync(v -> {
            System.out.println("B");
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "B";
        }, wQExecutor);

        CompletableFuture<String> cc = CompletableFuture.supplyAsync(() -> {
            System.out.println("C");
            try {
                Thread.sleep(4000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "C";
        }, wQExecutor);

        CompletableFuture<String> cd = CompletableFuture.supplyAsync(() -> {
            System.out.println("D");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "D";
        }, wQExecutor);

        cb.join();
        System.out.println("dsfdsfsd");
    }


    /**
     * A, B, C有序执行 n次
     */
    @Test
    public void test4() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            CompletableFuture<String> ca = CompletableFuture.supplyAsync(() -> {
                System.out.print("A");
                try {
                    Thread.sleep(0L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "A";
            }, wQExecutor);

            CompletableFuture<String> cb = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("B");
                return "B";
            }, wQExecutor);

            CompletableFuture<String> cc = ca.thenApplyAsync(v -> {
                try {
                    Thread.sleep(0L);
                    System.out.print("C");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "C";
            }, wQExecutor);
            Thread.sleep(1000);
            System.out.println("");
            cc.join();
        }

    }


    /**
     * 测试异常处理类
     *
     * @throws InterruptedException /
     * @throws ExecutionException /
     */
    @Test
    public void testExceptionally() throws InterruptedException, ExecutionException {
        CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> {
            if (Math.random() < 0.5) {
                throw new RuntimeException("抛出异常");
            }
            System.out.println("正常结束");
            return 1.1;
        }).thenApply(result -> {
            System.out.println("thenApply接收到的参数 = " + result);
            return result;
        }).exceptionally(throwable -> {
            System.out.println("异常：" + throwable.getMessage());
            return 0.0;
        }).exceptionally(throwable -> {
            System.out.println("异常：" + throwable.getMessage());
            return 0.0;
        });
        System.out.println("最终返回的结果 = " + future.get());
    }




    private CompletableFuture<String> getTask(String s, long l) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("任务执行中...." + s);
            try {
                Thread.sleep(l);
                System.out.println("任务结束...." + s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s;
        });
    }

    private CompletableFuture<Void> getTaskWithoutResult(String s, long l) {
        return CompletableFuture.runAsync(() -> {
            System.out.println("任务执行中...." + s);
            try {
                Thread.sleep(l);
                System.out.println("任务结束111...." + s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


    /**
     * 试验结果 c1,c2并行， c3控制总体流程 下面写法可行
     */
    @Test
    public void test13() {
        CompletableFuture<?> c1 = CompletableFuture.runAsync(() ->{
            try {
                System.out.println("c1进行中");
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("c1结束");
        });

        CompletableFuture<?> c2 = CompletableFuture.runAsync(() ->{
            try {
                System.out.println("c2进行中");
                Thread.sleep(200L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("c2结束");
        });



        CompletableFuture<?> c3 = CompletableFuture.allOf(c1, c2).thenRun(() -> {
            System.out.println("c1, c2 都完成了");
        });

        c3.join();
    }


    /**
     * A 测试supplyAsync是否是同步的
     */
    @Test
    public void test32() {
        CompletableFuture<String> ca = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A");
            return "A";
        });


        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("搞完了");
    }
}
