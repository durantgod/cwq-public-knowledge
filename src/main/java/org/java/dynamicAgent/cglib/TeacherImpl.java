package org.java.dynamicAgent.cglib;

import org.java.dynamicAgent.CglibMethodInterceptor;
import org.java.dynamicAgent.cglib.Teacher;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description: TeacherImpl
 * @author: WeiQ.chen
 * @date: 2022/9/28
 */
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TeacherImpl implements Teacher {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void payAllAfter() {
        System.out.println("我来了...");
    }

    @Override
    public void payAllBefore() {
        System.out.println("hhhh");
    }

    public void pay() {
        System.out.println("我走了...");
        System.out.println("pay：" + this.getClass() );
    }

    public void print() {
        System.out.println("print:" + this.getClass());
    }
}
