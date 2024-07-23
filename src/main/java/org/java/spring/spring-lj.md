#### spring 启动方式有注解，xml, web等方式

注解方式：
AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
ac.registerShutdownHook();
System.out.println(ac.getBean(UserService.class));




1.容器启动的准备工作；
2.准备工厂，初始化bean工厂，设置一些后置处理器还有一些参数设置；
3.通过bean工厂的后置处理器去处理配置类信息，将配置类配置的路径下的所有普 通类扫描成BeanDefinition。
4.注册系统中的bean工厂后置处理器到后置处理器列表中。
5.初始化资源；
6.事件和监听的发布处理；
7.最后创建spirng的核心单例池；
8.容器启动完成，发布事件







Spring启动过程是IOC容器的启动过程，本质是创建和初始化bean工厂(BeanFactory).BeanFactory是Spring IOC的核心，Spring使用beanFactory来实例化，配置和管理bean。
对于web程序，IOC容器启动过程即是建立上下文的过程，web容器会提供一个全局的servletContext上下文环境。
其启动过程主要包含三个类，ContextLoaderListener，ContextLoader和XmlWebApplicationContext。
在web.xml中提供ContextLoaderListener上下文监听器，在web容器启动时，会触发容器初始化事件，ContextLoaderListener会监听到这个事件，
从而触发ContextInitialized方法完成上下文初始化，这个方法中调用父类ContextLoader的方法完成上下文初始化。ContextLoader类中主要完成三件事：

1）创建WebApplicationContext；
2）加载对应的Spring配置文件中的bean；（refresh方法，完成bean的加载）
3）将WebApplicationContext放入servletContext中。
ContextLoaderListener监听器初始化完之后，开始初始化web.xml中配置的servlet，如DispatcherSevlet
ContextLoaderListener监听器监听的是servletContext，当web容器初始化后，servletContext发生变化时，会触发相应事件。




触发的事件
ContextClosedEvent
ContextRefreshedEvent
ContextStartedEvent
ContextStoopedEvent
RequestHandleEvent
Spring容器启动后会触发ContextRefreshedEvent事件，想要在某个类加载完毕是干某事，但用了Spring管理对象，这个类又引用了其他类，
比较复杂，可以写一个类继承Spring的ApplicationListener监听并监控ContextRefreshedEvent事件。

对于非web程序也是差不多的步骤
1 新建IOC容器 new AnnotationConfigApplicationContext(xx.class);
2 refesh初始化IOC容器

prepareRefresh；准备上下文环境
得到一个beanFactory组件。
预处理 prepareBeanFactory（BeanFactory）
对BeanFactory组件进行后处理 postProcessBeanFactory（BeanFactory）
调用bean工厂的后置处理器 invokeBeanFactoryPostProcessors
Spring bean的生命周期
new->属性注入->Init生命周期初始化方法->初始化回调方法->代理AOP->放入单例池 singletonObject




        public void refresh() throws BeansException, IllegalStateException {
        synchronized (this.startupShutdownMonitor) {
        // Prepare this context for refreshing.
        //容器启动前准备工作，也就是设置容器当前的状态和记录启动开始时间以及初始化资源数据以及验证下我们需要验证的一些资源key是否存在
        prepareRefresh();

      // Tell the subclass to refresh the internal bean factory.
      /**获取一个默认的工厂，这个工厂在我们的构造实例化的时候就一个创建了一个默认的工厂
       * 这个工厂非常重要，我们spring的执行的开始阶段是先暴露一个工厂，这个工厂里面包括了spring
       * 之后执行的所有东西，其中我们的加了组件注解的，比如@Component @Reponsity等都会被扫描到
       * 而扫描到的类，目前还不是Bean，spring扫描到过后将它们变成Bd，一般是AnnotatedBeanDefinition
       * 也就是注解BD,然后将这个BD放入 默认工厂DefaultListableBeanFactory中的bdmap
       */
      ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

      // Prepare the bean factory for use in this context.
      /**
       * 这里是准备工厂：
       * 1.设置BeanDefinition的类加载器
       * 2.设置spring容器默认的类型转换器
       * 3.设置spring解析el表达式的解析器
       * 4.添加一个Bean的后置处理器ApplicationContextAwareProcessor
       * 5.将bean工厂的一些类，比如ApplicationContext直接注册到单例池中
       * 6.去除一些在byType或者byName的时候需要过滤掉的一些bean（spring在依赖注入的时候会先在这些默认注册的bean中进行byType找
       * 如果找到了，就加入到列表中，简单来说就是比如你在bean中依赖注入了ApplicationContext context,那么spring会把默认注册的这些bean
       * 中找到然后进行注册）。
       * 7.将系统的环境信息、spring容器的启动环境信息、操作系统的环境信息直接注册成一个单例的bean
       */
      prepareBeanFactory(beanFactory);

      try {
         // Allows post-processing of the bean factory in context subclasses.
         //这里是一个空壳方法，spring目前还没有对他进行实现，但是我们通过名字postProcessBeanFactory
         //其实后续可以添加一些用户自定义的或者默认的一些特殊的后置处理器工程到beanFactory中去
         //这个方法是留给子类去实现的
         postProcessBeanFactory(beanFactory);

         // Invoke factory processors registered as beans in the context.
         //这里就是调用后置处理器，程序执行到这里为止，还没有添加的有我们用户自定义的后置处理器，但是
         //spring添加了自己默认的后置处理器，比如ConfigurationClassBeanFactory，这个类用来解析和扫描
         //应用组件，比如加了@Compent或者相对的其他组件的类，将它们转成bd放入工厂的bdmap中
         //这里做的事情有：
         // 1.将我们标记为容器单例类扫描成bd放入bdmap
         // 2.处理@Import注解
         //3.如果我们的配置类是@Configuration的，那么会生成这个配置类的CGLIB代理类，如果没有加@Configuration，则就是一个普通Bean
         invokeBeanFactoryPostProcessors(beanFactory);

         // Register bean processors that intercept bean creation.
         /**
          * 上面的一个方法invokeBeanFactoryPostProcessors是将我们系统中所有符合条件的普通类都扫描成了一个BeanDefinition
          * 并且放入到了beanDefinitionMap中，包括业务的bean，ban的后置处理器、bean工厂的后置处理器等等
          * 也就是说所有的BeanDefinition都已经扫描完成了，下面这个方法做的事情就是从beanDefinitionMap中
          * 取出bean的后置处理器然后 放入到后置处理器的缓存列表中
          * 当然了，这里不仅仅去取出后置处理器，还进行了一些排序，具体怎么排序，可以看下里面的操作
          */
         registerBeanPostProcessors(beanFactory);

         // Initialize message source for this context.
         //初始化国际化资源信息

         initMessageSource();

         // Initialize event multicaster for this context.
         //事件注册器初始化
         initApplicationEventMulticaster();

         // Initialize other special beans in specific context subclasses.
         //空壳方法，留给子类实现
         onRefresh();

         // Check for listener beans and register them.
         //将容器中和BeanDefinitionMap中的监听器添加到事件监听器中
         registerListeners();

         // Instantiate all remaining (non-lazy-init) singletons.
         /**
          * 创建单例池，将容器中非懒加载的Bean，单例bean创建对象放入单例池中，包括容器的依赖注入
          */
         finishBeanFactoryInitialization(beanFactory);

         // Last step: publish corresponding event.
         //容器启动过后，发布事件
         finishRefresh();
      }

      catch (BeansException ex) {
         if (logger.isWarnEnabled()) {
            logger.warn("Exception encountered during context initialization - " +
                  "cancelling refresh attempt: " + ex);
         }

         // Destroy already created singletons to avoid dangling resources.
         destroyBeans();

         // Reset 'active' flag.
         cancelRefresh(ex);

         // Propagate exception to caller.
         throw ex;
      }

      finally {
         // Reset common introspection caches in Spring's core, since we
         // might not ever need metadata for singleton beans anymore...
         resetCommonCaches();
      }
}
}









































