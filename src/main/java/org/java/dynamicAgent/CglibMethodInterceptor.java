package org.java.dynamicAgent;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @description: CglibMethodInterceptor 方法拦截器
 * @author: WeiQ.chen
 * @date: 2022/9/28
 */
public class CglibMethodInterceptor implements MethodInterceptor {

    private Object target;

    public Object getTarget() {
        return target;
    }

    public Object  getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.getTarget().getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        methodProxy.invokeSuper(o, objects);
        return null;
    }
}
