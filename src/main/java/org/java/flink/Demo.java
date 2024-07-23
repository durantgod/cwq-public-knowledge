package org.java.flink;

import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;
import java.util.List;

/**
 * @description: Demo
 * @author: WeiQ.chen
 * @date: 2022/10/27
 *
 *
 * 1.准备环境
 * 2、设置运行参数，如并行度（parallelism），运行模型(runtimeMode)
 * 3、指定数据源
 *    基于文件
 *    基于socket
 *    基于集合
 *    自定义输入
 *    系统内置提供了一批connectors
 * 4、数据处理，转换
 * 5、数据输出
 * 6、执行程序
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        // 1.准备环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 设置运行模式
        env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);

        // 设置并行度
        env.setParallelism(1);

        List<Integer> data = Arrays.asList(1,22,33);

        //3、指定数据源
        DataStreamSource<Integer> collectionData = env.fromCollection(data);

        //4、通过map数据进行处理
        DataStream<Integer> num = collectionData.map(value -> value + 1);
        num.print();

        // 5.执行程序
        env.execute();
    }
}


