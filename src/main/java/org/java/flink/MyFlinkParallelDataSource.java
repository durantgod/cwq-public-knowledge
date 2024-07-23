package org.java.flink;

import org.apache.flink.streaming.api.functions.source.ParallelSourceFunction;

/**
 * @description: MyFlinkParallelDataSource
 * @author: WeiQ.chen
 * @date: 2022/12/19
 */
public class MyFlinkParallelDataSource implements ParallelSourceFunction<Integer> {


    @Override
    public void run(SourceContext<Integer> ctx) {

    }

    @Override
    public void cancel() {

    }
}
