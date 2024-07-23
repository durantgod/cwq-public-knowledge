package org.java.alg.offer;

/**
 * @description: 二进制加法
 * @author: Weichuan.chen
 * @date: 2022/2/19
 */
public class 二进制加法 {

    /**
     * 给定两个 01 字符串a和b，请计算它们的和，并以二进制字符串的形式输出。
     * <p>
     * 输入为 非空 字符串且只包含数字1和0。
     * <p>
     * <p>
     * <p>
     * 示例1:
     * <p>
     * 输入: a = "11", b = "10"
     * 输出: "101"
     * 示例2:
     * <p>
     * 输入: a = "1010", b = "1011"
     * 输出: "10101"
     * <p>
     * <p>
     * 提示：
     * <p>
     * 每个字符串仅由字符 '0' 或 '1' 组成。
     * 1 <= a.length, b.length <= 10^4
     * 字符串如果不是 "0" ，就都不含前导零。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/JFETK5
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public static String addBinary(String a, String b) {
        StringBuilder str = new StringBuilder();

        int aIndex = a.length();
        int bIndex = b.length();

        // 标识低位是否进位
        int flag = 0;
        int value;
        int aValue, bValue;
        while (aIndex != 0 || bIndex != 0) {
            aValue = 0;
            bValue = 0;
            if (aIndex > 0) {
                aValue = Integer.parseInt(String.valueOf(a.charAt(--aIndex)));
            }

            if (bIndex > 0) {
                bValue = Integer.parseInt(String.valueOf(b.charAt(--bIndex)));
            }

            value = aValue + bValue + flag;
            if (value == 0) {
                flag = 0;
                str.append(0);
            }

            if (value == 1) {
                flag = 0;
                str.append(1);
            }

            if (value == 2) {
                flag = 1;
                str.append(0);
            }

            if (value == 3) {
                flag = 1;
                str.append(1);
            }
        }

        if (flag == 1) {
            str.append(1);
        }
        return str.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(addBinary("111", "1"));
    }
}
