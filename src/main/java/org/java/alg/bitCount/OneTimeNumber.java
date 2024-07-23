package org.java.alg.bitCount;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @description: OneTimeNumber
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 * @author: WeiQ.chen
 * @date: 2023/4/10
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,2,3,2]
 * 输出：3
 * 示例 2：
 * <p>
 * 输入：nums = [0,1,0,1,0,1,100]
 * 输出：100
 */
public class OneTimeNumber {
    public static int singleNumber(int[] nums) {
        Map<Integer, Integer> ans = new HashMap<>();
        for (int i : nums) {
            ans.put(i, ans.getOrDefault(i, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> ss : ans.entrySet()) {
           if (ss.getValue() == 1) {
               return ss.getKey();
           }
        }
        return 0;
    }

    public static void main(String[] args) {

    }
}
