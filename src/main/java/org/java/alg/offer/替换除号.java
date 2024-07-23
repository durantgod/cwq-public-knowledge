package org.java.alg.offer;

/**
 * @description: 替换除号
 * @author: Weichuan.chen
 * @date: 2022/2/14
 */
public class 替换除号 {


    public static int divide(int a, int b) {
        int flag = 0;
        if (a > 0) {
            a = -a;
            flag += 1;
        }

        if (b > 0) {
            b = -b;
            flag += 1;
        }
        int ret = calc(a, b);
        if (flag != 1 && ret == Integer.MIN_VALUE) {
            ret++;
        }
        return flag == 1 ? ret : -ret;
    }

    private static int calc(int a, int b) {
        int ret = 0;
        while (a <= b) {
            int cnt = 1;
            int val = b;
            while (val >= Integer.MIN_VALUE >> 1 && a <= val << 1) {
                cnt += cnt;
                val += val;
            }
            ret -= cnt;
            a -= val;
        }
        return ret;
    }

    /**
     *  时间复杂度：O(logn * logn)，n 是最大值 2147483647 --> 10^10
     *
     * @param a
     * @param b
     * @return
     */
    public static int divide2(int a, int b) {
        if (a == Integer.MIN_VALUE && b == -1)
            return Integer.MAX_VALUE;
        int sign = (a > 0) ^ (b > 0) ? -1 : 1;
        if (a > 0) a = -a;
        if (b > 0) b = -b;
        int res = 0;
        while (a <= b) {
            int value = b;
            int k = 1;
            // 0xc0000000 是十进制 -2^30 的十六进制的表示
            // 判断 value >= 0xc0000000 的原因：保证 value + value 不会溢出
            // 可以这样判断的原因是：0xc0000000 是最小值 -2^31 的一半，
            // 而 a 的值不可能比 -2^31 还要小，所以 value 不可能比 0xc0000000 小
            // -2^31 / 2 = -2^30
            while (value >= 0xc0000000 && a <= value + value) {
                value += value;
                // 代码优化：如果 k 已经大于最大值的一半的话，那么直接返回最小值
                // 因为这个时候 k += k 的话肯定会大于等于 2147483648 ，这个超过了题目给的范围
                if (k > Integer.MAX_VALUE / 2) {
                    return Integer.MIN_VALUE;
                }
                k += k;
            }
            a -= value;
            res += k;
        }
        // bug 修复：因为不能使用乘号，所以将乘号换成三目运算符
        return sign == 1 ? res : -res;
    }

    public static void main(String[] args) {
        System.out.println(divide2(1000,3));
    }
}
