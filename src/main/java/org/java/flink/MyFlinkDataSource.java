package org.java.flink;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

/**
 * @description: MyFlinkDataSource
 * @author: WeiQ.chen
 * @date: 2022/12/19
 */
public class MyFlinkDataSource implements SourceFunction<Integer> {
    // 设置一个线程可见的开关常量
    private volatile boolean isRunning = true;

    private Integer autoInt = 0;

    @Override
    public void run(SourceContext<Integer> ctx) throws InterruptedException {
        if (isRunning) {
            while(true) {
                Thread.sleep(2000);
                autoInt++;
                ctx.collectWithTimestamp(autoInt, 2000);
            }
        }
    }

    @Override
    public void cancel() {
        isRunning = false;
    }
}
