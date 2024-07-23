package org.java.DesignPattern.zrl.DesignPattern.chain;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 责任链定义的抽象接口类
 * <p>
 * 1、定义子类执行入口
 * 2、定义下一个处理器的入口
 *
 * @description: AbstractChainHandler
 * @author: WeiQ.chen
 * @date: 2023/2/3
 */
@Slf4j
public abstract class AbstractChainHandler {
    /**
     * 下一个处理器
     */
    @Getter
    @Setter
    AbstractChainHandler nextHandler;

    /**
     * 执行器入口
     *
     * @param param 参数
     */
    abstract void execute(String param);

    /**
     * 定义下一个处理器的入口
     *
     * @param param /
     */
    void nextExecute(String param, HandlerResult result) {
        log.info("上一个过滤的执行结果！{}", result.getMsg());
        if (Objects.nonNull(this.nextHandler)) {
            this.nextHandler.execute(param);
        }
    }
}
