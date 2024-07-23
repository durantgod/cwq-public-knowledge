#### 1、ateway --> 路由到service --> 微服务组件通讯RPC框架（feign, dubbo, zookeeper/nacos)
        dubbo负载均衡策略：1、随机 2、轮训 3、最少活跃调用 4、一致性hash
        nacos负载均衡: 内置ribbon，默认是轮训机制，这个机制上可以加权重
        dubbo通讯协议：dubbo协议，传输是tcp, 长连接，适用：短且多的调用，且内容不能太大，大文件或者超大字符串不适用该协议。
                     http协议，rmi协议（多且短），Hessian（多且短）
