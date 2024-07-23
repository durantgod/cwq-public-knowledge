package org.java.alg.bitCount;

import com.alibaba.fastjson.JSON;

/**
 * @description: T1
 * @author: WeiQ.chen
 * @date: 2023/4/10
 */
public class T1 {
    /**
     * 输入一个无符号数，输出一个数组, 表示每个数二进制1的个数
     * 如
     * n=2
     * 输出：[0,1,1]
     *
     * @param n /
     * @return /
     */
    public static int[] countBits(int n) {
        int[] ans = new int[n + 1];
        ans[0] = 0;
        for (int i=1;i<=n;i++) {
            ans[i] = (i&1) == 0? ans[i/2] : ans[i-1] + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(countBits(5)));
    }
}
