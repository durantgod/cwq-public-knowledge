package org.java.alg.dp;

/**
 * @description: 判断子序列
 * @author: Weichuan.chen
 * @date: 2021/11/17
 */
public class 判断子序列 {
    /*
给定字符串 s 和 t ，判断 s 是否为 t 的子序列。

字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。

进阶：

如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？


 

示例 1：

输入：s = "abc", t = "ahbgdc"
输出：true
示例 2：

输入：s = "axc", t = "ahbgdc"
输出：false
 

提示：

0 <= s.length <= 100
0 <= t.length <= 10^4
两个字符串都只由小写字符组成。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/is-subsequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

    public static boolean isSubsequence(String s, String t) {
        // t 父序列
        char[] s1 = t.toCharArray();

        // s 子序列
        char[] s2 = s.toCharArray();
        int s1Length = s1.length;
        int s2Length = s2.length;

        if (s1Length < s2Length) {
            return false;
        }

        if (s2Length == 0) {
            return true;
        }

        if (s1Length == 0) {
            return false;
        }

        // s2头部指针
        int ct = 0;
        // s2尾部指针
        int ce = s2Length;
        for (int i = 0; i < s1Length; i++) {
            if (s2[ct] == s1[i]) {
                ct++;
            }

            if (s2[ce - 1] == s1[s1Length - i - 1]) {
                ce--;
            }

            if (ct > ce) {
                return true;
            }
            if (s2Length == 1 && ct == 1) {
                return true;
            }
        }

        return false;
    }
    public static void main(String[] args) {
        System.out.println(isSubsequence3("abc", "ahbgdc"));
    }


    public static boolean isSubsequence2(String s, String t) {
        if (s.length() > t.length()) {
            return false;
        }

        // t 父序列
        char[] s1 = t.toCharArray();

        // s 子序列
        char[] s2 = s.toCharArray();
        int j = 0;
        for (int i = 0; i < s1.length && j < s2.length; i++) {
          if (s2[j] == s1[i]) {
              j++;
          }
        }
        return j == s2.length;
    }

    public static boolean isSubsequence3(String s, String t) {
        if (s.length() > t.length()) {
            return false;
        }
        int j = 0;
        for (int i = 0; i < t.length() && j < s.length(); i++) {
            if (t.charAt(i) == s.charAt(j)) {
                j++;
            }
        }
        return j == s.length();
    }
}
