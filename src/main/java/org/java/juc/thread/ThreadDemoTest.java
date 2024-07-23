package org.java.juc.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 主要测试线程池中懒加载线程过程，阻塞队列实现，自定义线程工厂，自定义拒绝策略
 *
 * @author WQ
 * @date 2024/7/23/023
 * @since 1.0.0
 */
@Slf4j
public class ThreadDemoTest {

    @Test
    @DisplayName("测试线程池调用过程")
    public void testThreadDemo() throws InterruptedException {
        ExecutorBuilder executor = ExecutorBuilder.builder()
                .corePoolSize(1)
                .maxPoolSize(2)
                .workQueue(new LinkedBlockingQueue<>(1))
                .threadFactory(new WQThreadFactory("测试线程组"))
                .build();

        ThreadPoolExecutor ex = executor.build();

       log.info("阻塞10s, 这个过程中，虽然线程池创建了，但是核心线程还未创建！");
        Thread.sleep(10000);
        ex.submit(() -> log.info("{}执行任务1", Thread.currentThread().getName()));

        // 延迟1s等待任务1完成
        Thread.sleep(1000);
        log.info("当有任务存在，则创建核心线程执行。");
        ex.submit(() -> {
            log.info("{}执行任务2", Thread.currentThread().getName());
            try {// 故意延迟5s,让任务3进入阻塞队列中
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        ex.submit(() -> log.info("{}执行任务3", Thread.currentThread().getName()));

        // 理论上任务队列满了，任务4理论上应该是由非核心线程执行
        ex.submit(() -> log.info("{}执行任务4", Thread.currentThread().getName()));
        Thread.sleep(5000);
    }
}
