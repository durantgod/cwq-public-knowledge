package org.java.dynamicAgent.cglib;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.core.DebuggingClassWriter;
import org.java.dynamicAgent.CglibMethodInterceptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @description: CglibDemo 模拟场景
 * @author: WeiQ.chen
 * @date: 2022/9/28
 */
@Slf4j
@DisplayName("Cglib动态代理总测试类")
public class CglibDemo {

    /**
     * 常规测试，主要测试动态代理的过程
     */
    @Test
    @DisplayName("测试cglib生成代理对象的过程")
    public void test1() throws Exception {
        saveGeneratedCGlibProxyFiles(System.getProperty("user.dir")+"\\4");
        // 构建增强器，设置代理对象以及设置代理对象代理的对象
        CglibMethodInterceptor interceptor = new CglibMethodInterceptor();
        TeacherImpl teacher = new TeacherImpl();
        teacher.setName("印度阿三");
        interceptor.setTarget(teacher);

        Teacher anotherOne = (Teacher) interceptor.getProxy();

        anotherOne.payAllAfter();
    }

    /**
     * 测试反射实现方法
     */
    @Test
    @DisplayName("测试cglib生成代理对象的过程")
    public void test2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 构建增强器，设置代理对象以及设置代理对象代理的对象
        CglibMethodInterceptor interceptor = new CglibMethodInterceptor();
        TeacherImpl teacher = new TeacherImpl();
        teacher.setName("印度阿三");
        interceptor.setTarget(teacher);

        TeacherImpl anotherOne = (TeacherImpl) interceptor.getProxy();
        Method m = anotherOne.getClass().getDeclaredMethod("pay");
        m.setAccessible(true);
        m.invoke(anotherOne, null);
    }

    /**
     * 测试反射实现方法
     */
    @Test
    @DisplayName("通过cglib代理获取到的是同一个对象")
    public void test5() {
        // 构建增强器，设置代理对象以及设置代理对象代理的对象
        CglibMethodInterceptor interceptor = new CglibMethodInterceptor();
        TeacherImpl teacher = new TeacherImpl();
        teacher.setName("印度阿三");
        interceptor.setTarget(teacher);

        TeacherImpl anotherOne = (TeacherImpl) interceptor.getProxy();
        TeacherImpl anotherSecond = (TeacherImpl) interceptor.getProxy();

        log.info("anotherOne: {}", anotherOne.getClass());
        log.info("anotherSecond: {}", anotherSecond.getClass());

    }

    @Test
    @DisplayName("说明过程")
    public void test3() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException{
        // 构建增强器，设置代理对象以及设置代理对象代理的对象
        CglibMethodInterceptor interceptor = new CglibMethodInterceptor();
        TeacherImpl teacher = new TeacherImpl();
        System.out.println("main:" + teacher.getClass());
        teacher.setName("印度阿三");
        interceptor.setTarget(teacher);

        // 这里获取到的是cglib通过enhance字节码增强器生产出的代理类，亦可以理解为被代理对象的子类，因此父类中的私有方法是获取不到的
        TeacherImpl anotherOne = (TeacherImpl) interceptor.getProxy();

        // 这里通过代理对象获取方法是获取不到的
        // NoSuchMethodException: org.dynamicAgent.cglib.TeacherImpl$$EnhancerByCGLIB$$b97ea141.pay()
        // Method m = anotherOne.getClass().getDeclaredMethod("pay");

        // 从代理类中获取方法列表(相当于从被代理对象的子类获取一样，只能获取非私有方法)
        Method m = TeacherImpl.class.getDeclaredMethod("print");
        // Method m = anotherOne.getClass().getDeclaredMethod("pay");
        m.setAccessible(true);

        m.invoke(anotherOne);
        anotherOne.print();
    }


    /**
     * 设置保存Cglib代理生成的类文件。
     */
    public static void  saveGeneratedCGlibProxyFiles(String dir) throws Exception {
        Field field = System.class.getDeclaredField("props");
        field.setAccessible(true);
        Properties props = (Properties) field.get(null);
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, dir);
        props.put("net.sf.cglib.core.DebuggingClassWriter.traceEnabled", "true");
    }
}
