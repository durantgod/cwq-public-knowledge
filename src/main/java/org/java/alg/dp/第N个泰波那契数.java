package org.java.alg.dp;

/**
 * @description: 第N个泰波那契数
 * @author: Weichuan.chen
 * @date: 2021/12/24
 */
public class 第N个泰波那契数 {
    /**
     *
     *泰波那契序列Tn定义如下：
     *
     * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0的条件下 Tn+3 = Tn + Tn+1 + Tn+2
     *
     * 给你整数n，请返回第 n 个泰波那契数Tn 的值。
     *
     *
     *
     * 示例 1：
     *
     * 输入：n = 4
     * 输出：4
     * 解释：
     * T_3 = 0 + 1 + 1 = 2
     * T_4 = 1 + 1 + 2 = 4
     * 示例 2：
     *
     * 输入：n = 25
     * 输出：1389537
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/n-th-tribonacci-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     */

    public static int tribonacci(int n) {
        int t0 = 0;
        int t1 = 1;
        int t2 = 1;
        if (n == 0) {
            return t0;
        }
        if (n == 1) {
            return t1;
        }
        if (n == 2) {
            return t2;
        }

        int ans  = 0;
        for (int i = 3; i <= n; i++) {
            ans = t0 + t1 + t2;
            t0 = t1;
            t1 = t2;
            t2 = ans;
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(tribonacci(25));
    }

}
