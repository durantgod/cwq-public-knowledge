package org.java.interfaces;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: AnnotionInterface
 * @author: WeiQ.chen
 * @date: 2022/4/7
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
    public @interface AnnotionInterface {
    int value();
}
