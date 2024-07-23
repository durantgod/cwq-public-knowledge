package org.java.alg.dp;

/**
 * @description: 买卖股票的最佳时机
 * @author: Weichuan.chen
 * @date: 2021/11/13
 */
public class 买卖股票的最佳时机 {
    /**
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * <p>
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * <p>
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 示例 1：
     * <p>
     * 输入：[7,1,5,3,6,4]
     * 输出：5
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
     * 示例 2：
     * <p>
     * 输入：prices = [7,6,4,3,1]
     * 输出：0
     * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     *
     *     假如利润变化为 -5 -3 +5 + 10 —111 + 1 -100 +1000 -111
     *     假如 第i天买进，第j天卖出
     * 假如第j天卖出最高，那么第j-1天的利润肯定也是最高的
     *
     * d[i] 代表第I天的利润
     * count[i] 代表前i天最大的利润
     *
     * 所以转移方程为：
     * count[i] = count[i -1] + d[i]
     *
     * 出口？
     *  max(count[i], count[i-1])
     */


    public static int maxProfit(int[] prices) {
        // 前一天利润
        int pre = 0;
        // 第I天利润
        int max = 0;
        // 当天与前一天利润差
        for (int i = 1; i < prices.length; i++) {
            pre = Math.max(pre + prices[i] - prices[i - 1], 0);

            max = Math.max(max, pre);
        }
        return max;
    }

    // 前缀和求解(错误)
    public static int sum(int[] prices) {
        int max = 0;
        int[] count = new int[prices.length];
        count[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            count[i] = prices[i] - prices[i - 1];
        }

        for (int i = 1; i < prices.length; i++) {
            count[i] = count[i] + count[i-1];
        }

        for (int i = 0; i < prices.length; i++) {
            for (int j = 0; j < i; j++) {
                max = Math.max(max, count[i] - count[j]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] sdf = {7,1,5,3,6,4,11,66,2};
        System.out.println(sum(sdf));
        System.out.println(maxProfit(sdf));
    }
}
