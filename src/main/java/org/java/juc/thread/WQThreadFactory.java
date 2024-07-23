package org.java.juc.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: WQThreadFactory
 * @author: WeiQ.chen
 * @date: 2022/11/15
 */
public class WQThreadFactory implements ThreadFactory {
    String threadFactoryName;

    private static final AtomicInteger poolNum = new AtomicInteger();

    private ThreadGroup group;

    public WQThreadFactory(String threadFactoryName) {
        this.threadFactoryName = threadFactoryName;
    }

    public WQThreadFactory(String threadFactoryName, String group) {
        this.threadFactoryName = threadFactoryName;
        this.group = new ThreadGroup(group);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread ans = new Thread(group, r);
        ans.setDaemon(false);
        ans.setName(this.threadFactoryName.concat("中的线程").concat(String.valueOf(poolNum.incrementAndGet())));
        return ans;
    }
}
