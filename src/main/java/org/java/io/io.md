##### 1、
        1、BIO(blocking io 同步阻塞)，NIO(non-blocking-io 同步非阻塞IO), io多路复用（selector, epoll），AIO(异步IO)
        2、场景分析：
            BIO: 连接数少，且固定应用，并发小（一个连接一个线程）
            NIO：连接数多且短，比如聊天，弹幕，服务器间通讯（一个线程对应多个连接）
            AIO: 连接数多且连接比较长
        3、NIO: Channel(通道)，Buffer(缓冲区), Selector(选择器),netty中用到
