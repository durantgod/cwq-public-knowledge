package org.java.dynamicAgent.staticAgent;

import org.junit.Test;

/**
 * @description: StudentStaticDemo
 * @author: WeiQ.chen
 * @date: 2022/7/11
 */
public class StudentStaticDemo {
    public static void main(String[] args) {
        StudentService student = new StudentService() {
            @Override
            public void handOut() {
                System.out.println("增强方法？");
            }
        };

        student.handOut();
    }

    @Test
    public void staticAgentTest() {
        StudentProxyService proxyService = new StudentProxyService(new StudentService());
        proxyService.handOut();
    }
}
