package org.java.alg.offer;

import com.alibaba.fastjson.JSON;

/**
 * @description: TwoNumCount
 *
 * 给定一个已按照 升序排列 的整数数组numbers ，请你从数组中找出两个数满足相加之和等于目标数target 。
 *
 * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 0开始计数 ，所以答案数组应当满足 0<= answer[0] < answer[1] <numbers.length。
 *
 * 假设数组中存在且只存在一对符合条件的数字，同时一个数字不能使用两次。
 *
 *
 *
 * 示例 1：
 *
 * 输入：numbers = [1,2,4,6,10], target = 8
 * 输出：[1,3]
 * 解释：2 与 6 之和等于目标数 8 。因此 index1 = 1, index2 = 3 。
 * 示例 2：
 *
 * 输入：numbers = [2,3,4], target = 6
 * 输出：[0,2]
 * 示例 3：
 *
 * 输入：numbers = [-1,0], target = -1
 * 输出：[0,1]
 *
 *
 * 提示：
 *
 * 2 <= numbers.length <= 3 * 104
 * -1000 <= numbers[i] <= 1000
 * numbers 按 非递减顺序 排列
 * -1000 <= target <= 1000
 * 仅存在一个有效答案
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/kLl5u1
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: WeiQ.chen
 * @date: 2023/4/12
 */
public class TwoNumCount {

    public static int[] twoSum(int[] numbers, int target) {
        int head = 0;
        int tail = numbers.length - 1;

        while (head < tail) {
            if(numbers[head] + numbers[tail] > target) {
                // 如果头加尾大于目标值，尾指针往前挪
                tail--;
            } else if(numbers[head] + numbers[tail] < target) {
                // 头加尾小于目标值，头尾指针都可以挪
                int temp = head;
                int tempTail = numbers[tail];

                // 头指针往后挪
                while (head < tail) {
                    head++;
                    if (numbers[head] + tempTail == target) {
                       return new int[]{head, tail};
                    }
                }

                head = temp;
                int tempHead = numbers[head];
                // 尾指针往后前挪
                while (head < tail) {
                    tail--;
                    if (numbers[tail] + tempHead == target) {
                        return new int[]{head, tail};
                    }
                }
            } else {
                break;
            }
        }

        return new int[]{head, tail};
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(twoSum(new int[]{12,13,23,28,43,44,59,60,61,68,70,86,88,92,124,125,136,168,173,173,180,199,212,221,227,230,277,282,306,314,316,321,325,328,336,337,363,365,368,370,370,371,375,384,387,394,400,404,414,422,422,427,430,435,457,493,506,527,531,538,541,546,568,583,585,587,650,652,677,691,730,737,740,751,755,764,778,783,785,789,794,803,809,815,847,858,863,863,874,887,896,916,920,926,927,930,933,957,981,997}, 542)));
    }


}
