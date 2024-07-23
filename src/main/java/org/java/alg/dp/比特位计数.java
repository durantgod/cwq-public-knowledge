package org.java.alg.dp;

import java.util.Arrays;

/**
 * @description: 比特位计数
 * @author: Weichuan.chen
 * @date: 2021/11/16
 */
public class 比特位计数 {

    /**
     *
     *给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
     *
     * 输入：n = 2
     * 输出：[0,1,1]
     * 解释：
     * 0 --> 0
     * 1 --> 1
     * 2 --> 10
     *
     *
     * 示例 2：
     *
     * 输入：n = 5
     * 输出：[0,1,1,2,1,2]
     * 解释：
     * 0 --> 0
     * 1 --> 1
     * 2 --> 10
     * 3 --> 11
     * 4 --> 100
     * 5 --> 101
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/counting-bits
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * 0 <= n <= 105
     */

    /**
     * 动态规划解题  ============
     *
     * @param n
     * @return
     */
    public static int[] countBits(int n) {
        int[] ans = new int[n + 1];
        if (n == 0) {
            ans[n] = 0;
            return ans;
        }
        if (n == 1) {
            ans[n] = 1;
            return ans;
        }
        ans[0] = 0;
        ans[1] = 1;

        int y;
        int c;
        for (int i = 2; i <= n; i++) {
            c = i / 2;
            y = i % 2;
            // 能整除2，相当于i >> 1
            if (y == 0) {
                ans[i] = ans[c];
            } else {
                ans[i] = ans[c] + ans[y];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(countBits(5)));
    }
}
