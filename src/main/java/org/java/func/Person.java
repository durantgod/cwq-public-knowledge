package org.java.func;

import lombok.Data;
import org.java.functionInterface.SpaceArrayList;

import java.util.stream.Collectors;

/**
 * @description: Person
 * @author: Weichuan.chen
 * @date: 2021/12/10
 */
@Data
public class Person {
    String name;

    String phone;

    String logInfo;

    String msg;

    Integer age;

    public Person() {
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        SpaceArrayList<Person> ss = new SpaceArrayList<>();
        ss.add(new Person());
        // ss = ss.spaceStream().collect(Collectors.toList());

    }
}
