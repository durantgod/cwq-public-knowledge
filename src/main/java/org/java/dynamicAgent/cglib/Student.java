package org.java.dynamicAgent.cglib;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.java.dynamicAgent.cglib.Person;

/**
 * @description: Student 学生代理类
 * @author: WeiQ.chen
 * @date: 2022/9/27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Student extends Person {

    public void pay() {
        System.out.println("学生交学费...");
    }

}
