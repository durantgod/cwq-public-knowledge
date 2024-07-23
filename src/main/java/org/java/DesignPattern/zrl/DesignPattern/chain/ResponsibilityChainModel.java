package org.java.DesignPattern.zrl.DesignPattern.chain;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: ResponsibilityChainModel 责任链模式demo
 * @author: WeiQ.chen
 * @date: 2023/2/2
 */
@RestController
@RequestMapping("/wq-alg/demo")
public class ResponsibilityChainModel {

    @Resource
    ChainHandler handler;

    @GetMapping("/t1")
    public void ss() {
        handler.Start("123");
    }

}
