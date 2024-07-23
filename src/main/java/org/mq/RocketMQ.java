package org.mq;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;

/**
 * RocketMQ demo
 *
 * @author WQ
 * @date 2024/5/25/025
 * @since 1.0.0
 */

@RocketMQMessageListener(nameServer = "", consumerGroup = "", topic = "", consumeMode = ConsumeMode.ORDERLY)
public class RocketMQ {
    // consumeMode 是指消费模式，顺序消费和并发消费。顺序消费是指单个线程去消费数据，这样能保证顺序，如果是并发消费，那么多个线程同时消费
    // 同一个queue下的消息，这样是没有办法保证顺序的。除非最大线程数和最少线程数设置为1
}
