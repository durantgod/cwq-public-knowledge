package org.java.alg;

import com.google.common.util.concurrent.RateLimiter;

public class TokenBucket {
    RateLimiter rateLimiter = RateLimiter.create(1);

    public String order() {
        boolean acquire = rateLimiter.tryAcquire();
        if (!acquire) {
            return "失败！";
        }
        return "获取成功!";
    }

    public String order2() {
        double acquire = rateLimiter.acquire();
        return "获取成功!, 耗时：" + acquire;
    }
    public static void main(String[] args) throws InterruptedException {
        TokenBucket tokenBucket = new TokenBucket();
        long start = System.currentTimeMillis();
        //Thread.sleep(6000);
        for (int i = 0; i < 5; i++) {
            //Thread.sleep(62);
            System.out.println(tokenBucket.order2() + "， 总耗时：" + (System.currentTimeMillis() - start));
        }
    }
}
