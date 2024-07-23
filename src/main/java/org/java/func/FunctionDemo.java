package org.java.func;

import java.util.function.Function;

/**
 * @description: FunctionDemo  转换对象 参考Optional对象
 * @author: Weichuan.chen
 * @date: 2021/12/13
 */
public class FunctionDemo {

    static final Function<String, Person> FUNCTION = FunctionDemo::chang;

    public static void main(String[] args) {
        apply(v -> new Person(v, 1));
    }


    static Person chang(String str) {
        return new Person(str, 11);
    }

    static void apply(Function<String, Person> function) {
        function.apply("sdf");
    }

}
