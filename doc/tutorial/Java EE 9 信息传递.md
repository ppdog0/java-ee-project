## 消息服务概念

### 什么是信息传递（Messaging）？

消息传递的本质：软件组件或应用程序之间的一种通信方法

消息传递系统

* 本质：peer-to-peer facility
* 每个客户机都连接到一个消息传递代理。通过代理，向其他客户机创建、发送、接收和读取消息

JMS API

* 功能：允许应用程序创建、发送、接收和读取消息
* JMS API 定义了一组通用的接口和相关语义，允许用 Java 编程语言编写的程序与其他消息传递实现进行通信。

* 使得通信具有以下特点：
    * 松耦合的
    * 异步的
    * 可靠的
* 使用 JMS 的情景：
    * 提供者希望组件不依赖于有关其他组件接口的信息，因此可以轻松地替换组件
    * 无论所有组件是否同时启动和运行，提供程序都希望应用程序运行。
    * 应用程序业务模型允许组件向另一个组件发送信息，并在不立即收到响应的情况下继续运行。
* Javaee 平台中的 JMS API 具有以下特性：
    * 应用程序客户机、 EJB 组件和 web 组件可以发送或同步接收 JMS 消息
    * 消息驱动 bean 是一种企业 bean，支持 EJB 容器中消息的异步消费
    * 消息发送和接收操作可以参与 Java 事务 API (JTA)事务，该事务允许 JMS 操作和数据库访问在单个事务中进行。

### 基本的 JMS API 概念

#### JMS API 体系结构

* 一个 JMS provider
    * 是实现 JMS 接口并提供管理和控制特性的消息传递系统
* 若干个 JMS 客户机
    * 是用 Java 编程语言编写的产生和消费消息的程序或组件
* 消息
    * 是在 JMS 客户端之间传递信息的对象
* 管理对象
    * 是为客户端的使用而配置的 JMS 对象
    * 有 2 类：
        * 目的地
        * 连接工厂

JMS API 架构

* 管理工具或注释允许您将目的地和连接工厂绑定到 JNDI 名称空间中
* JMS 客户机可以使用资源注入来访问名称空间中的受管理对象
* 通过 JMS 提供程序建立到相同对象的逻辑连接

![image-20200907161010501](C:\Users\Gwan\AppData\Roaming\Typora\typora-user-images\image-20200907161010501.png)

### 2 种信息传递方式

* 点对点 Point-to-Point
    * 基础概念
        * 消息队列
        * 发送方和接收方
    * 特点
        * 每个消息都被寻址到一个特定的队列，接收客户端从为保存其消息而建立的队列中提取消息。
        * 队列保留发送给它们的所有消息，直到消息被使用或过期。
        * 每条消息只有一个消费者。
        * 无论客户端发送消息时接收方是否正在运行，接收方都可以获取消息。
* 发布/订阅 Publish/Subscribe
    * 特点
        * 每条消息可以有多个消费者。
        * 订阅主题的客户端只能使用在客户端创建订阅之后发送的消息，使用者必须继续处于活动状态，以便使用消息。
        * JMS API 通过允许应用程序创建持久订阅在一定程度上放松了这一要求
            * 持久订阅接收在使用者不活动时发送的消息。

信息的消耗方式

* 同步
    * 消费者通过调用 receive 方法显式地从目的地获取消息
    * receive 方法可以阻塞直到消息到达，或者如果消息没有在指定的时间限制内到达，则可以超时。
* 异步
    * 应用程序客户机或 javase 客户机可以向消费者注册消息侦听器。
        * 消息侦听器类似于事件侦听器
        * 每当消息到达目的地时，JMS 提供者通过调用监听器的 onMessage 方法来传递消息
        * 该方法对消息的内容起作用。在 javaee 应用程序中，消息驱动的 bean 充当消息侦听器(它也有一个 onMessage 方法) ，但是客户机不需要向使用者注册它。

### JMS API 编程模型

![image-20200907162118338](C:\Users\Gwan\AppData\Roaming\Typora\typora-user-images\image-20200907162118338.png)

JMS 应用程序的基本构建块包括：

* 管理对象:

    * 2种

        * 连接工厂
            * 连接工厂是客户端用来创建到提供者的连接的对象
            * 类型：`ConnectionFactory`, `QueueConnectionFactory`, or `TopicConnectionFactory`
            * 在 JMS 客户机程序的开始，通常将连接工厂资源注入到 `ConnectionFactory` 对象中
        * 目的地
            * 是客户端用来指定其生成的消息目标和消息来源的对象
            * 在点对点方式中，称为 queue
            * 在发布/订阅方式中，称为 topic

    * 通常以管理方式而不是编程方式进行维护

        * 使用 GlassFish Server，您可以使用 asadmin create-JMS-resource 命令或管理控制台以连接器资源的形式创建 JMS 管理对象。您还可以在名为 glassfish-resources.xml 的文件中指定可以与应用程序绑定的资源。

    * 在 javaee 应用程序中，JMS 管理对象通常放在 `jms` 命名子上下文中

    * ```oac_no_warn
        @Resource(lookup = "jms/MyQueue")
        private static Queue queue;
        
        @Resource(lookup = "jms/MyTopic")
        private static Topic topic;
        ```

* 连接

    * 连接封装了与 JMS 提供者的虚拟连接
    * 在 javaee 平台中，从单个连接创建多个会话的能力仅限于应用程序客户机。在 web 和企业 bean 组件中，一个连接只能创建一个会话。
    * 通过创建 `JMSContext` 对象来创建连接

* 会话

    * 会话是用于生成和消费消息的单线程上下文

* `JMSContext` 对象

    * 它将连接和会话结合在一个对象中

    * 通常通过创建 JMSContext 对象来创建会话(以及连接)

    * 使用 JMSContext 创建以下对象:

        - 信息生产者
        - 消息消费者
        - 信息
        - 队列浏览器
        - 临时队列和主题(请参阅创建临时目的地)

    * 可以在 try-with-resources 块中创建 JMSContext

    * 要创建 JMSContext，请在连接工厂上调用 createContext 方法:

        ```oac_no_warn
        JMSContext context = connectionFactory.createContext();
        ```

* 信息生产者

    * 消息生成器是由 JMSContext 或会话创建的对象

    * 概念：将消息发送到目标

    * ```oac_no_warn
        try (JMSContext context = connectionFactory.createContext();) {
            JMSProducer producer = context.createProducer();
            ...
        ```

    * ```oac_no_warn
        context.createProducer().send(dest, message);
        ```

* 消息消费者

    * 消息使用者是由 JMSContext 或会话创建的对象，用于接收发送到目标的消息

    * ```oac_no_warn
        try (JMSContext context = connectionFactory.createContext();) {
            JMSConsumer consumer = context.createConsumer(dest);
            ...
        ```

    * ```oac_no_warn
        Message m = consumer.receive();
        Message m = consumer.receive(0);
        ```

        * 使用 receive 方法以同步方式使用消息

        * 如果没有指定任何参数或0的参数，则该方法将无限期地阻塞，直到消息到

        * 使用带超时的同步接收: 调用超时参数大于0的接收方法。一秒是推荐的超时值:

            ```oac_no_warn
            Message m = consumer.receive(1000); // time out after a second
            ```

        * 要从应用程序客户机或 javase 客户机启用异步消息传递，您可以使用消息侦听器

* 消息

    * 消息头
    * 属性
    * 消息体

其他：

* JMS 消息侦听器

    * 本质：对象

    * 功能：异步事件处理器

    * 实现了 `MessageListener` 接口

        * 包含一个方法：`onMessage`

    * 在客户机，使用 `setMessageListener` 设置监听器

        ```oac_no_warn
        Listener myListener = new Listener();
        consumer.setMessageListener(myListener);
        ```

* JMS 消息选择器

    * 功能：过滤接收到的消息

* 消费来自主题的消息

    * 订阅可以是：
        * 持久的
        * 非持久的
            * 只有在订阅上有活动消费者时，非持久订阅才存在。这意味着只有在使用者存在且未关闭时，发送到主题的任何消息才会添加到订阅中。
        * 共享的
            * 订阅中的每条消息只传递给一个使用者
        * 非共享的
            * 非共享订阅仅限于单个使用者
            * 在这种情况下，订阅中的所有消息都传递给该使用者
    * 订阅可以被认为是 JMS 提供者本身中的一个实体，而使用者是应用程序中的 JMS 对象。
    * 除非指定了消息选择器，否则订阅将接收在创建订阅后发送到主题的每条消息的副本
    * 如果指定了消息选择器，则只有属性与消息选择器匹配的消息才会添加到订阅中。







## Java 消息服务示例

## Java EE 平台的安全性介绍

## 开始保护网络应用

## 企业应用安全入门

## 使用 Java EE 安全 API

## Java EE Security: Advanced Topics