package org.java.DesignPattern.zrl.DesignPattern.strategy.context;

import java.lang.annotation.*;

/**
 * @description: WQStatisticsScope 策略注解，scope是场景，key是指具体不同策略
 * @author: Weichuan.chen
 * @date: 2021/11/22
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface WQStatisticsScope {
    /**
     * 使用场景
     *
     * @return str
     */
    WQStrategyKeyEnum scope();

    /**
     * 具体策略
     *
     * @return str
     */
    String strategyKey();
}
