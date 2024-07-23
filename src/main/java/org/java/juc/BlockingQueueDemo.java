package org.java.juc;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @description: BlockingQueueDemo
 * @author: WeiQ.chen
 * @date: 2023/3/28
 */
public class BlockingQueueDemo {
    public static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(10);

    @Test
    public void test1() {
        for (int i = 0; i < 20; i++) {
            queue.add(String.valueOf(i));
        }
    }

    @Test
    public void test2() {
        int ans = "0A7B08AA00015D099F628D5C029A5B68".hashCode();
        int ans2 = "0A7B08AA00015D099F628D5C02F85BF6".hashCode();
        int ans3 = "0A7B08AA00015D099F628D5C029A5B68".hashCode();
        int ans4 = "0A7B08AA00015D099F628D5C02225AC8".hashCode();
        System.out.println(ans);
        System.out.println(ans%1000000000);
        System.out.println(ans2);
        System.out.println(ans2%1000000000);
        System.out.println(ans3);
        System.out.println(ans3%1000000000);
        System.out.println(ans4);
        System.out.println(ans4%1000000000);
    }

    @Test
    public void test3() {
        List<Integer> list = Arrays.asList(1,2,3,4,4,3,1);

        int ans = 0;
        for (Integer i : list) {
          ans = ans ^ i;
        }

        System.out.println("不重复的数为："+ans);
    }

}
