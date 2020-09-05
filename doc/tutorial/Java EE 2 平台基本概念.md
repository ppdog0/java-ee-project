# Java EE 平台基础知识



## 资源创建

### 资源和 JNDI Naming

在一个分布式应用中，一个组件需要访问其它组件

Java EE 平台中，JNDI 使得组件可以定位其它组件和资源

* 资源
    * 是一个程序对象
    * 提供连接到系统的服务
    * 每个资源都有一个 JNDI 名字
    * 管理员可以使用控制台或 asadmin 在 JNDI namespace 中创建资源
    * application 使用 annotations 来注入资源
    * 一个资源对象及其 JNDI 名字通过 JNDI 服务绑定到一起
        * 通过使用 @Resource 注解的方式，注入资源

### DataSource 对象和连接池

使用 JDBC API 来访问数据库

DataSource 对象

* 包括：数据库服务器的位置，数据库名字，等等信息

* 可以视作一个制造连接的工厂，连接到特定的数据源

JDBC 连接池

* 一组可以重复利用的、针对某一个数据库的连接



## 注入

### 资源注入

功能：允许你将任何在 JNDI 中可用的资源注入到任何一个由容器管理的对象中

* field-based injection
* method-based injection

### 依赖注入

功能：将普通的类转化为受管理的对象，并将它们注入到任意一个受管理的对象

依赖注入定义了 scopes，进而定义了所注入对象的生命期

### 资源注入VS 依赖注入

![image-20200901112728262](C:\Users\Gwan\AppData\Roaming\Typora\typora-user-images\image-20200901112728262.png)



## 打包

### 对应用进行打包

一个 Java EE 应用可以打包到以下任意一种文件中：

* JAR: Java Archive
* WAR: Web Archive
* EAR: Enterprise Archive

WAR 和 EAR 都是标准JAR文件的扩展

一个 EAR 文件包括：

* Java EE 模块
    * 一个模块包含多个组件（通常都有相同的容器类型和该类型对应的 deployment descriptors）
    * 有以下 4 种类型：
        * EJB 模块
            * 包含：
                * EJB 的 class 文件
                * （可选）一个 EJB deployment descriptor
            * 被打包为一个 JAR 文件
        * Web 模块
            * 包含：
                * servlet class 文件
                * web 文件
                * supporting class 文件
                * GIF 和 HTML 文件
                * （可选）一个web deployment descriptor
            * 被打包为一个 WAR 文件
        * Application client 模块
            * 包含：
                * class 文件
                * （可选）一个 application client deployment descriptor
            * 被打包为一个 JAR 文件
        * Resource adapter 模块
            * 包含：
                * 所有的 Java 接口、类和原生库
                * （可选）resource adapter deployment descriptor
            * 实现了 Connector architecture
            * 被打包为一个 JAR 文件(.rar 后缀)
* （可选）deployment descriptors
    * 是一个 XML 文件
    * 描述了部署设置
    * 由于是声明式的，所以无需改动源码
    * 与部署相关的信息通常以注解的形式写在源码中，但如果使用了 deployment descriptor，那么就优先采用后者

### 对 Enterprise Beans 进行打包

* 将 Enterprise Beans 打包成 EJB JAR 模块
    * 为了装配一个Java EE 程序，需要将若干个模块（比如 EJB JAR文件）打包到一个 EAR 文件中。这个 EAR 文件，就是装载应用的文件。
    * 部署这个 EAR  文件时，还需要部署 enterprise bean 到 GlassFish 服务器上
* 将 Enterprise Beans 打包成 WAR 模块
    * 将 enterprise bean 打包在一个 web application 的 WAR 模块中，可以简化部署和程序的组织
    * Enterprise Beans 与 Java 类打包在一个 WAR 模块中，或在 WAR 模块的一个 JAR 文件中

### 对 Web Archives 进行打包

Web 模块是最小的可部署的和可用的 web 资源单元

一个 web 模块

* 包括：
    * 组件
    * 静态 web 内容文件（比如图片），成为 web 资源
    * 服务器侧的 Utility classes
    * 客户端侧的 类
* 结构：
    * web 模块的顶层目录是一个 document root，用于存放 XHTML 页面，客户端侧的类和 archives 和 静态 web 资源
    * document root 包含一个叫做 WEB-INF 的子目录，包括：
        * classes 目录。存放服务器侧的类
        * lib 目录。存放包含 enterprise beans 和由服务器侧的类所调用的库的 JAR archives 
        * deployment descriptors，比如：web.xml 和 ejb-jar.xml

### 对 Resource Adapter Archives 进行打包

一个 RAR 文件存储以下文件：

* XML 文件
* Java 类
* JCA 应用所使用的其它对象

一个 RAR 文件可以被包含在一个 EAR 文件中，也可以单独存在

