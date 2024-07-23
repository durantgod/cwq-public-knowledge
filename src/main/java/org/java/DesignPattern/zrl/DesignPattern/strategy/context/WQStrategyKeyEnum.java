package org.java.DesignPattern.zrl.DesignPattern.strategy.context;

import lombok.Getter;

/**
 * @description: WQStrategyKeyEnum 每个应用域的策略名称
 * @author: Weichuan.chen
 * @date: 2021/11/22
 */
public enum WQStrategyKeyEnum {
    /**
     * 场景1 ex:设备联动
     **/
    DEVICE("0"),

    /**
     * 场景2 ex:消息通知
     */
    MSG("1"),
    ;

    @Getter
    private final String key;

    WQStrategyKeyEnum(String key) {
        this.key = key;
    }
}
