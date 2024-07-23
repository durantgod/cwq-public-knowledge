package org.java.juc.thread;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 主要测试线程池中懒加载线程过程，阻塞队列实现，自定义线程工厂，自定义拒绝策略
 *
 * @author WQ
 * @date 2024/7/23/023
 * @since 1.0.0
 */
public class ThreadDemoTest {

    @Test
    @DisplayName("测试线程池调用过程")
    public void testThreadDemo() throws InterruptedException {
        ExecutorBuilder executor = ExecutorBuilder.builder()
                .corePoolSize(1)
                .maxPoolSize(2)
                .workQueue(new LinkedBlockingQueue<>(2))
                .threadFactory(new WQThreadFactory("测试线程组"))
                .build();

        ThreadPoolExecutor ex = executor.build();
        ex.submit(() -> System.out.println("执行任务"));

        Thread.sleep(20000);
    }
}
