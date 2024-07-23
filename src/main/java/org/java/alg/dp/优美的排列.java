package org.java.alg.dp;

/**
 * @description: 优美的排列
 * @author: Weichuan.chen
 * @date: 2021/12/24
 */
public class 优美的排列 {
    /**
     * 假设有从 1 到 n 的 n 个整数。用这些整数构造一个数组 perm（下标从 1 开始），只要满足下述条件 之一 ，该数组就是一个 优美的排列 ：
     *
     * perm[i] 能够被 i 整除
     * i 能够被 perm[i] 整除
     * 给你一个整数 n ，返回可以构造的 优美排列 的 数量 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：n = 2
     * 输出：2
     * 解释：
     * 第 1 个优美的排列是 [1,2]：
     *     - perm[1] = 1 能被 i = 1 整除
     *     - perm[2] = 2 能被 i = 2 整除
     * 第 2 个优美的排列是 [2,1]:
     *     - perm[1] = 2 能被 i = 1 整除
     *     - i = 2 能被 perm[2] = 1 整除
     * 示例 2：
     *
     * 输入：n = 1
     * 输出：1
     *
     * 假如是3
     * 输出[1,2] , [2,1], [1,3], [3,1], [3,2], [1,2,3]
     *
     * 假如是4
     * 输出[1,2] , [2,1], [1,3], [3,1], [3,2], [1,2,3]
     * [1,2,3,4], [1,4], [4,2,3], [4,2], [2,4], [2,4,1]
     *
     *
     *
     * 提示：
     *
     * 1 <= n <= 15
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/beautiful-arrangement
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 思路和算法
     *
     * 由于题目保证了排列的长度 nn 至多为 1515，因此我们可以用一个位数为 nn 的二进制数 \textit{mask}mask 表示排列中的数被选取的情况。若 \textit{mask}mask 中的第 ii 位为 11（从 00 开始编号），则数 i+1i+1 已经被选取，否则就还未被选取。我们可以利用这样的二进制数表示选取数的过程的状态，以 n = 4, \textit{mask} = (0110)_2n=4,mask=(0110)
     * 2
     * ​
     *   为例，这代表数 2,32,3 都已经被选取，并以任意顺序放置在排列中前两个位置。
     *
     * 令 f[\textit{mask}]f[mask] 表示状态为 \textit{mask}mask 时的可行方案总数，这样答案即为 f[2^n - 1]f[2
     * n
     *  −1]。
     *
     * 这样我们可以得到状态间的转移方程：
     *
     * f[\textit{mask}] = \sum_{i \in \textit{mask} ~\wedge \big( i+1 \mid \textit{num}(\textit{mask}) ~\vee~ \textit{num}(\textit{mask}) \mid i+1 \big) } f[\textit{mask} - 2^i]
     * f[mask]=
     * i∈mask ∧(i+1∣num(mask) ∨ num(mask)∣i+1)
     * ∑
     * ​
     *  f[mask−2
     * i
     *  ]
     *
     * 其中 \textit{num}(\textit{mask})num(mask) 表示二进制数 \textit{mask}mask 中 11 的个数，x \mid yx∣y 表示 xx 可以整除 yy。
     *
     * 状态转移方程的含义为，当我们想要计算 f[\textit{mask}]f[mask] 时，我们只需要在前 \textit{num}(\textit{mask}) - 1num(mask)−1 位都已经放置了数的情况下，考虑第 \textit{num}(\textit{mask})num(mask) 位要放置的数即可，我们枚举当前位的符合条件的数，并将方案数累加到 f[\textit{mask}]f[mask] 中即可。
     *
     * 代码
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/beautiful-arrangement/solution/you-mei-de-pai-lie-by-leetcode-solution-vea2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int countArrangement(int n) {
        return 0;
    }

    public static void main(String[] args) {

    }
}
