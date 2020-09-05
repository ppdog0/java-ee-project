### 术语杂货铺

* JCP: Java Community Process

* JSR: Java Specification Requests

* EJB: Enterprise JavaBeans
* JMS: Java Message Service
* 耦合：相互绑定
* 紧耦合：类与类绑定在一起
* 松耦合：类与接口绑定在一起
* 解耦：让耦合从紧到松的过程
* 向上转型：把一个对象赋值给自己的接口或父类变量
* 注入(inject)：通过方法或构造函数把一个对象传递给另一个对象
* 装配(Assemble): 和上述注入是一个意思,看个人喜好使用
* 工厂(Factory): 如果一个类或对象专门负责创建(new) 对象,这个类或对象就是工厂
* 容器(Container): 专门负责存放创建好的对象的东西. 可以是个Hash表或 数组.
* 面向接口编程(Interface based programming) 



多层应用 (Multitiered Applications) 运行在不同的机器上

* Client Tier
    * Web Client
        * 动态页面
        * 多种标记语言
        * 由浏览器呈现
    * Application Client
    * Applets
        * 用 Java 写的一个小型 client application，在浏览器的 JVM 中运行
        * 要求 client 系统安装 Java 插件和安全策略文件
* Web Tier
* Business Tier
* EIS Tier



### JavaBeans

**JavaBeans**是[Java](https://zh.wikipedia.org/wiki/Java)中一种特殊的[类](https://zh.wikipedia.org/wiki/类_(计算机科学))，可以将多个[对象](https://zh.wikipedia.org/wiki/对象_(计算机科学))封装到一个对象（bean）中。特点是可[序列化](https://zh.wikipedia.org/wiki/序列化)，提供[无参构造器](https://zh.wikipedia.org/w/index.php?title=无参构造器&action=edit&redlink=1)，提供[getter方法和setter方法](https://zh.wikipedia.org/w/index.php?title=赋值方法&action=edit&redlink=1)访问对象的属性。名称中的“Bean”是用于Java的可重用软件组件的惯用叫法

#### 优点：

- Bean可以控制它的属性、事件和方法是否暴露给其他程序。
- Bean可以接收来自其他对象的事件，也可以产生事件给其他对象。
- 有软件可用来配置Bean。
- Bean的属性可以被序列化，以供日后重用。

#### 规范

要成为JavaBean[类](https://zh.wikipedia.org/wiki/類_(計算機科學))，则必需遵循关于命名、构造器、方法的特定规范。有了这些规范，才能有可以使用、复用、替代和连接JavaBeans的工具。

规范如下：

- 有一个public的无参数构造器。
- 属性可以通过*get*、*set*、*is*（可以替代get，用在布尔型属性上）方法或遵循特定命名规范的其他方法访问。
- 可序列化。



### Servlet

**Servlet**（Server Applet），全称**Java Servlet**，未有中文译文。是用[Java](https://zh.wikipedia.org/wiki/Java)编写的[服务器](https://zh.wikipedia.org/wiki/服务器)端[程序](https://zh.wikipedia.org/wiki/程序)。其主要功能在于交互式地浏览和修改数据，生成动态[Web](https://zh.wikipedia.org/wiki/Web)内容。狭义的Servlet是指Java语言实现的一个[接口](https://zh.wikipedia.org/wiki/接口)，广义的Servlet是指任何实现了这个Servlet接口的[类](https://zh.wikipedia.org/wiki/类_(计算机科学))，一般情况下，人们将Servlet理解为后者。

Servlet运行于支持Java的[应用服务器](https://zh.wikipedia.org/wiki/应用服务器)中。从实现上讲，Servlet可以响应任何类型的请求，但绝大多数情况下Servlet只用来扩展基于[HTTP](https://zh.wikipedia.org/wiki/HTTP)[协议](https://zh.wikipedia.org/wiki/协议)的[Web服务器](https://zh.wikipedia.org/wiki/Web服务器)。

最早支持Servlet标准的是JavaSoft的Java Web Server。此后，一些其它的基于Java的Web服务器开始支持标准的Servlet。

#### 工作模式

- 客户端发送请求至服务器
- 服务器启动并调用Servlet，Servlet根据客户端请求生成响应内容并将其传给服务器
- 服务器将响应返回客户端
- 其他

### Applet

**Applet**或**Java小应用程序**是一种在[Web](https://zh.wikipedia.org/wiki/Web)环境下，运行于[客户端](https://zh.wikipedia.org/wiki/客户端)的[Java](https://zh.wikipedia.org/wiki/Java)程序组件。

通常，每个Applet的功能都比较单一（例如仅用于显示一个舞动的Logo），因此它被称作“小应用程序”[1](https://zh.wikipedia.org/wiki/Java_applet#注释)。

Applet必须运行于某个特定的“容器”，这个容器可以是[浏览器](https://zh.wikipedia.org/wiki/浏览器)本身，也可以是通过各种[插件](https://zh.wikipedia.org/wiki/插件)，或者包括支持Applet的移动设备在内的其他各种程序来运行。与一般的Java[应用程序](https://zh.wikipedia.org/wiki/应用程序)不同，Applet不是通过main方法来运行的。在运行时Applet通常会与用户进行互动，显示动态的画面，并且还会遵循严格的安全检查，阻止潜在的不安全因素（例如根据[安全策略](https://zh.wikipedia.org/wiki/安全策略)，限制Applet对客户端文件系统的访问）。

* Web Components
    * servlets
    * 使用 JavaServer Faces 或 JSP 创建的网页





### Container

容器 是 组件 和 底层工具 之间的接口

#### 作用

* 在执行容器之前，必须将一个 web, enterprise bean 或 application client 组件装配到一个 Java EE 模块中。在装配过程中，需要设置好 Java EE 应用及其组件

* 管理那些非构建性的服务，比如：enterprise bean and servlet lifecycles, database connection resource pooling, data persistence, and access to the Java EE platform APIs

#### 种类

![Diagram of client-server communication showing servlets and web pages in the web tier and enterprise beans in the business tier.](https://javaee.github.io/tutorial/img/javaeett_dt_005.png)

* Java EE server
    * 提供：
        * EJB container
            * 管理 enterprise beans 的执行
        * Web container
            * 管理 web pages, servlets, 和一些 EJB components 的执行
        * Application client container
            * 管理运行在 client 的 application clients 组件的执行
        * Applet container
            * 管理 applet 的执行



### Web Services Support

Web 服务，是使用了 XML 标准和传输协议的、基于 web 的 enterprise applications。

Java EE 平台提供 XML APIs 和工具来快速处理

用 Java EE XML APIs 写 Web 服务，只需要向方法传递参数并处理返回的数据

### SOAP Transport Protocol

Simple Object Access Protocol

功能：客户请求 和 web服务响应 都通过 SOAP信息 进行传输

特点：

* SOAP 基于 XML
* 采用 HTTP 的请求-响应模型





### Java EE 应用的装配(Assembly)和部署(Deployment)

一个 Java EE 程序被打包为若干个可以部署到任意 Java EE 平台的标准单元。

每个单元包含：

* 一个功能性组件，比如：an enterprise bean, web page, servlet, or applet
* 一个可选的 deployment descriptor，用于描述其内容

一旦 Java EE 单元制造完毕，就可以部署。

部署，需使用各个平台自己的部署工具，来指定与平台自身相关的信息

一旦部署完毕，就可以运行程序。



## Java EE APIs

Java EE 平台所用的技术

### EJB

* Enterprise JavaBeans，也称为 enterprise bean
* 其实就是业务代码，干活用的
* 分为 2 类
    * session bean
        * 特点：转瞬即逝 (transient)。只要 client 结束执行，session bean 和 它的数据 就消失
    * message-driven bean
        * 特点：允许一个 business component 异步接收信息。通常是 JMS 信息
        * 结合了 session bean 和 message listener 的特点

### Java Servlet

Servlet 技术使得可以定义 HTTP特定 的 servlet 类

servlet 类扩展了服务器的功能，而不局限于传统的 请求-响应 编程模型

### JavaServer Faces

一个用于构建 web applications 的用户接口框架

主要部件：

* GUI
* 一个可以代理HTML及其它标记语言各个部分的灵活模型
* 一个标准的 RenderKit，用于创建 HTML 4.01 标记

### JSP

JavaServer Pages

是一种动态网页技术标准

部署在网络服务器上

可以响应客户端发送的请求，并根据请求内容动态地生成[HTML](https://zh.wikipedia.org/wiki/HTML)、[XML](https://zh.wikipedia.org/wiki/XML)或其他格式文档的[Web](https://zh.wikipedia.org/wiki/Web)网页，然后返回给请求者

将Java代码和特定变动内容嵌入到静态的页面中，实现以静态页面为模板，动态生成其中的部分内容。

特点：

* 能以模板化的方式简单、高效地添加动态网页内容
* 可利用[JavaBean](https://zh.wikipedia.org/wiki/JavaBean)和标签库技术复用常用的功能代码
* 可与其它企业级Java技术相互配合，JSP可以只专门负责页面中的数据呈现

### JSTL

JavaServer Pages Standard Tag Library

JSTL 是由 JCP 制定的标准规范，是一个标准通用的标签库，开发人员利用它取代 JSP 页面上的 Java 代码

### JPA

Java Persistence API

JPA 通过 对象/关系映射 的方式来搭起面向对象模型和关系型数据库的桥梁

JPA 包含 3 个方面：

* Java Persistence API 本身，定义在 javax.persistence 包内
* Java持久化查询语言 (JPQL)
* 对象/关系 元数据

### JTA

Java Transaction API

功能：

* 划分事务的边界
* X/Open XA API允许资源参与到事务中。

Java事务API由三个部分组成：

- UserTransaction - 高层的应用事务划分接口，供客户程序使用
- TransactionManager - 高层的事务管理器接口，供应用服务器使用
- XAResource，X/Open XA协议的标准Java映射，供事务性资源管理器使用。

### JAX-RS

Java API for RESTful Web Services

一个 JAX-RS 应用是一个 web 应用，它的类和所需的库打包成一个 servlet 在一个 WAR 文件中，

### Managed Beans

Managed Beans 是一个由 JSF 框架管理的 Java bean

是 lightweight container-managed objects (POJOs)

需求最小化，支持最基本的操作

### CDI

Contexts and Dependency Injection for Java EE

是一套组件管理服务（component management services）

通过 依赖注入，允许不同层之间的组件松耦合联系在一起

### Bean Validation

Bean Validation 定义了一个元数据模型和 API，用于验证 JavaBeans 组件的数据

优点：将以前分散在各个层的验证工作收归到一起

### JMS

Java Message Service API

是一个通信标准，允许 Java EE 应用组件对消息进行创建、发送、接收、读取

允许松耦合、可靠、异步的分布式通信

### JCA

Java EE Connector Architecture

功能：创建 resource adapter(RA)，从而访问任何可以加入到 Java EE 产品的企业信息系统，进而访问资源

### JavaMail API

功能：发送邮件提示

分为 2 部分：

* 一个应用层的接口，被应用组件用于发邮件
* 一个服务提供者接口

### JACC

Java Authorization Contract for Containers

JACC 提供了一个位于 Java EE application server 和 authorization policy provider 之间的契约

### JASPIC

Java Authentication Service Provider Interface for Containers

### Java EE Security API

提供了：

* 便携式、插入式接口，用于HTTP验证、身份存储
* injectable SecurityContext interface that provides an API for programmatic security

HttpAuthenticationMechanism 接口：

* 可用于验证 web applications 的调用者

IdentityStore 接口

* 可用于验证 user credentials(用户凭证) 和 检索组信息

### Java API for WebSocket

WebSocket 是一个应用层协议，在 TCP 之上提供了全双工通信

### Java API for JSON Processing

也称为 JSON-P

功能：使用 对象模型 或 流模型，对 JSON 数据进行解析、转换和查询

包括：

* JSON Pointer
    * 从指定的JSON文档中，提取出值
    * 将值转换为 JSON 文档
* JSON Patch
* JSON Merge Patch

### Java API for JSON Binding

JSON-B

实现 Java 对象 和 JSON 信息之间的转换

### Concurrency Utilities for Java EE

### Batch Applications for the Java Platform



## 来自 Java SE 的 APIs

### JDBC

Java Database Connectivity

功能：访问数据库

JDBC API 包含 2 部分：

* 一个应用层接口，被应用组件使用，用于访问数据库
* 一个 service provider 接口，用于将一个 JDBC 驱动连接到 Java EE 平台

### JNDI

Java Naming and Directory Interface

功能：使得应用可以访问多个 naming 和 directory 服务，比如 LDAP, DNS, 和 NIS

### JAF

The JavaBeans Activation Framework

JAF 由 JavaMail API 使用

功能：

* 确定任意数据的类型
* 封装对该数据的访问方法
* 发现允许对它的操作
* 创建合适的 JavaBeans 组件来执行这些操作

### JAXP

Java API for XML Processing

功能：解析 or 转换 XML 文档，独立于某种 XML-处理实现

使用：

* DOM: Document Object Model
* SAX: Document Object Model
* XSLT: Extensible Stylesheet Language Transformations

### JAXB

Java Architecture for XML Binding

功能：将一个 XML schema 绑定到 Java 语言程序的一种展现形式

### JAX-WS

Java API for XML Web Services

功能：使用 JAXB API，将 XML 数据绑定到 Java 对象

### SAAJ

SOAP with Attachments API for Java

是一个底层 API

JAX-WS 依赖 SAAJ

大多数开发者不使用 SAAJ，而是使用高级的 JAX-WS

### JAAS

Java Authentication and Authorization Service

功能：验证与授权一个或一组用户来运行

JAAS 是一个 Java 版本的 PAM 框架

### Common Annotations for the Java Platform

注解 提供了一种声明式的编程风格





## GlassFish Server 工具

* Administration Console
    * GUI 管理工具
* asadmin
    * CLI 管理工具
* appclient
    * CLI 工具
    * 发布应用程序容器
    * 调用打包在应用客户 JAR 文件中的客户程序
* capture-schema
    * CLI
    * 从数据库中提取 schema 信息
    * 产生 schema 文件，GlassFish Server 可以用它在容器管理的持久化中
* package-appclient
    * CLI 工具
    * 打包应用程序容器的库和 JAR 文件
* Apache Derby
    * Apache Derby 数据库的一个拷贝
* xjc
    * CLI 工具
    * 将一个源 XML schema 转换到一组 JAXB 内容类
* schemagen
    * CLI 工具
    * 为 java 文件中引用的每个 namespace 创建一个 schema 文件
* wsimport
    * CLI 工具
    * 为一个 WSDL 文件产生一个 JAX-WS portable artifacts
* wsgen
    * CLI  工具
    * 阅读 web service endpoint class
    * 产生 Web service 部署和调用所需要的 JAX-WS portable artifacts



## GlassFish

domain：一个 domain 是由一个管理服务器进行管理的 GlassFish Server 实例

* 每个 domain 都有：
    * GlassFish Server 端口号，默认 8080
    * 管理服务器的端口号，默认 4848
    * 管理员名字和密码，默认名字为 admin，默认密码为空

GlassFish Server 包括 Apache Derby

### Maven 应用目录结构 

每个应用模块的结构：

* `pom.xml`: Maven build 文件
* `src/main/java`: 该模块的 java 源文件
* `src/main/resources`: 该模块的配置文件（web applications 除外）
* `src/main/webapp`: web pages, style sheets, tag files, 和 images (仅限于 web applications)
* `src/main/webapp/WEB-INF`:  web applications 的配置文件(仅限于 web applications )

当一个程序由多个 application modules 打包在一个 EAR 文件时，它的子模块目录遵循以下命名原则：

* example-name\`-app-client\`: application clients
* example-name\`-ejb\`: enterprise bean JAR files
* example-name\`-war\`: web applications
* example-name\`-ear\`: enterprise applications
* example-name\`-common\`: library JAR containing components, classes, and files used by other modules

### Java EE Maven Archetypes

Archetypes 是用于生成一个特定 Maven 项目的模板





## Debug

使用 Server Log

* 右键服务器节点，选择 view log files

使用 debugger





