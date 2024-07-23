package org.java.DesignPattern.zrl.DesignPattern.strategy.context;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: WQStrategyFactory 策略工厂类，单例模式，用于注册/生成相对应的策略实现类
 * @author: Weichuan.chen
 * @date: 2021/11/22
 */
public class WQStrategyFactory<T extends WQStrategyContext> implements WQFactory {
    /**
     * 实例
     */
    private static volatile WQStrategyFactory<?> instance = null;

    /**
     * 策略集合
     */
    private final Map<Object, Map<Object, T>> strategy = new HashMap<>();

    private WQStrategyFactory() {
    }

    public static WQStrategyFactory<? extends WQStrategyContext> getInstance() {
        if (instance == null) {
            instance = new WQStrategyFactory<>();
            synchronized(WQStrategyFactory.class) {
                if (instance == null) {
                    instance = new WQStrategyFactory<>();
                }
            }
        }
        return instance;
    }

    /**
     * 根据策略标识获取相应的处理逻辑，如：根据设备关系策略名和对应的策略flag获取实现类
     *
     * @param flag com.space.iot.device.enums.DeviceRelationEnum
     * @param key {@link WQStrategyKeyEnum}
     * @return 返回实现类
     */
    public T create(Object key, Object flag) {
        return strategy.getOrDefault(key, new HashMap<>(1)).getOrDefault(flag, null);
    }


    /**
     * 注册策略，把相应的策略注册上来，在工厂中统一生成
     */
    public void register(Object key, Map<Object, T> sonStrategy) {
        this.strategy.put(key, sonStrategy);
    }

}
