package org.java.DesignPattern.zrl.DesignPattern.strategy;

import org.java.DesignPattern.zrl.DesignPattern.strategy.context.WQStatisticsScope;
import org.java.DesignPattern.zrl.DesignPattern.strategy.context.WQStrategyContext;
import org.java.DesignPattern.zrl.DesignPattern.strategy.context.WQStrategyFactory;
import org.java.DesignPattern.zrl.DesignPattern.strategy.context.WQStrategyKeyEnum;

/**
 * 策略工厂的实现
 *
 * @author WQ
 * @date 2023/12/8/008
 * @since 1.0.0
 */
public class StrategyDemo {
    WQStrategyFactory<? extends WQStrategyContext> factory = WQStrategyFactory.getInstance();

    public void test() {
        WQStrategyContext sim = factory.create(WQStrategyKeyEnum.DEVICE, "1");

        sim.doSomething();
    }
}

@WQStatisticsScope(scope = WQStrategyKeyEnum.MSG, strategyKey = "1")
class Sim extends WQStrategyContext {
    @Override
    public void doSomething() {
        // 短信推送
    }
}

@WQStatisticsScope(scope = WQStrategyKeyEnum.MSG, strategyKey = "2")
class Sim2 extends WQStrategyContext{
    @Override
    public void doSomething() {
        // 邮件推送
    }
}

@WQStatisticsScope(scope = WQStrategyKeyEnum.DEVICE, strategyKey = "1")
class Device2 extends WQStrategyContext{
    @Override
    public void doSomething() {
        // 设备触发
    }
}


