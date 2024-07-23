package org.java.func;

import java.util.function.Consumer;

/**
 * @description: 因为没有出参，适用的场景一般有打印信息，记录日志，发送短信 比如：有设备告警，先记录日志，然后发送短信 参考Collectors
 * @author: Weichuan.chen
 * @date: 2021/12/8
 */
public class ConsumerDemo {

    static final Consumer<Person> LOG_CONSUMER = ConsumerDemo::tailLog;
    static final Consumer<Person> MSG_CONSUMER = ConsumerDemo::sendMsg;

    static final Consumer<Person> DEAL_ALARM_CONSUMER = LOG_CONSUMER.andThen(MSG_CONSUMER);

    private static <T extends Person> void tailLog(T alarm) {
        System.out.println("记录日志：" + alarm.getLogInfo());
    }

    private static <T extends Person> void sendMsg(T alarm) {
        System.out.println("发送短信：" + alarm.getMsg());
    }


    public static void main(String[] args) {
        Person person = new Person();
        person.setMsg("短信提醒：你有一条告警，请处理~");
        person.setLogInfo("2021/12/10 14:27:25 产生告警，记录日志~");

        DEAL_ALARM_CONSUMER.accept(person);
    }

}
