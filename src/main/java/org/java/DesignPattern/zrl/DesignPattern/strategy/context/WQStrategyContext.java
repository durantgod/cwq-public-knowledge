package org.java.DesignPattern.zrl.DesignPattern.strategy.context;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: WQStrategyContext 策略工厂参数父类
 * @author: Weichuan.chen
 * @date: 2021/11/22
 */
@Getter
@Setter
public abstract class WQStrategyContext {

    public abstract void doSomething();
}
