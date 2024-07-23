package org.java.dynamicAgent.staticAgent;

/**
 * @description: Student
 * @author: WeiQ.chen
 * @date: 2022/7/11
 */
public class StudentService implements StudentBehaviorAgent {
    @Override
    public void handOut() {
        System.out.println("交作业");
    }
}
