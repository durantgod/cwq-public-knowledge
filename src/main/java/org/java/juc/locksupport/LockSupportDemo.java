package org.java.juc.locksupport;

import org.java.juc.thread.WQThreadFactory;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

/**
 * 线程间通讯，长时间park，对性能是否有影响？
 * 老方式实现线程的同步并不明智，考虑用CompletableFuture
 *
 *
 * @description: LockSupportDemo
 * @author: WeiQ.chen
 * @date: 2022/11/15
 */
public class LockSupportDemo {
    ThreadFactory factory = new WQThreadFactory("WQ factory", "lockSupport group");
    ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 10, 1,
            TimeUnit.SECONDS, new LinkedBlockingDeque<>(), factory);

    @Test
    public void s2() {

    }

    @Test
    public void s3() {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "业务执行中.....");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "业务执行需要等待 t2 执行....");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "业务执行完成.....");
        }, "lockSupport t1");


        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "开始业务....");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "结束业务....");
            LockSupport.unpark(t1);
        }, "lockSupport t2");


        try {
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * a -> b -> c
     */
    @Test
    public void s4() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Thread tc = new Thread(() -> {
                LockSupport.park();
                System.out.println(Thread.currentThread().getName() + "业务执行中.....");
            }, "c");


            Thread tb = new Thread(() -> {
                LockSupport.park();
                System.out.println(Thread.currentThread().getName() + "业务执行中.....");
                LockSupport.unpark(tc);
            }, "b");

            Thread ta = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "业务执行中.....");
                LockSupport.unpark(tb);
            }, "a");

            try {
                ta.start();
                tb.start();
                tc.start();
                ta.join();
                tb.join();
                tc.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
            Thread.sleep(1000);
        }
    }

    /**
     * 失败的例子，异步线程和同步线程的关系理解不对
     */
    @Test
    public void s1() {
        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1 业务执行中.....");
            }
        }, "lockSupport t1");

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.park(t1);

        // 让另外一辆车通过后再让他走
        Thread t2= new Thread(() -> {
            System.out.println("t2 开始业务....");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 停车
            LockSupport.park(t1);
            System.out.println("t2 业务执行，t1 先停止业务等待 t2 完成业务....");


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2 结束业务....");
            LockSupport.unpark(t1);
        }, "lockSupport t2");
        t2.start();

        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
