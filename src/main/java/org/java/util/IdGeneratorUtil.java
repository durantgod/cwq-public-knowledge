package org.java.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * ID生成工具类
 * 该类提供了一种生成唯一ID的方法，ID由时间戳、用户ID和序列号三部分组成
 * 设计用于高并发环境下，生成全局唯一ID
 *
 * @author Durant
 * @date 2024/9/9/周一
 * @since 1.0.0
 */
public class IdGeneratorUtil {
    // 时间戳左移位数
    private static final long TIMESTAMP_LEFT_SHIFT = 22;
    // 用户ID左移位数
    private static final long USER_ID_LEFT_SHIFT = 32;
    // 序列号掩码，用于确保序列号不超过12位
    private static final long SEQUENCE_MASK = ~(-1L << 12); // 12 bits max sequence

    // 用户ID，使用AtomicLong保证线程安全
    private final AtomicLong userId = new AtomicLong(0); // 假设用户ID是从0开始递增
    // 序列号，使用AtomicLong保证线程安全
    private final AtomicLong sequence = new AtomicLong(0);
    // 上次生成ID的时间戳
    private volatile long lastTimestamp = -1L;

    /**
     * 生成订单ID
     * ID由时间戳、用户ID和序列号三部分组成
     *
     * @param currentTimestamp 当前时间戳
     * @return 生成的订单ID
     */
    public synchronized long generateOrderId(long currentTimestamp) {
        // 如果当前时间戳小于上次生成ID的时间戳，抛出异常
        if (currentTimestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id");
        }

        // 如果当前时间戳等于上次生成ID的时间戳，说明在同一毫秒内，序列号+1
        if (lastTimestamp == currentTimestamp) {
            long sequenceId = sequence.getAndIncrement();
            // 如果序列号溢出，等待下一毫秒
            if ((sequenceId & SEQUENCE_MASK) == 0L) {
                currentTimestamp = getNextMill();
            }
        } else {
            // 如果是新的毫秒，重置序列号
            sequence.set(0);
        }

        // 更新上次生成ID的时间戳
        lastTimestamp = currentTimestamp;

        // 用户ID左移USER_ID_LEFT_SHIFT位
        long userIdShifted = userId.get() << USER_ID_LEFT_SHIFT;
        // 序列号与SEQUENCE_MASK进行与操作，确保序列号不超过12位
        long sequenceShifted = sequence.get() & SEQUENCE_MASK;

        // 拼接时间戳、用户ID和序列号生成ID
        return (currentTimestamp << TIMESTAMP_LEFT_SHIFT) | userIdShifted | sequenceShifted;
    }

    /**
     * 获取下一个毫秒的时间戳
     *
     * @return 下一个毫秒的时间戳
     */
    private long getNextMill() {
        long mill = System.currentTimeMillis();
        // 如果时间戳还未超过上次的时间戳，继续等待
        while (mill <= lastTimestamp) {
            mill = System.currentTimeMillis();
        }
        return mill;
    }

    /**
     * 主函数，用于测试生成订单ID
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        IdGeneratorUtil generator = new IdGeneratorUtil();

        // 模拟生成订单ID
        for (int i = 0; i < 10; i++) {
            long orderId = generator.generateOrderId(System.currentTimeMillis());
            System.out.println("Generated Order ID: " + orderId);
        }
    }
}
