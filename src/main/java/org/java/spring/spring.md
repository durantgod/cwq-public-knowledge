#### spring常见面试题
    1、什么是Spring的(IOC)依赖注入？
        通俗来说是不需要显式创建对象，只需要从spring容器中获取即可。
    2、有什么类型的依赖注入(IOC)方式？
        set方法注入，构造器注入，命名空间注入，注解注入
    3、 spring支持那些配置方式？
        xml方式；注解方式如#service
    4、spring bean的生命周期?
        实例化Bean -> 设置属性 -> BeanPostProcessor预初始化 -> InitializingBean 的afterPropertiesSet方法
        -> 调用BeanPostProcessor后置方法 -> 放入spring一级缓存中。
        
        
