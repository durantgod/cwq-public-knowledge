package org.java.myStream;

import org.java.func.Person;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * @description: HeightDemo
 * @author: WeiQ.chen
 * @date: 2023/3/13
 */
public class HeightDemo {


    public static void main(String[] args) {
        SArrayList<Person> ps = new SArrayList<>();
        ps.add(new Person("张三", 16));
        ps.add(new Person("李四", 16));
        ps.myStream().filter(v -> v.getAge()> 0).map(Person::getAge);
    }
}

interface MyStream<E> {
    <R> MyStream<E> map(Function<? extends E, ? extends R> fuc);

    <R> MyStream<E> filter(Function<? extends E, ? extends R> fuc);
}

abstract class ReferencePipeline<P_OUT, P_INT> implements MyStream<P_INT> {
    boolean parallel;
    BaseSArrayList<P_OUT> sArrayList;

    /**
     * 定义管道流的头
     *
     * @param <P_OUT>
     * @param <P_INT>
     */
    static class Head<P_OUT, P_INT> extends ReferencePipeline<P_OUT, P_INT>{
        public Head(BaseSArrayList<P_OUT> sArrayList, boolean parallel) {
            super(sArrayList, parallel);
        }
    }

    /**
     * 定义无状态操作，不需要上个流程全部完成就可以执行下个操作，filter, map...
     *
     * @param <P_OUT>
     * @param <P_INT>
     */
    static class StatelessOP<P_OUT, P_INT> extends ReferencePipeline<P_OUT, P_INT>{
        public StatelessOP(MyStream<P_INT> stream, Function<P_INT, P_OUT> func) {
            super(stream, func);
        }
    }

    /**
     * 定义有状态操作 如collect, count 等操作
     *
     * @param <P_OUT>
     * @param <P_INT>
     */
    static class StatefulOP<P_OUT, P_INT> extends ReferencePipeline<P_OUT, P_INT>{
        public StatefulOP(MyStream<P_INT> stream, Function<P_INT, P_OUT> func) {
            super(stream, func);
        }
    }

    public ReferencePipeline(BaseSArrayList<P_OUT> sArrayList, boolean parallel) {
        this.parallel = parallel;
        this.sArrayList = sArrayList;
    }

    public ReferencePipeline(MyStream<P_INT> ref, Function<P_INT, P_OUT> func) {

    }

    @Override
    public <R> MyStream<P_INT> map(Function<? extends P_INT, ? extends R> fuc) {
        return null;
    }

    @Override
    public <R> MyStream<P_INT> filter(Function<? extends P_INT, ? extends R> fuc) {
        return null;
    }

}

class BaseSArrayList<E> extends ArrayList<E> {
    MyStream<E> myStream() {
        return new ReferencePipeline.Head<>(this, false);
    }
}

class SArrayList<E> extends BaseSArrayList<E>{

}

