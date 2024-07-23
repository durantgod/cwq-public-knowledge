package org.java.alg.dp;

/**
 * @description: 暴力递归
 * @author: Weichuan.chen
 * @date: 2021/12/28
 */
public class 暴力递归 {

    public static void main(String[] args) {
        //System.out.println(count(5));
        System.out.println(discount(5, 1));
    }

    public static int count(int c) {
        if (c == 0) {
            return 0;
        }
        return c + count(c - 1);
    }

    public static int discount(int c, int times) {
        System.out.println("第" + times + "次");
        if (c == 50 || times == 3) {
            return c;
        }
        return discount(c - 1, ++times);
    }

}
