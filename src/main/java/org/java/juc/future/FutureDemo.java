package org.java.juc.future;

import java.util.concurrent.*;

/**
 * @description: FutureDemo
 * @author: WeiQ.chen
 * @date: 2022/9/27
 */
public class FutureDemo {
   public static ExecutorService executor = new ThreadPoolExecutor(1,2,50L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

    public static Future<?> ex(Runnable r) {
        return executor.submit(r);
    }

    public static void main(String[] args) throws InterruptedException {
        if(extracted()) {
            System.out.println("sdffffffffffffffff");
        }

    }

    private static boolean extracted() throws InterruptedException {
        Future<?> ss =  ex(() -> {
            System.out.println("制定中");
            try {
                Thread.sleep(2000L);
                throw new RuntimeException();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("借宿了");
        });
        while (true) {
            if (ss.isDone()) {
                System.out.println("完成了！！！");
                return true;
            }
            System.out.println("等待中....");
            Thread.sleep(200L);
        }
    }


}
