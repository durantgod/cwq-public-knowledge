## 该文件夹主要是动态代理的一些小实验
总结：springboot2.x 中注入的对象基本都是cglib 通过enhance动态代理创建的对象
当代理对象去调用方法时，再通过反射去实现，首先通过 MethodAccessor 通过一定的策略调用 DelegatingMethodAccessorImpl（java版本实现）
或 NativeMethodAccessorImpl（native c++实现 jvm中获取），可以设置阈值，一般调用超过15次用方式1，调用少于15次用方式2

遇到的问题：
1、代理类中获取不到被代理类中的private方法，因为代理类可以理解为被代理类的子类。
2、通过反射获取private方法，在反射进去后，依旧为代理类属性，而不是被代理类的属性。（未解，主要方法考虑：nativeMethodAccessorImpl）


cglib基于ASM字节码工具操作字节码


所以：

如果想要实现JDK动态代理那么代理类必须实现接口，否则不能使用;
如果想要使用CGlib动态代理，那么代理类不能使用final修饰类和方法；
还有： 在jdk6、jdk7、jdk8逐步对JDK动态代理优化之后，在调用次数较少的情况下，JDK代理效率高于CGLIB代理效率，
只有当进行大量调用的时候，jdk6和jdk7比CGLIB代理效率低一点，但是到jdk8的时候，jdk代理效率高于CGLIB代理。

不同点：
1.java动态代理只能对类的实现接口代理，不能对类直接代理。

2.cglib动态代理既能对类直接代理也能对实现接口的代理。

3.jdk动态代理实现InvocationHandler接口和invoke（）方法

4.cglib动态代理实现MethodInterceptor方法拦截器intercept方法；

5.利用ASM框架，对代理对象类生成的class文件加载进来，通过java反射机制修改其字节码生成子类来处理


相同点：
都是利用反射实现的代理。



1.什么时候使用jdk动态代理和cglib动态代理？

 1、目标对象生成了接口 默认用JDK动态代理

 2、如果目标对象使用了接口，可以强制使用cglib

 3、如果目标对象没有实现接口，必须采用cglib库，Spring会自动在JDK动态代理和cglib之间转换



2、Cglib比JDK快？

 1、cglib底层是ASM字节码生成框架，但是字节码技术生成代理类，在JDL1.6之前比使用java反射的效率要高

 2、在jdk6之后逐步对JDK动态代理进行了优化，在调用次数比较少时效率高于cglib代理效率

 3、只有在大量调用的时候cglib的效率高，但是在1.8的时候JDK的效率已高于cglib

 4、Cglib不能对声明final的方法进行代理，因为cglib是动态生成代理对象，final关键字修饰的类不可变只能被引用不能被修改；



3、Spring如何选择是用JDK还是cglib？

 1、当bean实现接口时，会用JDK代理模式

 2、当bean没有实现接口，用cglib实现

 3、可以强制使用cglib（在spring配置中加入<aop:aspectj-autoproxy proxyt-target-class=”true”/>）


名词解析：

dispatcher : 调度器
interceptor: 拦截器
Accessor:    存取器










