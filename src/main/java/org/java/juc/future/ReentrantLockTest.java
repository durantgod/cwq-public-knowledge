package org.java.juc.future;

import org.junit.Test;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description: ReentranLockTest
 * @author: WeiQ.chen
 * @date: 2022/7/18
 */
public class ReentrantLockTest {
    /**
     * 读写锁，别看错了 可以指定是否使用公平锁还是非公平锁，内部有FairSync，nonFairSync 等三个内部类实现
     */
    ReentrantReadWriteLock reentrantLock = new ReentrantReadWriteLock();

    ReentrantLock rk = new ReentrantLock();

    @Test
    public void test1() {
        Lock r = reentrantLock.readLock();
        Lock w = reentrantLock.writeLock();



    }
}
