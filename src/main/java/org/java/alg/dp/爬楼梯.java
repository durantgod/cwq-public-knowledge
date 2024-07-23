package org.java.alg.dp;

/**
 * @description: 爬楼梯
 * @author: Weichuan.chen
 * @date: 2021/11/13
 */
public class 爬楼梯 {
    public static void main(String[] args) {
        /**
         * 有n个楼梯
         * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
         *
         * 输入： 2
         * 输出： 2
         * 解释： 有两种方法可以爬到楼顶。
         * 1.  1 阶 + 1 阶
         * 2.  2 阶
         *
         * 输入： 3
         * 输出： 3
         * 解释： 有三种方法可以爬到楼顶。
         * 1.  1 阶 + 1 阶 + 1 阶
         * 2.  1 阶 + 2 阶
         * 3.  2 阶 + 1 阶
         */
        long start = System.currentTimeMillis();
        System.out.println(climbStairs22(45) + " 用时：" +(System.currentTimeMillis() - start));
        //System.out.println(climbStairs2(45) + " 用时：" +(System.currentTimeMillis() - start));
    }

    /**
     * 该方法用递归，每一步都需要算，dp思想应该是由上一步的结果得出下一步的结果
     *
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public static int climbStairs2(int n) {
       int[] dp = new int[n + 3];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i -2];
        }

       return dp[n];
     }

    public static int climbStairs22(int n) {
        int[] dp = new int[n];
        cal(dp, n);
        return dp[n];
    }

    static int cal(int[] dp, int n) {
        if (n == 1 || n == 2) {
            dp[n] = n;
            return n;
        }
        dp[n] = cal(dp, n - 1);
       return dp[n];
    }
}
