## Enterprise Beans

本质：封装应用程序业务逻辑的服务器端组件

优点：

* 简化了大型分布式应用程序的开发
* 由于 bean 不是客户机包含应用程序的业务逻辑，所以客户机开发人员可以专注于客户机的渲染
* 由于企业 bean 是可移植组件，应用程序组装程序可以从现有 bean 构建新的应用程序

 使用企业 bean 的时机：

* 应用程序必须是可伸缩的
* 事务必须确保数据完整性。企业 bean 支持事务，即管理共享对象并发访问的机制
* 该应用程序将有各种各样的客户端

企业 bean 的 2 种类型：

* Session
    * 为客户端执行任务
    * （可选）实现 web 服务
* Message-driven
    * 充当特定消息传递类型的侦听器

### 什么是 Session Bean

功能：封装了业务逻辑

可被谁调用？

* 本地、远程或 web 服务客户机视图

特点：

* 会话 bean 不是持久的(也就是说，它的数据不会保存到数据库中)

类型：

* 有状态 Session Bean

    * 对象的状态由其实例变量的值组成，这些实例变量表示会话的状态
    * 这种状态通常称为会话状态
    * 会话 bean 不是共享的; 它只能有一个客户机
    * 适用场景：
        * Bean 的状态表示 bean 和特定客户机之间的交互。
        * Bean 需要跨方法调用保存有关客户机的信息
        * Bean 在客户机和应用程序的其他组件之间进行中介，向客户机提供一个简化的视图
        * 在幕后，bean 管理几个企业 bean 的工作流

* 无状态 Session Bean

    * 无状态会话 bean 不维护与客户机的会话状态
    * 当客户机调用无状态 bean 的方法时，bean 的实例变量可能包含特定于该客户机的状态，但仅限于调用期间。
    * 当方法完成时，特定于客户端的状态不应保留。
    * 无状态会话 bean 可以实现 web 服务，但有状态会话 bean 不能
    * 通常，应用程序比有状态会话 bean 需要更少的无状态会话 bean 来支持相同数量的客户机。
    * 适用场景
        * Bean 的状态没有特定客户机的数据。
        * 在单个方法调用中，bean 为所有客户机执行一个通用任务。例如，您可以使用无状态会话 bean 发送确认在线订单的电子邮件。
        * 这个 bean 实现了一个 web 服务

* 单例 Session Bean

    * 单例会话 bean 是为单个企业 bean 实例跨客户机共享和并发访问而设计的
    * 单例会话 bean 提供了与无状态会话 bean 类似的功能，但与之不同的是，每个应用程序只有一个单例会话 bean，而不是一个无状态会话 bean 池

    * 适用场景
        * 状态需要在应用程序之间共享。
        * 单个企业 bean 需要由多个线程并发访问。
        * 应用程序需要一个企业 bean 在应用程序启动和关闭时执行任务。
        * 这个 bean 实现了一个 web 服务。

### 什么是消息驱动的 Bean？

本质：一个企业 bean

功能：它允许 javaee 应用程序异步处理消息

这种类型的 bean 通常充当 JMS 消息侦听器，它类似于事件侦听器，但接收 JMS 消息而不是事件。

* 消息可以由任何 javaee 组件(应用程序客户机、另一个企业 bean 或 web 组件)或不使用 javaee 技术的 JMS 应用程序或系统发送。
* 消息驱动 bean 可以处理 JMS 消息或其他类型的消息。

消息驱动的 bean 与会话 bean 的不同之处：

* 客户机不通过接口访问消息驱动 bean
* 消息驱动 bean 只有一个 bean 类
* 在几个方面，消息驱动的 bean 类似于无状态会话 bean
    * 消息驱动 bean 的实例不保留特定客户机的数据或会话状态
    * 消息驱动 bean 的所有实例都是等效的，允许 EJB 容器将消息分配给任何消息驱动 bean 实例。容器可以共享这些实例，以允许同时处理消息流。
    * 一个消息驱动的 bean 可以处理来自多个客户机的消息

消息驱动 bean 实例的实例变量可以在处理客户端消息时包含一些状态，例如

* JMS API 连接
* 开放数据库连接
* 对企业 bean 对象的对象引用

客户端组件不会定位消息驱动的 bean 并直接在它们上面调用方法。相反，客户机通过向消息目的地发送消息来访问消息驱动的 bean

* 消息驱动的 bean 类是 MessageListener
* 在部署期间，可以使用 GlassFish Server 资源分配消息驱动 bean 的目的地。

### 访问企业 bean

客户机访问企业 bean 的 2 种途径：

* 通过无接口视图
    * 企业 bean 的无接口视图向客户机公开企业 bean 实现类的 public 方法
* 通过业务接口
    * 业务接口是一个标准的 Java 编程语言接口，其中包含企业 bean 的业务方法

在客户端中使用企业 bean 的 2 种途径：

* 通过 `注解`的依赖注入

* 通过 JNDI 查找

    * 3 个命名空间

        * `java:global`：查找远程企业 beans

            * ```oac_no_warn
                java:global[/application name]/module name /enterprise bean name[/interface name ]
                ```

        * `java:module`：查找本地企业 bean

            * ```oac_no_warn
                java:module/enterprise bean name/[interface name]
                ```

        * `java:app`：查找打包在同一应用程序中的本地企业 bean

            * ```oac_no_warn
                java:app[/module name]/enterprise bean name [/interface name]
                ```

    * 例如，如果一个企业 bean `MyBean` 被打包在 web 应用程序归档文件 `myApp.war` 中，那么模块名就是 myApp。可移植的 JNDI 名称是 `java: module/MyBean`。使用 `java: global` 名称空间的等效 JNDI 名称是 `java: global/myApp/MyBean`

### 决定远程或本地访问

* 相关 bean 的紧耦合或松耦合
* 客户机类型
* 组件分发
* 性能

如果您不确定企业 bean 应该具有哪种类型的访问，请选择远程访问。这个决定给了你更多的灵活性。将来，您可以分发组件以适应应用程序上不断增长的需求。

（不常见）企业 bean 同时允许远程和本地访问是可能的

* 通过 `@Remote` 或 `@Local` 注释显式地指定为业务接口
* 如果 bean 类没有使用 `@Remote` 或 `@Local` 指定接口，业务接口默认为本地接口。

#### 本地客户端

特点：

* 它必须运行在与它访问的企业 bean 相同的应用程序中
* 它可以是一个 web 组件或另一个企业 bean
* 对于本地客户机，它访问的企业 bean 的位置是不透明的。

构建只允许本地访问的企业 bean的途径：

* 创建一个不实现业务接口的企业 bean 实现类，指示 bean 向客户端公开一个无接口视图。

    ```oac_no_warn
    @Session
    public class MyBean { ... }
    ```

* 将企业 bean 的业务接口注释为 `@Local` 接口

    ```oac_no_warn
    @Local
    public interface InterfaceName { ... }
    ```

* 通过将 bean 类装饰为@local 来指定接口，并指定接口名称

    ```oac_no_warn
    @Local(InterfaceName.class)
    public class BeanName implements InterfaceName  { ... }
    ```

使用无接口视图访问本地企业 bean 的 2 种方法：

* 要通过依赖注入获得对企业 bean 无接口视图的引用，请使用 `javax.ejb.EJB` 注释并指定企业 bean 的实现类

    ```oac_no_warn
    @EJB
    ExampleBean exampleBean;
    ```

* 要通过 JNDI 查找获得对企业 bean 的无接口视图的引用，请使用 `javax.naming.InitialContext` 接口的 `lookup` 方法:

    ```oac_no_warn
    ExampleBean exampleBean = (ExampleBean)
            InitialContext.lookup("java:module/ExampleBean");
    ```

* 注意：客户端不使用 `new` 操作符来获取使用无接口视图的企业 bean 的新实例。

#### Web 服务客户端

Web 服务客户端可以通过 2 种方式访问 javaee 应用程序:

* 客户机可以访问使用 JAX-WS 创建的 web 服务
* web 服务客户机可以调用无状态会话 bean 的业务方法
    * 注意：Web 服务客户端无法访问消息 bean

### 企业 Bean 的内容

要开发企业 bean，必须提供以下文件：

* Enterprise bean 类: 实现企业 bean 的业务方法和任何生命周期回调方法
* 业务接口: 定义由企业 bean 类实现的业务方法。如果企业 bean 公开本地的无接口视图，则不需要业务接口
* Helper 类: 企业 bean 类所需的其他类，例如 exception 和 utility 类

### 企业 bean 的命名约定

![image-20200907133907291](C:\Users\Gwan\AppData\Roaming\Typora\typora-user-images\image-20200907133907291.png)

### 企业 bean 的生命周期

每种类型的企业 bean (有状态会话、无状态会话、单例会话或消息驱动)都有不同的生命周期

* 有状态会话 Bean 的生命周期
    * ![image-20200907135220141](C:\Users\Gwan\AppData\Roaming\Typora\typora-user-images\image-20200907135220141.png)
    * 您的代码只控制一个生命周期方法的调用: 带注释的@remove 方法。图35-2中的所有其他方法都是由 EJB 容器调用的
    * 在准备阶段，EJB 容器可能决定通过将 bean 从内存移动到二级存储来停用或钝化 bean

* 无状态或单例会话 Bean 的生命周期
    * ![image-20200907135330792](C:\Users\Gwan\AppData\Roaming\Typora\typora-user-images\image-20200907135330792.png)
    * EJB 容器通常创建和维护一个无状态会话 bean 池，从而开始无状态会话 bean 的生命周期。容器执行任何依赖注入，然后调用带注释的@postconstruct 方法，如果它存在的话。Bean 现在已经准备好让客户机调用其业务方法。在生命周期的末尾，EJB 容器调用带注释的@predestroy 方法(如果存在的话)。然后就可以对 bean 的实例进行垃圾收集了。
    * 与无状态会话 bean 一样，单例会话 bean 从不进行钝化；只有两个阶段：不存在 & 为业务方法的调用做好了准备

* 消息驱动 Bean 的生命周期
    * ![image-20200907135527603](C:\Users\Gwan\AppData\Roaming\Typora\typora-user-images\image-20200907135527603.png)
    * EJB 容器通常创建一个消息驱动的 bean 实例池
        * 如果消息驱动的 bean 使用依赖注入，则容器在实例化实例之前注入这些引用
        * 容器调用带注释的 `@PostConstruct` 方法(如果有的话)







 

## 开始使用Enterprise bean



## 运行Enterprise Bean 示例



## 使用嵌入式Enterprise Bean 容器



## 在Session bean 中使用异步方法调用