#### 1、docker容器中使用jstack,jmap等分析工具
        方案一：docker1.0版本后加入安全特性，jmap这些工具依赖linux的PTRACE_ATTACH，所以需要加入参数 --cap-add=SYS_PTRACE
                如果是docker-compose,可以：
                cap-add: 
                    - SYS_PTRACE
                
        方案二：镜像安装tini，由它管理进程,如：ENTRYPOINT ["tini"]


    
#### 2、已有docker进程中使用命令
        cd /etc/apk
        vi repositories

        替换为阿里的源：
        http://mirrors.aliyun.com/alpine/v3.8/main/
        http://mirrors.aliyun.com/alpine/v3.8/community/

        apk update
        apk add openjdk8






####   常用命令
        docker cp 本地路径 image/containerName:/opt(容器内路径)
        关闭指定端口防火墙
        firewall-cmd --zone=public --add-port=18080/tcp --permanent


