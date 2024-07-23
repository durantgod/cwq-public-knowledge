package org.java.func;

import java.util.function.Supplier;

/**
 * @description: 创建对象 使用场景：参考Collectors
 * @author: Weichuan.chen
 * @date: 2021/12/10
 */
public class SupplierDemo {

    public static final Supplier<Person> SP = SupplierDemo::createPerson;


    public static void main(String[] args) {
        System.out.println(SP.get().getName().concat("年龄为：") + SP.get().getAge());
        System.out.println(SP.get().getName().concat("年龄为：") + SP.get().getAge());
    }


    static Person createPerson() {
        return new Person("zs",Double.valueOf(Math.random() * 10).intValue());
    }
}
