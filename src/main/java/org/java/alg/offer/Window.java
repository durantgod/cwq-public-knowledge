package org.java.alg.offer;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Window
 * @author: WeiQ.chen
 * @date: 2023/4/6
 */
public class Window {
    /**
     * 给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算滑动窗口里所有数字的平均值。
     * <p>
     * 实现 MovingAverage 类：
     * <p>
     * MovingAverage(int size) 用窗口大小 size 初始化对象。
     * double next(int val)成员函数 next每次调用的时候都会往滑动窗口增加一个整数，请计算并返回数据流中最后 size 个值的移动平均值，即滑动窗口里所有数字的平均值。
     * <p>
     * <p>
     * 示例：
     * <p>
     * 输入：
     * inputs = ["MovingAverage", "next", "next", "next", "next"]
     * inputs = [[3], [1], [10], [3], [5]]
     * 输出：
     * [null, 1.0, 5.5, 4.66667, 6.0]
     * <p>
     * 解释：
     * MovingAverage movingAverage = new MovingAverage(3);
     * movingAverage.next(1); // 返回 1.0 = 1 / 1
     * movingAverage.next(10); // 返回 5.5 = (1 + 10) / 2
     * movingAverage.next(3); // 返回 4.66667 = (1 + 10 + 3) / 3
     * movingAverage.next(5); // 返回 6.0 = (10 + 3 + 5) / 3
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= size <= 1000
     * -105 <= val <= 105
     * 最多调用 next 方法 104 次
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/qIsx9U
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
}

/**
 * 窗口用列表来存储，统计用stream sum统计速度慢
 */
class MovingAverage {
    // 临时滑动窗口
    List<Integer> temWindow;

    // 窗口大小
    int size;

    /**
     * Initialize your data structure here.
     */
    public MovingAverage(int size) {
        temWindow = new ArrayList<>();
        this.size = size;
    }

    public double next(int val) {
        temWindow.add(val);
        // 如果还没达到最大窗口
        if (temWindow.size() > size) {
            temWindow.remove(0);
        }
        return (double) temWindow.stream().mapToInt(Integer::intValue).sum() / temWindow.size();
    }
}

/**
 * 窗口用数组来存储
 */
class MovingAverage1 {
    // 临时滑动窗口
    int[] temWindow;

    // 窗口大小
    int size;

    /**
     * Initialize your data structure here.
     */
    public MovingAverage1(int size) {
        temWindow = new int[size + 1];
        this.size = size;
    }

    public double next(int val) {
        final int currSize = temWindow.length;

        return 1d;
    }
}

class MovingAverage2 {

    private int sum;
    private int elementSize;
    private int[] temp;

    public MovingAverage2(int size) {
        this.temp = new int[size];
    }

    public double next(int val) {
        elementSize++;
        sum += val;
        int pos = (elementSize - 1) % temp.length;
        if (elementSize > temp.length) {
            sum -= temp[pos];
        }
        temp[pos] = val;
        return (double) sum / (Math.min(elementSize, temp.length));

    }
}
