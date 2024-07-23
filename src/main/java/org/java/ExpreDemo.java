package org.java;

import io.github.jarvisjin.finexpr.expr.Expression;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @description: ExpreDemo
 * @author: WeiQ.chen
 * @date: 2022/12/10
 */
public class ExpreDemo {

    @Test
    public void test() {
        Expression e = new Expression("a / b * c / d * 152");

        e.addVariable("a", new BigDecimal("279"));
        e.addVariable("b", new BigDecimal("120"));
        e.addVariable("c", new BigDecimal("33"));
        e.addVariable("d", new BigDecimal("35"));
        e.addVariable("e", new BigDecimal("152"));

        BigDecimal result = e.calculate();
        System.out.println(result);
    }
}
