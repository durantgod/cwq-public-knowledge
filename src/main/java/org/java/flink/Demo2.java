package org.java.flink;

import lombok.Getter;
import lombok.Setter;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @description: Demo2
 * @author: WeiQ.chen
 * @date: 2022/10/27
 */
public class Demo2 {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.BATCH);
        env.setParallelism(1);
        long start = System.currentTimeMillis();
        DataStreamSource<String> source = env.readTextFile("C:\\Users\\cesh1\\Desktop\\gitee\\wq-alg\\src\\main\\resources\\flink.txt");

        SingleOutputStreamOperator<Tuple2<String, Integer>> wordAndOne = source
                .flatMap((FlatMapFunction<String, Tuple2<String, Integer>>) (value, out) -> {
                    String[] split = value.split(" ");
                    if (split.length > 0) {
                        Stream<String> stream = Arrays.stream(split);
                        stream.forEach(word -> out.collect(Tuple2.of(word, 1)));
                    }
                }).returns(Types.TUPLE(Types.STRING, Types.INT));

        wordAndOne.print();
        SingleOutputStreamOperator<Tuple2<String, Integer>> sum = wordAndOne
                .keyBy(f -> f.f0)
                .sum(1);

        sum.print();
        env.execute();
        System.out.println("耗時:" + (System.currentTimeMillis() - start));
    }

    @Test
    @DisplayName("从kafka取得的JSON被反序列化成Student实例，统计每个name的数量，窗口是5秒")
    public void test1() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.BATCH);

        List<Student> stubs = new ArrayList<>();
        stubs.add(new Student("Jonny", 15));
        stubs.add(new Student("Jonny", 33));
        stubs.add(new Student("Tom", 16));
        stubs.add(new Student("Amy", 17));

        DataStream<Student> dataStreamSource = env.fromCollection(stubs);

        dataStreamSource
                .map((MapFunction<Student, Tuple2<String, Integer>>) value -> new Tuple2<>(value.name, 1))
                .returns(Types.TUPLE(Types.STRING, Types.INT)).keyBy("f0").sum(1)
                .print();

        env.execute("Connector DataSource demo : kafka bean");
    }


    @Getter
    @Setter
    static
    class Student {
        String name;

        Integer age;

        public Student(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
    }
}
