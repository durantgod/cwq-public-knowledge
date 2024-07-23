package org.java.jvm;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * desc
 *
 * @author WQ
 * @date 2024/7/6/006
 * @since 1.0.0
 */

@DisplayName("守护线程和非守护线程，一般守护线程指JVM创建的线程，如GC")
public class JvmDaemonThread {

    @DisplayName("主线程执行完毕，如果是非守护线程还未结束，jvm会等待")
    public static void main2(String[] args) {
        new Thread(() -> {
            System.out.println("1执行任务前。。。。");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("1执行任务后。。。。。");

        }).start();

        System.out.println("1主线程退出！！！");
    }

    @DisplayName("主线程执行完毕，如果是守护线程还未结束，jvm会直接退出")
    public static void main(String[] args) {
        Thread xx = new Thread(() -> {
            System.out.println("2执行任务前。。。。");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("2执行任务后。。。。。");

        });
        xx.setDaemon(true);
        xx.start();
        System.out.println("2主线程退出！！！");
    }
}
