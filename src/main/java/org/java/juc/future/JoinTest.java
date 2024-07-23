package org.java.juc.future;

/**
 * @description: JoinTest
 * @author: WeiQ.chen
 * @date: 2022/7/18
 */
public class JoinTest {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            extracted();
            System.out.println();
            Thread.sleep(200L);
        }
    }

    private static void extracted() {
        Thread tc = new Thread(() -> {
            System.out.print("C");
        });

        Thread tb = new Thread(() -> {
            try {
                tc.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("B");
        });

        Thread ta = new Thread(() -> {
            try {
                tb.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("A");
        });
        tc.start();
        tb.start();
        ta.start();
    }
}
