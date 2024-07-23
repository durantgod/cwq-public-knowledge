package org.java.DesignPattern.zrl.DesignPattern.Singleton;

import lombok.Getter;

/**
 * 多例模式 单例模式控制对象数量的一种手段
 *
 * @author WQ
 * @date 2023/11/18/018
 * @since 1.0.0
 */
public class PatternModel {
    // 普通单例不做演示
}

enum PatternEnum {
    INSTANCE1("张三", 1),
    INSTANCE2("李四", 0),;

    @Getter
    private final Teacher teacher;

    PatternEnum(String name, Integer sex) {
        teacher = new Teacher(name, sex);
    }
}

class Teacher{
    String name;
    Integer sex;

    public Teacher(String name, Integer sex) {
        this.name = name;
        this.sex = sex;
    }
}