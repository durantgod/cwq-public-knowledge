package org.java.flink;

import org.apache.flink.streaming.api.functions.windowing.AllWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.Window;
import org.apache.flink.util.Collector;

/**
 * @description: MyWindowFunction
 * @author: WeiQ.chen
 * @date: 2022/12/20
 */
public class MyWindowFunction<W extends Window, T> implements AllWindowFunction<T, T, W> {
    @Override
    public void apply(W window, Iterable<T> values, Collector<T> out) throws Exception {
        for (T in : values) {
            out.collect(in);
        }
    }
}
