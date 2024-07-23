1、常用的注解有？
@Override, @Deprecated, @SuppressWarnings, @Retention, @Documented, @Target, @Inherited, @SafeVarargs, @FunctionalInterface, @Repeatable
@Override: 标识是重写父类中的方法，不写时是不会检查入参是否错误，而且不会自动屏蔽父类方法，写上是会进行参数校验，自动屏蔽父类方法。
@Deprecated: 标识不推荐使用的代码，可以作用在类，方法，字段...
@SuppressWarnings：屏蔽告警信息
@Retention: 标识注解的作用域，如果需要用反射获取注解，作用域必须是Runtime，如果只是说明，可以在class文件或者source文件中保留即可
@Documented：标识要不要打入到doc文档中
@Target：标识可应用的作用域，如字段，类，或方法上
@Inherited: 标识子类是否可以继承父类的注解，元注解，只作用注解的注解
@SafeVarargs：屏蔽不定长参数带来的警告
@FunctionalInterface： 功能函数式注解，只能有一个方法，lambda的底层注解，最大的作用是可以把函数当做参数一样传递
@Repeatable： 元注解之一，只能作用于注解，还要显式声明标识的注解，标识该注解可以重复声明使用，看到他的第一眼，我们更应该关注它的使用场景：
              spring的使用场景基本上是用于标识配置文件等信息。标识注解可以重复声明！！！！！

