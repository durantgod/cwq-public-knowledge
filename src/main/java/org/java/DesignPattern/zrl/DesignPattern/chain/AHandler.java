package org.java.DesignPattern.zrl.DesignPattern.chain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 责任链中处理器A
 * <p>
 * ps: 应该还需要考虑顺序，分组，并发，禁用
 *
 * @description: 处理器A
 * @author: WeiQ.chen
 * @date: 2023/2/3
 */
@Slf4j
@Handler(order = 1)
@Component
public class AHandler extends AbstractChainHandler {

    @Override
    void execute(String param) {
        log.info("A 处理中...");
        nextExecute(param, HandlerResult.ok());
    }
}
