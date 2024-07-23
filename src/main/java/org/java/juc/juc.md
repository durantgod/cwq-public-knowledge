### 1、常见问题
        1、什么是AQS，作用及原理？
            ：abstractQueueSynchronizer，即抽象的队列同步器，是一种构建锁和同步器的框架。
            reentrantLock，semaphone，countDownLatch...都有使用，还记得公平锁和非公平锁的实现，
            之前看过一下源码，AQS维护一个双向链表实现的FIFO队列，ReentrantLock公平非公平通过参数控制，
            默认是非公平锁。CLH队列和非CLH队列。还有共享锁用到的也是这个类。
