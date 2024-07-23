#### 1、快速启动kafka图形化界面
        docker run -d --rm -p 9000:9000 -e KAFKA_BROKERCONNECT=172.16.163.16:9092,172.16.163.17:9092,172.16.163.18:9092 -e JVM_OPTS="-Xms32M -Xmx64M" -e SERVER_SERVLET_CONTEXTPATH="/" obsidiandynamics/kafdrop
