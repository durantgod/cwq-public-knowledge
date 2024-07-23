package org.java.dynamicAgent.staticAgent;

/**
 * @description: Student
 * @author: WeiQ.chen
 * @date: 2022/7/11
 */
public class StudentProxyService implements StudentBehaviorAgent {

    StudentService studentService;

    public StudentProxyService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void handOut() {
        // 班长统一收作业逻辑
        System.out.println("班长统一收作业逻辑");

        // 班长统一叫作业逻辑
        studentService.handOut();
    }
}
