#### node 环境搭建，已经配置环境变量
    1、下载包：https://registry.npmmirror.com/binary.html?path=node/v14.14.0/
    2、解压：tar -zxvf node-v14.14.0-linux-x64.tar.gz
    3、mv node-v14.14.0-linux-x64 /usr/local/node
    4、配置环境变量：
        vim /etc/profile
        
        写入：
        export NODE_HOME=/usr/local/node  
        export PATH=$NODE_HOME/bin:$PATH

        保存后：source /etc/profile

    上面整个node环境就配置完成了，可能还需要改一下下载包的地址：
    
#### vuepress 轻量级博客平台搭建
    1、官方文档地址：
        https://vuepress.vuejs.org/zh/guide/getting-started.html

    2、后台运行：nohup npm run docs:dev >/dev/null 2>&1 & exit
       后台指定端口运行：nohup npm run docs:dev -- --port 8088>/dev/null 2>&1 & exit

