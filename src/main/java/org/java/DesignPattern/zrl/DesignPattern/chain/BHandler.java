package org.java.DesignPattern.zrl.DesignPattern.chain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: B处理器
 * @author: WeiQ.chen
 * @date: 2023/2/3
 */
@Slf4j
@Handler(order = 2)
@Component
public class BHandler extends AbstractChainHandler{

    @Override
    void execute(String param) {
        log.info("B 处理中...");
        nextExecute(param, HandlerResult.error("B 处理失败"));
    }
}
