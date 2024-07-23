package org.java.interfaces;

import java.lang.annotation.Annotation;

/**
 * @description: AnnotionTest
 * @author: WeiQ.chen
 * @date: 2022/4/7
 */
@AnnotionInterface(2)
public class AnnotionTest {

    public static void main(String[] args) throws ClassNotFoundException {
        Class cl = Class.forName("org.interfaces.AnnotionTest");
        Annotation[] annotations =  cl.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);//输出：@com.reflection.mytypeannotation(value=user_info)
        }
    }
}
