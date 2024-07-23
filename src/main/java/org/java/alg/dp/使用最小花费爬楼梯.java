package org.java.alg.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @description: 使用最小花费爬楼梯
 * @author: Weichuan.chen
 * @date: 2021/12/25
 */
public class 使用最小花费爬楼梯 {

    /**
     * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
     *
     * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
     *
     * 请你计算并返回达到楼梯顶部的最低花费。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：cost = [10,15,20]
     * 输出：15
     * 解释：你将从下标为 1 的台阶开始。
     * - 支付 15 ，向上爬两个台阶，到达楼梯顶部。
     * 总花费为 15 。
     * 示例 2：
     *
     * 输入：cost = [1,100,1,1,1,100,1,1,100,1]
     * 输出：6
     * 解释：你将从下标为 0 的台阶开始。
     * - 支付 1 ，向上爬两个台阶，到达下标为 2 的台阶。
     * - 支付 1 ，向上爬两个台阶，到达下标为 4 的台阶。
     * - 支付 1 ，向上爬两个台阶，到达下标为 6 的台阶。
     * - 支付 1 ，向上爬一个台阶，到达下标为 7 的台阶。
     * - 支付 1 ，向上爬两个台阶，到达下标为 9 的台阶。
     * - 支付 1 ，向上爬一个台阶，到达楼梯顶部。
     * 总花费为 6 。
     *  
     *
     * 提示：
     *
     * 2 <= cost.length <= 1000
     * 0 <= cost[i] <= 999
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/min-cost-climbing-stairs
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public int minCostClimbingStairs(int[] cost) {
        // 有N个台阶
        int size = cost.length;
        return 0;
    }


    public static void main(String[] args) {
        // 1,100,1,1,1,100,1,1,100,1
        /**
         * [4,5,6,0,0,0]
         * 3
         * [1,2,3]
         * 3
         */
        //System.out.println(maxSubArray(new int[]{10,15,20,-999, 1, 1000}));

        merge2(new int[]{4,5,6,0,0,0}, 3, new int[]{1,2,3}, 3);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int temp;
        if (m == 0) {
            nums1 = nums2;
            for (int i = 0; i != m + n; ++i) {
                nums1[i] = nums2[i];
            }
        } else if (n != 0) {
            for (int i = 0, j = 0; i + j < m + n + 2; ) {
                if (i < m && nums1[i] <= nums2[j]) {
                    i++;
                } else if (i < m) {
                    temp = nums1[i];
                    nums1[i] = nums2[j];
                    nums2[j] = temp;
                    i++;
                } else {
                    nums1[i] = nums2[j];
                    i++;
                    j++;
                }
            }
        }
        System.out.println(Arrays.toString(nums1));
    }

    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        int[] sum = new int[nums1.length];
        // 标识两个指针，一个指向nums1头，p2指向nums2
        int p1 = 0, p2 = 0;
        int index = 0;

        // 标识是否需要转换
        boolean flag = false;
        while (p1 + p2 < m + n) {
            // 主要要考虑边界问题，比如 m 或 n 都可能为0
            if (m == 0) {
                flag = true;
                for (; p2 < n; p2++) {
                    nums1[index] = nums2[p2];
                    index++;
                }
            } else if (n == 0) {
                flag = true;
            } else if(p1 < m || p2 < n){
                // p1还没遍历完nums1
                // p1 指向的值小于 p2指向的值，这种情况取p1的值
                if (p2 < n && nums1[p1] <= nums2[p2]) {
                    sum[index] = nums1[p1];
                    p1++;
                } else {
                    sum[index] = nums2[p2];
                    p2++;
                }
                index++;
            }
        }
        if (!flag) {
            for (int i = 0; i < sum.length; i++) {
                nums1[i] = sum[i];
            }
        }
        System.out.println(Arrays.toString(nums1));
    }



    public static boolean containsDuplicate(int[] nums) {
        int size = nums.length;
        Set<Integer> ss = new HashSet<>(size);
        for (int i = 0; i < size; i++) {
            System.out.println(ss.add(nums[i]));
        }
        return false;
    }

    static int ddd(int[] cost) {
        //     * 输入：cost = [10,15,20,8]
        //     * 输出：15
        //     * 解释：你将从下标为 1 的台阶开始。
        //     * - 支付 15 ，向上爬两个台阶，到达楼梯顶部。
        //     * 总花费为 15 。
        int size = cost.length;
        int[] count = new int[size + 1];
        if (cost.length == 0) {
            return 0;
        }

        if (cost.length == 1) {
            return cost[0];
        }

        for (int i = 2; i <= size; i++) {
            count[i] = Math.min(count[i - 1] + cost[i - 1], count[i - 2] + cost[i -2]);
        }

        return count[size];
    }

    public static int maxSubArray(int[] nums) {
        int preMax = Integer.MIN_VALUE;
        int currentMax = 0;
        for (int num : nums) {
            currentMax = Math.max(currentMax + num, num);
            preMax = Math.max(currentMax, preMax);
        }
        return preMax;
    }

    public static int divide(int a, int b) {
        // 15 5  》》 4 8   =========  15 >>2 = 3,4   15>>3 = 1,2
        // 求两个数的商，不能使用*%/, 考虑位运算
        boolean isPositive = b > 0;
        boolean flag = (isPositive && a > 0) || (!isPositive && a < 0);
        b = isPositive ? b : -b;
        for (;b != 1; b = b >> 1) {
            a = a >> 1;
        }
        return flag ? a : -a;
    }

    public static int[] twoSum(int[] nums, int target) {
        int i = 0,j = 0;
        for (int num : nums) {
            if (num > target) {
                continue;
            }
            for(int num2 : nums) {
                if (num2 > target) {
                    continue;
                }
                if (num2 + num == target) {
                    return new int[]{i,j};
                }
                j++;
            }
            i++;
        }

        return new int[]{i,j};
    }
}
