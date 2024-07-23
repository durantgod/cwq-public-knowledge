package org.java.alg.dp;

import java.util.LinkedList;
import java.util.List;

/**
 * @description: 杨辉三角2
 * @author: Weichuan.chen
 * @date: 2021/11/13
 */
public class 杨辉三角2 {


    /**
     *
     * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
     *
     * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
     *
     *
     *示例 1:
     *
     * 输入: rowIndex = 3
     * 输出: [1,3,3,1]
     * 示例 2:
     *
     * 输入: rowIndex = 0
     * 输出: [1]
     * 示例 3:
     *
     * 输入: rowIndex = 1
     * 输出: [1,1]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/pascals-triangle-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    /**
     * 这样虽然可以通过用例，但是不够精简，每行头尾都是1，中间的值可以通过动态规划从上一步获取，这里可以一行行来算
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> ans = new LinkedList<>();
        // 代表行
        List<Integer> row;
        for (int i = 0; i <= rowIndex; i++) {
            row = new LinkedList<>();
            if (i == 0) {
                row.add(1);
                ans.add(row);
                continue;
            }

            if (i == 1) {
                row.add(1);
                row.add(1);
                ans.add(row);
                continue;
            }

            // 代表行中第j个元素
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                    continue;
                }
                row.add(ans.get(i-1).get(j-1) + ans.get(i-1).get(j));
            }
            ans.add(row);
        }
        return ans.get(rowIndex);
    }

    public List<Integer> getRow2(int rowIndex) {
       int[][] dp = new int[rowIndex + 1][rowIndex + 1];
       if (rowIndex == 0) {
           dp[0][0] = 1;
       }

       if (rowIndex == 1) {
           dp[1][0] = 1;
           dp[1][1] = 1;
       }

        for (int i = 0; i <= rowIndex; i++) {
            if (i == 0 || i == rowIndex) {
                //dp[]
            }
        }

        return null;
    }
}
