package org.java.DesignPattern.zrl.DesignPattern.chain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: Handler
 * @author: WeiQ.chen
 * @date: 2023/2/3
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Handler {
    /**
     * 顺序
     *
     * @return /
     */
    int order() default 0;

    /**
     * 是否启用禁用
     *
     * @return /
     */
    boolean disable() default false;
}
