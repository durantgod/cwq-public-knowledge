package org.java.alg.offer;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @description: LinedRever
 * @author: WeiQ.chen
 * @date: 2023/4/6
 */
public class LinedRever {

    public static void main(String[] args) {
        // 构造1->2->3->4单向链表
        SingleLinkObject link = new SingleLinkObject(1, new SingleLinkObject(2, new SingleLinkObject(3, new SingleLinkObject(4, null))));

        SingleLinkObject pre = null;
        SingleLinkObject cur = link;
        SingleLinkObject temp;
        while(cur != null) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }

        System.out.println(JSON.toJSONString(pre));

    }


}

@Data
class SingleLinkObject {
    int id;
    SingleLinkObject next;

    public SingleLinkObject(int id, SingleLinkObject next) {
        this.id = id;
        this.next = next;
    }
}
