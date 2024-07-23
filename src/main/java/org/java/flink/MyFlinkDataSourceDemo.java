package org.java.flink;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple1;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.junit.jupiter.api.Test;

/**
 * @description: MyFlinkDataSourceDemo
 * @author: WeiQ.chen
 * @date: 2022/12/19
 */
public class MyFlinkDataSourceDemo {

    @Test
    public void prt() throws Exception {
        // 获取任务运行环境，如果没有则在本地创建一个运行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // TODO 设置并行度.... 环境并行度和实际并行度区别是？

        // 添加自定义 datasource 数据来源
        DataStreamSource<Integer> source = env.addSource(new MyFlinkDataSource()).setParallelism(1);

        // 打印数据
        DataStream<Integer> stream = source.windowAll(TumblingProcessingTimeWindows.of(Time.seconds(4))).apply(new MyWindowFunction());
        stream.print();

        // 提交执行任务
        env.execute("my flink data source demo");
    }

    @Test
    public void testTumbling() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // receive a text-stream
        DataStreamSource<String> source = env.socketTextStream("127.0.0.1", 9999);

//        SingleOutputStreamOperator<Tuple1<String>> stream = source
//                .map((T, O) -> {
//
//                    return null;
//                })
//                .keyBy(0)
//                // 滚动事件窗口（按照process-time），如果使用event-time，即TumblingEventTimeWindows，则需要指定事件时间和watermark
//                //            .window(TumblingProcessingTimeWindows.of(Time.seconds(5)))
//                // 滑动时间窗口，窗口长度为5s，每次滑动长度为3s
//                //            .window(SlidingProcessingTimeWindows.of(Time.seconds(5), Time.seconds(3)))
//                // 会话窗口，两次处理时间相隔5秒以上，则不认为这是同一个窗口了
//                //            .window(ProcessingTimeSessionWindows.withGap(Time.seconds(5)))
//                // 简写：滚动窗口
//                //            .timeWindow(Time.seconds(5))
//                // 简写：滑动窗口
//                //            .timeWindow(Time.seconds(5), Time.seconds(3))
//                // 计数窗口：滚动
//                //            .countWindow(10)
//                // 计数窗口：滑动
//                .countWindow(10, 5)
//                .sum(1);

//        stream.print();

        env.execute("window demo");
    }
}
