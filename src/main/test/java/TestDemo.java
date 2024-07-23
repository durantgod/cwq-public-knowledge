import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: TestDemo
 * @author: WeiQ.chen
 * @date: 2022/11/21
 */
@SpringBootTest
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestDemo {

    @Order(1)
    @Execution(ExecutionMode.SAME_THREAD)
    @DisplayName("单线程执行10次")
    @RepeatedTest(value = 10, name="完成度：{currentRepetition}/{totalRepetitions}")
    public void test1() {
        String expression = "a-(b-c)>100";
        Expression compiledExp = AviatorEvaluator.compile(expression);
        Map<String, Object> env = new HashMap<>();
        env.put("a", 100.3);
        env.put("b", 45);
        env.put("c", -199.100);
        Boolean result = (Boolean) compiledExp.execute(env);
        log.info("测试进度完成 1/10");
    }

    @Order(2)
    @Execution(ExecutionMode.CONCURRENT)
    @DisplayName("多线程执行10次")
    @RepeatedTest(value = 10, name="完成度：{currentRepetition}/{totalRepetitions}")
    public void test2() {
        String expression = "MTC + STM + 氮气用量 - F";
        Expression compiledExp = AviatorEvaluator.compile(expression);
        Map<String, Object> env = new HashMap<>();
        env.put("F", 1);
        env.put("MTC", 2);
        env.put("STM", 3);
        env.put("氮气用量", 4);
        Object result = compiledExp.execute(env);
        log.info("测试进度完成 2/10");
    }

    @Test
    public void test3() {
        double d1 = 100.0;
        double d2 = 100.0;
        double d3 = 1000.0;
        Double d4 = 1000.0;
        Double d5 = 1000.0;

        System.out.println(d1 == d2);
        System.out.println(d3 == d4);
        System.out.println(d5 == d4);
    }
}
