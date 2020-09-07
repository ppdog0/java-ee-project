## Web 服务简介

### Web 服务的类型

在概念层面上：

* 服务是通过网络可访问端点提供的基于组件的软件工程服务
* 服务使用者和提供者使用消息以自包含文档的形式交换调用请求和响应信息

在技术层面上，web 服务可以通过多种方式实现，主要有 2 种：

* “大”web 服务
    * **JAX-WS**
    * 使用遵循简单对象访问协议(SOAP)标准的 XML 消息
        * 这是一种定义消息体系结构和消息格式的 XML 语言
    * 使用 Web 服务描述语言(Web Services Description Language，WSDL)
        * 这是一种 XML 语言，用于在语法上定义接口。
    * 基于 soap 的设计必须包括以下元素：
        * 一个正式的契约，来描述 web 服务提供的接口
        * 体系结构必须处理复杂的非功能需求
        * 体系结构需要处理异步处理和调用
* “ RESTful” web 服务
    * **JAX-RS**
    * REST 非常适合于基本的、特殊的集成场景
    * RESTful web 服务通常比基于 soap 的服务更好地与 HTTP 集成，它不需要 XML 消息或 WSDL 服务-api 定义
    * 满足以下条件时，可以采用 RESTful 设计
        * Web 服务是完全无状态的
        * 可以利用缓存基础结构来提高性能
        * 服务生产者和服务消费者对传递的上下文和内容有相互理解

### 决定使用哪种类型的 Web 服务

* 对于通过 web 进行集成的，使用 RESTful
* 对于具有高级服务质量(QoS)要求的企业应用程序集成场景，使用 big web service





## 使用 JAX-WS 构建 Web 服务

web 服务操作调用由基于 xml 的协议(如 SOAP)表示

### **JAX-WS Web 服务和客户机之间的通信**

开发 JAX-WS web 服务的起点是使用 `javax.jws.WebService` 注解的 Java 类

使用了 `@WebService` 的类，是一个 `web 服务端点`

* 功能：声明客户机可以在服务上调用的方法

* 服务端点接口是一个 Java 接口

* 服务端点实现(SEI) 是一个 Java 类

    * 必须使用 `javax.jws.WebService` 或者 `javax.jws.WebServiceProvider` 注解

    * 实现类可以通过`@WebService` 注释的 `endpointInterface` 元素显式引用 SEI。但这不是必需的，如果没有指定，则隐式定义。

    * 实现类的业务方法必须是 public，不能是 static 或 final

    * 实现类不能声明为 final，也不能是 abstract

    * 实现类不能定义 finalize 方法

    * 向 web 服务客户机公开的业务方法必须使用 `javax.jws.WebMethod` 注解

    * ```oac_no_warn
        package javaeetutorial.helloservice;
        
        import javax.jws.WebService;
        import javax.jws.WebMethod;
        
        @WebService
        public class Hello {
            private final String message = "Hello, ";
        
            public Hello() {
            }
        
            @WebMethod
            public String sayHello(String name) {
                return message + name + ".";
            }
        }
        ```

#### 编写应用程序客户端代码

当调用端口上的远程方法时，客户端执行以下步骤

1. 使用生成的 `HelloService.endpoint.HelloService` 类，该类表示已部署服务的 WSDL 文件的 URI 上的服务。其中 `javaeetutorial.helloservice` 是远程已有的包，`endpoint.HelloService` 是生成的类

    ```oac_no_warn
    import javaeetutorial.helloservice.endpoint.HelloService;
    import javax.xml.ws.WebServiceRef;
    
    public class HelloAppClient {
        @WebServiceRef(wsdlLocation =
          "http://localhost:8080/helloservice-war/HelloService?WSDL")
        private static HelloService service;
    ```

2. 通过调用服务上的 getHelloPort 来检索服务的代理，也称为端口。端口实现服务定义的 SEI。其中，get类名Port()。

    ```
    javaeetutorial.helloservice.endpoint.Hello port = service.getHelloPort();
    ```

    





## 用 JAX-RS 构建 RESTful Web Services

### 什么是 RESTful Web 服务？

Representational State Transfer

特点：

* 轻量级
* 松耦合
* 适合为遍布互联网的客户创建 api
* 客户机-服务器应用程序的体系结构风格
* 数据和功能被视为资源，并使用统一资源标识符(uri)访问
* REST 体系结构样式被设计为使用无状态通信协议，通常是 HTTP

### 创建 RESTful 根资源类

根资源类是"plain old Java objects" (POJOs)

* 要么整个类都用 `@Path` 注释，要么至少一个方法用 `@Path` 注释，要么有一个请求方法指示器，比如 `@GET`, `@PUT`, `@POST`, 或 `@DELETE`
* 资源方法是用请求方法指示符注释的资源类的方法

### 用 JAX-RS 开发 RESTful Web Services

JAX-RS 是一种 Java 编程语言 API













## 使用 JAX-RS 客户端 API 访问 REST 资源

## JAX-RS: 高级主题和示例

