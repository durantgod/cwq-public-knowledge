package org.java.functionInterface;

import org.java.func.Person;

/**
 * @description: SpaceFunctionDemo
 * @author: Weichuan.chen
 * @date: 2021/12/13
 */
public class SpaceFunctionDemo {

    static final SpaceFunction<Person, String ,Integer> FUNCTION = SpaceFunctionDemo::createPerson;
    // static final SpaceRunnableFunction<Person> spaceFunction = SpaceFunctionDemo::createPerson;


    private static Person apply(SpaceRunnableFunction<Person> spaceFunction) {
        return spaceFunction.apply();
    }

    public static void main(String[] args) {
        Integer age1 =  FUNCTION.apply("张三", 22).getAge();
        Integer age2 = apply(() -> createPerson("李四", 19)).getAge();

        /*
         * age1  age2 the result is same, but age1 is static and age2 is dynamic
         *
         * 因为函数式接口在接口上已经定义好参数的类型，这样就可以静态编写方法，实际调用传入参数即可（运行时传入即可）。
         * 如果在函数式接口编写上没有定义好参数类型，只能在编写代码时定义好参数类型。
         *
         * 上面的说法不对，应该是如果在定义函数式接口如果没有定义参数，是不能直接调用的，必须要在调用其他方法才能实现相同效果
         */
        System.out.println(age1);
        System.out.println(age2);
    }

    public static Person createPerson(String name, Integer age) {
       return new Person(name, age);
    }

    static Person apply(String name, Integer age) {
        return new Person(name, age);
    }

}
