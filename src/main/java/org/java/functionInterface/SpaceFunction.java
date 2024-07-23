package org.java.functionInterface;

/**
 * @description: FunctionInterface
 * @author: Weichuan.chen
 * @date: 2021/12/13
 */
@FunctionalInterface
public interface SpaceFunction<R, T, U> {

    /**
     * 转换对象
     *
     * @param t 输入参数1
     * @param u 输入参数2
     * @return 返回对象
     */
    R apply(T t, U u);

}
