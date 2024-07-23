package org.java.juc.threadLocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal 实际使用场景
 *
 * @author WQ
 * @date 2024/7/23/023
 * @since 1.0.0
 */
@DisplayName("测试ThreadLocal功能")
@Slf4j
public class ThreadLocalDemoTest {

    @Test
    @DisplayName("使用场景1：代替参数在上下文中的传递")
    public void testThreadLocal() {
        ThreadLocal<Map<String, String>> threadLocal = new ThreadLocal<>();
        Map<String, String> param = new HashMap<>();
        param.put("name", "Durant");
        param.put("age", "22");

        threadLocal.set(param);

        new Thread(() -> {
            log.info("T线程：{}， 获取到的名字：{}", Thread.currentThread().getName(), threadLocal.get().get("name"));
        }).start();

        log.info("M线程：{}， 获取到的名字：{}", Thread.currentThread().getName(), threadLocal.get().get("name"));
    }

}
