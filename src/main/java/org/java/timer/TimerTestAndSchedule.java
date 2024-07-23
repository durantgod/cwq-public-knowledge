package org.java.timer;

import org.java.juc.thread.WQThreadFactory;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @Desc: desc
 * @Author: WQ
 * @Date: 2022/6/7
 */
public class TimerTestAndSchedule {
    private static int count = 10000;
    private static final int minute = 1;

    // 直播间和任务对应的map
    private static final Map<Long, ScheduledFuture<?>> TASK_POOL = new ConcurrentHashMap<>(256);

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1, new WQThreadFactory("直播间人数线程工厂", "直播业务线程组"));
        int random = count / (minute * 20 / 2 / 2);

        ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(() -> {
            if (count < 0) {
                return;
            }
            // 根据需要刷新的人数以及时间 -> 产生随机数
            int thisCount = new Double(Math.random() * random).intValue();
            System.out.println(Thread.currentThread().getName() + " ->  本次刷新人数为：" + thisCount);
            count = count - thisCount;
            System.out.println("本次刷新后剩余需要刷的人数为：" + count);
        }, 0, 2, TimeUnit.SECONDS);
        TASK_POOL.put(1L, future);
    }

}

