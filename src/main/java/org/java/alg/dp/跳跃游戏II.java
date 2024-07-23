package org.java.alg.dp;

/**
 * @description: 跳跃游戏II
 * @author: Weichuan.chen
 * @date: 2021/11/25
 */
public class 跳跃游戏II {

    /**
     * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
     *
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     *
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     *
     * 假设你总是可以到达数组的最后一个位置。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: nums = [2,3,1,1,4]
     * 输出: 2
     * 解释: 跳到最后一个位置的最小跳跃数是 2。
     *     从下标为 0 跳到下标为 1 的位置，跳1步，然后跳3步到达数组的最后一个位置。
     * 示例 2:
     *
     * 输入: nums = [2,3,0,1,4]
     * 输出: 2
     *
     *
     * 提示:
     *
     * 1 <= nums.length <= 104
     * 0 <= nums[i] <= 1000
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/jump-game-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     */

    public static int jump(int[] nums) {
        // 定义边界
        int end = 0;

        // 每次跳跃能跳到的最远的距离
        int max = 0;

        // 记录走的步数
        int step = 0;

        // 因为题目中明确了，肯定有最少的跳跃次数能达到最后一个位置。
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, i + nums[i]);

            if (i == end) {
                end = max;
                step++;
            }
        }
        return step;
    }

    public static void main(String[] args) {

    }
}
