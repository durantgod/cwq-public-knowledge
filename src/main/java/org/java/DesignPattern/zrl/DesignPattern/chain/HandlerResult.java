package org.java.DesignPattern.zrl.DesignPattern.chain;

import lombok.Data;

/**
 * 处理结果类
 *
 * @author: WeiQ.chen
 * @date: 2023/2/3
 */
@Data
public class HandlerResult {

    private String msg;

    public static HandlerResult ok() {
        HandlerResult res = new HandlerResult();
        res.setMsg("ok");
        return res;
    }

    public static HandlerResult error(String msg) {
        HandlerResult res = new HandlerResult();
        res.setMsg(msg);
        return res;
    }
}
