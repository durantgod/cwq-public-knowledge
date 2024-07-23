package org.java.alg.dp;

import java.util.LinkedList;
import java.util.List;

/**
 * @description: 杨辉三角
 * @author: Weichuan.chen
 * @date: 2021/11/13
 */
public class 杨辉三角 {


    /**
     * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
     *
     * 在「杨辉三角」中，每个数是它左上方和右上方的数的和
     *
     * 示例 1:
     *
     * 输入: numRows = 5
     * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
     * 示例 2:
     *
     * 输入: numRows = 1
     * 输出: [[1]]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/pascals-triangle
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new LinkedList<>();
        List<Integer> row;
        // 第i行
        for (int i = 0; i < numRows; i++) {
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
            // 第Y个元素
            for (int y = 0; y <= i; y++) {
                if (y == 0 || y == i) {
                    row.add(1);
                    continue;
                }
                row.add(ans.get(i-1).get(y) + ans.get(i-1).get(y - 1));
            }
            ans.add(row);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(generate(3));
    }
}
