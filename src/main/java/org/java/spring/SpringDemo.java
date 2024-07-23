package org.java.spring;

import org.java.dynamicAgent.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description: SpringDemo
 * @author: WeiQ.chen
 * @date: 2023/2/22
 */
public class SpringDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        ac.registerShutdownHook();
        System.out.println(ac.getBean(UserService.class));
    }
}
