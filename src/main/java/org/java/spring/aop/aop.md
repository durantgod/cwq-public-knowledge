#### 1、aop的几种实现方式？区别是？
    a: 通过实现spring原生的api(如：MethodBeforeAdvice， AfterReturningAdvice ) 一般有before advice  after advice in环绕实现等...注意需要配置aop-config xml配置文件指定某个方法用那个拦截器
    b: 通过自定义方式实现。定义切面类，这种方式和上面方式都需要通过xml aop-config 去配置
    c: 通过注解实现@Aspect

    另一种问法是：aop中的实现原理，一般使用jdk动态代理和cglib动态代理，对于没有接口的类需要强制使用cglib，默认是jdk...两者速度都差不多
    原理是：cglib通过生成子类的方式去实现动态代理，遇到的坑点：方法是私有化的，动态代理的时候有问题。jdk是通过实现接口的方式做的动态代理。
    
    Cglib基于ASM字节码工具操作字节码
