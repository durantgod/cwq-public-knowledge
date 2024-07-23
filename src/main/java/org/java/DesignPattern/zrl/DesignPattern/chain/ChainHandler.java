package org.java.DesignPattern.zrl.DesignPattern.chain;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 责任链处理器
 * 统一管理每个处理器，包括加载，存储，排序，处理器入口等
 *
 * @author: WeiQ.chen
 * @date: 2023/2/3
 */
@Service
public class ChainHandler {

    /**
     * 责任链
     */
    List<AbstractChainHandler> chain;

    /**
     * 当前处理器
     */
    AbstractChainHandler currentHandler;

    @Resource
    ApplicationContext applicationContext;

    /**
     * 初始化时获取需要加载的处理器
     */
    @PostConstruct
    void init() {
        // 获取AbstractChainHandler的子类，并且获取对应的顺序和禁用状态 注解
        Map<String, AbstractChainHandler> handlerClass = applicationContext.getBeansOfType(AbstractChainHandler.class);
        if (MapUtils.isEmpty(handlerClass)) {
            throw new RuntimeException("not found AbstractChainHandler");
        }
        chain = new ArrayList<>();
        // 过滤 & 排序
        List<AbstractChainHandler> temp = handlerClass.values().stream()
                .filter(v -> {
                    Handler handler = v.getClass().getAnnotation(Handler.class);
                    return !handler.disable();
                }).sorted((a, b) -> {
                    Class<?> aC = a.getClass();
                    Handler handlerA = aC.getAnnotation(Handler.class);

                    Class<?> bC = b.getClass();
                    Handler handlerB = bC.getAnnotation(Handler.class);

                    return handlerA.order() - handlerB.order();
                }).collect(Collectors.toList());
        // 添加到责任链中
        chain.addAll(temp);

        if (CollectionUtils.isEmpty(chain)) {
            throw new RuntimeException("not found AbstractChainHandler");
        }

        currentHandler = chain.get(0);
        chain.remove(0);

        AbstractChainHandler head = currentHandler;
        for (AbstractChainHandler next : chain) {
           currentHandler.setNextHandler(next);
           currentHandler = currentHandler.getNextHandler();
        }
        currentHandler = head;
    }


    /**
     * 开始执行
     *
     * @param param /
     */
    public void Start(String param) {
        Assert.notNull(currentHandler, "IllegalArgumentException not found order chain handler");
        currentHandler.execute(param);
    }


}
