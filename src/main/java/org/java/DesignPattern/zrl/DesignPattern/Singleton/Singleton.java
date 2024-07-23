package org.java.DesignPattern.zrl.DesignPattern.Singleton;

/**
 * desc 单例模式的四种写法之普通单例, 饿汉模式
 * 优点：没有线程安全
 * 缺点：资源浪费，启动实例化时耗时
 *
 * @author WQ
 * @date 2023/11/18/018
 * @since 1.0.0
 */
public class Singleton {
    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {
        // 私有构造器，避免外部创建实例
    }

    /**
     * 对外提供实例
     *
     * @return \
     */
    public static Singleton getInstance() {
        return INSTANCE;
    }
}

/**
 * 懒汉模式
 * 优点：保证线程安全，同时也是在需要的时候才实例化对象，不会浪费资源
 */
class Lsingleton {
    private static volatile Lsingleton instance;

    private Lsingleton() {
        //私有构造
    }

    // 线程不安全
    public static Lsingleton getInstance() {
        if (instance == null) {
            instance = new Lsingleton();
        }
        return instance;
    }

    // 在方法上加锁，比较耗性能，一般判断为空创建对象时才加锁
    public synchronized static Lsingleton getInstance2() {
        if (instance == null) {
            instance = new Lsingleton();
        }
        return instance;
    }

    // 比较常见的双重校验，还需要在实例对象前加 volatile
    public static Lsingleton getInstance3() {
        if (instance == null) {
            synchronized (Lsingleton.class) {
                if (instance == null) {
                    instance = new Lsingleton();
                }
            }
        }
        return instance;
    }
}

/**
 * 单例模式之静态内部类
 * 优点：简单
 * 缺点：通过反射会有漏洞，通过反射实例化无参构造方法会导致单例变成非单例
 * 修改：构造方法加判断条件
 */
class StaticSingleton {
    private StaticSingleton() {
        // 私有构造器
        if (Inner.INSTANCE != null) {
            throw new RuntimeException("不能支持重复实例化");
        }
    }

    public static StaticSingleton getInstance() {
        return Inner.INSTANCE;
    }

    // 静态内部类，只有第一次调用会生成一个静态类
    private static class Inner {
        private static final StaticSingleton INSTANCE = new StaticSingleton();
    }
}

/**
 * 单例模式之枚举单例，effect java 中，枚举单例是单例模式的最佳实现方式
 */
enum SingletonEnum {
    INSTANCE,
    ;

    private Student instance;

    private SingletonEnum() {
        instance = new Student();
    }

    public Student getInstance() {
        return instance;
    }

}

class Student {
    String name;
}

