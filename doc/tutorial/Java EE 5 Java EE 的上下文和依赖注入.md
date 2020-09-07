

## Java EE 的上下文和依赖注入介绍

Contexts and Dependency Injection, CDI

背景知识：每个对象都有`依赖`，在以前，对象想要获得自己的`依赖`，一般只有 2 种途径：

* 自行创建
* 作为参数传入

CDI 的创新意义：

* 自动给对象提供其`依赖`
* 还负责管理这些`依赖`的生命周期

对比例子：

* 有一个 `Message`接口和一个 `MessageB` 实例

    ```oac_no_warn
    public interface Message {
        public String get();
    }
    
    public class MessageB implements Message {
        public MessageB() { }
    
        @Override
        public String get() {
            return "message B";
        }
    }
    ```

* 没有 CDI 的传统方式，这个 servlet 需要手动创建一个 `Message` 实例（注意 `init()` 方法）

    ```oac_no_warn
    @WebServlet("/cdiservlet")
    public class NewServlet extends HttpServlet {
        private Message message;
    
        @Override
        public void init() {
            message = new MessageB();
        }
    
        @Override
        public void doGet(HttpServletRequest request, HttpServletResponse response)
                      throws IOException {
            response.getWriter().write(message.get());
        }
    }
    ```

* 使用 CDI 之后，使用一个 `@Inject` 注解，让 CDI 负责管理就好

    ```oac_no_warn
    @WebServlet("/cdiservlet")
    public class NewServlet extends HttpServlet {
        @Inject private Message message;
    
        @Override
        public void doGet(HttpServletRequest request, HttpServletResponse response)
                      throws IOException {
            response.getWriter().write(message.get());
        }
    }
    ```
    * 工作流程：
        1. CDI 运行时查找实现 Message 接口的类
        2. 查找到 MessageB 类
        3. 创建该类的新实例
        4. 在运行时将其注入 servlet
    * 为了管理新实例的生命周期，CDI 运行时需要知道实例的作用域是什么
        * 如果只需要该实例来处理 HTTP 请求，那么使用 `avax.enterprise.context.RequestScoped` 注解就足够了

### CDI bean

CDI bean 是 CDI 可以实例化、管理和自动注入的类，以满足其他对象的依赖关系

几乎任何 Java 类都可以由 CDI 管理和注入

如果一个接口有多个实现，那么可以使用 CDI 提供的一些机制来选择要注入的实现

### CDI 概述

目的：松耦合

2 个核心功能：

* 上下文：此服务使您能够将`有状态组件的生命周期和交互`绑定到`定义良好但可扩展的生命周期上下文`
* 依赖注入：使你能够以类型安全的方式将`组件`注入到应用程序中，并在部署时选择要注入的特定接口的`实现`

其它功能：

* 集成了 EL，使得可以在任何 JSF 或 JSP 页面中直接使用组件
* 装饰已注入的组件
* 使用类型安全的监听器与组件绑定在一起
* 一个事件通知模型
* 4 个作用域
    * 3 个标准作用域：request, session, application
    * 一个 web conversation scope
* 一个完整的 SPI（Service Provider Interface），允许第三方框架在 javaee 环境中进行干净的集成

为了实现松耦合，完成以下工作：

* 对耦合组件的生命周期进行解耦的办法：
    * 通过自动生命周期管理使组件上下文相关
    * 允许有状态组件像服务一样交互（只通过消息传递）
* 通过事件，将消息生产者与消费者完全分离
* 通过监听器，解耦 orthogonal concerns

为了实现强类型，完成以下工作：

* 消除使用基于字符串的名称进行连接和相关性的查找，以便编译器检测类型错误
* 允许使用声明性的 Java 注释来指定所有的内容，这在很大程度上消除了对 XML 部署描述符的需求，并且使得在开发时提供可以内省代码和理解依赖结构的工具变得容易

### 关于 Beans

CDI 重新定义了 bean 的概念

* 在 CDI 中，bean 是定义应用程序状态或逻辑的上下文对象的源。

* 如果容器可以根据 CDI 规范中定义的生命周期上下文模型来管理其实例的生命周期，那么 javaee 组件就是一个 bean。

一个 bean 具有以下属性：

* （非空）一组 bean 类型
    * Bean 类型定义了 bean 的客户机可见类型。几乎任何 Java 类型都可能是 bean 的 bean 类型
    * Bean 类型可以是
        * 原始类型
        * 原语类型
        * 数组类型
        * 接口、具体类或抽象类，可以声明为 final 或具有 final 方法
        * 带有类型参数和类型变量的参数化类型
* （非空）一组限定符（Qualifiers）
* 一个作用域
* （可选）一个 bean EL 名称
* 一组监听器绑定
* 一个 bean 实现

### 关于 CDI 托管 bean

一个托管 bean 实现为一个 Java 类，称为 bean类

一个 Java 类要成为一个托管 bean，必须满足以下任意一个条件：

* 它被其它 javaee 技术规范定义为一个托管 bean
* 满足以下所有条件：
    * 它不是非静态的内部类。
    * 它是一个具体的类，或者被注释为 `@Decorator`
    * 它没有使用 EJB 组件定义的注释进行注释，也没有在 EJB-jar.xml 中声明为 EJB bean 类
    * 它有一个无参构造函数，且该函数带一个注解 `@inject`

**定义托管 bean 不需要特殊声明(如注释)。**

### 作为可注入对象的 bean

CDI 可以注入以下几种对象：

* 几乎所有的 Java 类
* 会话 bean
* Javaee 资源：数据源、 javamessage 服务主题、队列、连接工厂等
* 持久化上下文。即 JPA `EntityManager` 对象
* 生产者字段
* 由生成器方法返回的对象
* Web 服务引用
* 远程企业 bean 引用

### 限定符 Qualifiers

本质：限定符是应用于 bean 的注释

作用：使用限定符来提供特定 bean 类型的各种实现。说白了，一个 bean 类型的实现有很多种，我只想使用我想要的那一种。

例子：自定义一个限定符

* ```oac_no_warn
    package greetings;
    
    import static java.lang.annotation.ElementType.FIELD;
    import static java.lang.annotation.ElementType.METHOD;
    import static java.lang.annotation.ElementType.PARAMETER;
    import static java.lang.annotation.ElementType.TYPE;
    import static java.lang.annotation.RetentionPolicy.RUNTIME;
    
    import java.lang.annotation.Retention;
    import static java.lang.annotation.RetentionPolicy.RUNTIME;
    import java.lang.annotation.Target;
    
    import javax.inject.Qualifier;
    
    @Qualifier
    @Retention(RUNTIME)
    @Target({TYPE, METHOD, FIELD, PARAMETER})
    public @interface Informal {}
    ```

* 此时，出现一个 extends 了 Greeting 类的 InformalGreeting 类，我们给它加上一个刚刚定义好的 注解`@Informal`，等实际使用时，直接加上注解就好

    ```oac_no_warn
    package greetings;
    
    @Informal
    public class InformalGreeting extends Greeting {
        public String greet(String name) {
            return "Hi, " + name + "!";
        }
    }
    ```

* 如果没有显示声明限定符，那么实际上使用了 `@Default` 限定符

    ```oac_no_warn
    package greetings;
    
    import javax.enterprise.inject.Default;
    
    @Default
    public class Greeting {
        public String greet(String name) {
            return "Hello, " + name + ".";
        }
    }
    ```

### 注入 Beans

`@Inject` 注解

同一种 bean 类型，使用不同的实现

```oac_no_warn
import javax.inject.Inject;

public class Printer {

    @Inject Greeting greeting;
    @Inject @Informal Greeting informalGreeting;
    ...
}
```

### 作用域 Scope

对于被注入的 bean ，它需要能够在用户与应用程序交互期间保持状态，办法是：给 bean 一个作用域：

* `@RequestScoped`
    * 单个用户的单个 HTTP 请求中
* `@SessionScoped`
    * 单个用户的跨多个 HTTP 请求
* `@ApplicationScoped`
    * 所有用户与 web 应用程序交互的共享状态
* `@Dependent`
    * 默认范围
    * 一个对象的存在只是为了服务于一个客户机(bean) ，并且与该客户机(bean)具有相同的生命周期。
* `@ConversationScoped`
    * 单个用户与 servlet 的交互

Javaee 组件(如 servlet 和 EJB) 和 javabean 组件没有明确定义的作用域，它们有：

* 单例。它的状态在所有客户端之间共享
* 无状态对象，例如 servlet 和无状态会话 bean，它们不包含客户机可见的状态。
* 必须由客户端显式创建和销毁的对象，例如 javabean 组件和有状态会话 bean，它们的状态通过客户端之间的显式引用传递来共享

### 给予 Beans 一个 EL 名字

要使 bean 可以通过 EL 访问，请使用 `@Named` 内置限定符

要求：第一个字母为小写

例子：

* 在 Facelets 网页访问时，称为 printer

    ```oac_no_warn
    import javax.enterprise.context.RequestScoped;
    import javax.inject.Inject;
    import javax.inject.Named;
    
    @Named
    @RequestScoped
    public class Printer {
    
        @Inject @Informal Greeting greeting;
        ...
    }
    ```

* 可以为 `@named` 指定一个参数，从而使用非默认名称。

    此时，称为 MyPrinter

    ```
    @Named("MyPrinter")
    ```

### 添加 Setter 和 Getter 方法

```oac_no_warn
package greetings;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class Printer {

    @Inject @Informal Greeting greeting;

    private String name;
    private String salutation;

    public void createSalutation() {
        this.salutation = greeting.greet(name);
    }

    public String getSalutation() {
        return salutation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

### 在 Facelets 页面中使用托管 Bean

必需要创建一个表单，该表单使用用户界面元素调用其方法并显示结果

```oac_no_warn
<h:form id="greetme">
   <p><h:outputLabel value="Enter your name: " for="name"/>
      <h:inputText id="name" value="#{printer.name}"/></p>
   <p><h:commandButton value="Say Hello"
                       action="#{printer.createSalutation}"/></p>
   <p><h:outputText value="#{printer.salutation}"/></p>
</h:form>
```

### 使用 Producer 方法注入对象

Producer 方法可以注入：

* 非 bean 对象

* 其值在运行时可能变化的对象

    * 该方法生成一个由@random 限定符定义的随机数

        ```oac_no_warn
        private java.util.Random random =
            new java.util.Random( System.currentTimeMillis() );
        
        java.util.Random getRandom() {
                return random;
        }
        
        @Produces @Random int next() {
            return getRandom().nextInt(maxNumber);
        }
        ```

    * 当你将这个对象注入到另一个托管 bean 中时，你声明了这个对象的上下文实例:

        ```
        @Inject @Random Instance<Integer> randomInt;
        ```

    * 然后调用 Instance 的 get 方法:

        ```
        this.number = randomInt.get();
        ```

* 需要自定义初始化的对象

    * 想要初始化一个由 `@maxnumber` 限定符定义的数值，那么你可以在托管 bean 中定义这个值，然后为它定义一个生产者方法 `getMaxNumber`

        ```oac_no_warn
        private int maxNumber = 100;
        ...
        @Produces @MaxNumber int getMaxNumber() {
            return maxNumber;
        }
        ```

    * 当你将对象注入另一个托管 bean 时，容器会自动调用 producer 方法，将值初始化为100:

        ```
        @Inject @MaxNumber private int maxNumber;
        ```

### 配置 CDI 应用程序

当使用作用域类型对 bean 进行注释时，服务器将应用程序识别为 bean 归档文件，不需要进行任何其他配置

CDI 使用一个可选的名为 `beans.xml` 的部署描述符

* 如果存在冲突，`beans.xml` 中的设置将覆盖 `注解` 的设置
* 仅在某些有限的情况下包含 `beans.xml` 部署描述符
* 存放位置：
    * 对于 web 应用程序，则必须位于 WEB-INF 目录中
    * 对于 EJB 模块或 JAR 文件，则必须位于 META-INF 目录中

### 在 CDI 托管 Bean 类中使用 `@PostConstruct` 和`@PreDestroy` 注释

* `@PostConstruct` 

    * 功能：初始化托管 Bean

    * 如果在超类中声明带注释的方法，除非声明类的子类覆盖该方法，否则将调用该方法

    * ```oac_no_warn
        @PostConstruct
        public void reset () {
            this.minimum = 0;
            this.userNumber = 0;
            this.remainingGuesses = 0;
            this.maximum = maxNumber;
            this.number = randomInt.get();
        }
        ```

* `@PreDestroy`
    
    * 功能：准备销毁托管 Bean





## 运行基本上下文和依赖注入例子



## 上下文和 Java EE 依赖注入: 高级主题

部署 javaee 应用程序时，CDI 会在 bean 归档文件（`bean archives`）中查找 bean

* Bean 归档文件是包含 CDI 运行时可以管理和注入的 bean 的任何模块
* 2 种 Bean 归档文件
    * 显式 bean 归档文件
        * 包含 beans.xml 部署描述符
        * 可以是一个空文件
        * 不包含版本号，或者包含版本号1.1
        * 将 `bean-discovery-mode` 属性设置为 `all`
        * CDI 可以管理和注入显式存档中的任何 bean，但那些带有 `@veted` 注释的除外
    * 隐式 bean 归档文件
        * 包含一些带有作用域类型注释的 bean
        * 不包含 beans.xml 部署描述符，或者包含一个设置为带注释的 bean-discovery-mode 属性的 beans.xml 部署描述符
        * 在隐式归档文件中，CDI 只能管理和注入带有作用域类型注释的 bean

beans.xml 部署描述符如果存在，

* 对于 web 应用程序，则必须位于 WEB-INF 目录中
* 对于 EJB 模块或 JAR 文件，则必须位于 META-INF 目录中

### 在 CDI 应用中选择一个 bean 的某种特定的实现

当一个 bean 有多种实现时，为了选择某一个，可以：

* 如果是在开发阶段，那么可以注入限定符

* （如果这些实现方案都在同一个归档文件中）如果不想更改源码，可以在部署时配置 `beans.xml` 文件

    * 对于备胎版本的实现，加上 `@Alternative` 注解，

        ```oac_no_warn
        @Alternative
        public class TestCoderImpl implements Coder { ... }
        ```

        并且在 `beans.xml` 文件中声明：

        ```oac_no_warn
        <beans ...>
            <alternatives>
                <class>javaeetutorial.encoder.TestCoderImpl</class>
            </alternatives>
        </beans>
        ```

* （如果这些实现方案分散在多个归档文件中）使用 `@Priority` 注释，且优先选择具有更高优先级值的可选项

    ```oac_no_warn
    @Alternative
    @Priority(Interceptor.Priority.APPLICATION+10)
    public class TestCoderImpl implements Coder { ... }
    ```

### 在 CDI 应用中使用 Producer 方法、Producer 字段和 Disposer 方法

Producer 方法和 Producer 字段都使用了 `javax.enterprise.inject.Produces` 注解

Producer 方法

* 生成的对象可以被注入

* ```oac_no_warn
    @Produces
    @Chosen
    @RequestScoped
    public Coder getCoder() {
    
        switch (coderType) {
            case TEST:
                return new TestCoderImpl();
            case SHIFT:
                return new CoderImpl();
            default:
                return null;
        }
    }
    ```

Producer 字段

* 是比生产者方法更简单的替代方法;

* 它是生成对象的 bean 字段
* 可以代替简单的 getter 方法
* Producer 字段对于声明 javaee 资源(如数据源、 JMS 资源和 web 服务引用)特别有用，比如 JDBC DataSource 或者 JPA `EntityManager`

Disposer 方法

* 功能：使用生产者方法或生产者字段生成一个对象，在其工作完成时，使用 Disposer 方法删除该对象

* `@Disposes `注解

* ```oac_no_warn
    public void close(@Disposes @UserDatabase EntityManager em) {
        em.close();
    }
    ```

### 在 CDI 应用程序中使用事件

**事件** 的优点：允许 bean 在没有任何编译时依赖性的情况下进行通信

事件可以在不同的 bean 上完成以下操作：

* 被定义
* 被触发(fire)
* 被处理

#### 定义事件

一个事件，包括：

* 一个 event 对象（本质是一个 Java 对象）
* 若干个限定符类型（本质是 event 限定符）

#### 使用 Observer 方法处理事件

event handler 使用 observer 方法来处理 event

* 参数

    * 一个特定事件类型的事件，用 `@Observes` 注解加上该事件类型的任何限定符

        ```oac_no_warn
        public void creditPayment(@Observes @Credit PaymentEvent event) {
            ...
        }
        
        public void debitPayment(@Observes @Debit PaymentEvent event) {
            ...
        }
        ```

    * （可选）其他参数是注入点，可以声明限定符

* Conditional Observer 方法

    * `@Observes(notifyObserver=IF_EXISTS)`
        * 只有当定义观察者方法的 bean 实例在当前上下文中已经存在时，条件观察者方法才会被通知事件
    * `@Observes(notifyObserver=ALWAYS)`
        * 获得默认的无条件行为

* Transactional Observer 方法

    * ```oac_no_warn
        @Observes(during=BEFORE_COMPLETION)
        
        @Observes(during=AFTER_COMPLETION)
        
        @Observes(during=AFTER_SUCCESS)
        
        @Observes(during=AFTER_FAILURE)
        ```

        * 事务观察器方法在触发事件的事务的完成前或完成后阶段被通知事件。您还可以指定只有在事务成功完成或未成功完成之后才发出通知

    * `@Observes(during=IN_PROGRESS)`

        * 获得默认的非事务性行为

Observer Method 排序：

* 在生成某个观察者事件通知之前，容器确定调用该事件的观察者方法的顺序
* 通过在观察者方法的事件参数上声明 `@Priority` 注释来建立

* ```oac_no_warn
    void afterLogin(@Observes @Priority(javax.interceptor.Interceptor.Priority.APPLICATION) LoggedInEvent event) { ... }
    ```

* 如果未指定 `@priority` 注释，则默认值为 `javax.Interceptor. Interceptor. priory.application + 500`

* 如果为两个或多个观察者方法分配了相同的优先级，则调用它们的顺序是未定义的，因此是不可预测的。

#### 触发事件

* 同步触发
    * 调用 `javax.enterprise.event.Event.fire` 方法
* 异步触发
    * 调用 `javax.enterprise.event.Event.fireAsync` 方法

### 在 CDI 应用程序中使用拦截器（Interceptors）

本质：一个类

目的：插入关联的目标类中发生的方法调用或生命周期事件

常用功能：执行一些与应用程序的业务逻辑分离的任务，比如：日志记录，审计

* 这些任务也称为 `cross-cutting tasks`

拦截器类包含

* 一个带注释 `@AroundInvoke`的方法：
    * 功能：指定在调用被拦截的方法时拦截器将执行的任务
    * 参数：一个 `javax.interceptor.InvocationContext` 参数
    * 返回值：`java.lang.Object`
    * 抛出 `Exception`
* （可选）`@PostConstruct`, `@PreDestroy`, `@PrePassivate`, 或 `@PostActivate`
* （可选）`@AroundTimeout` 指定 EJB 超时拦截器

一个拦截器类可以包含多个拦截器方法，但是每种类型的拦截器方法不能多于一个

除了拦截器之外，应用程序还定义一个或多个拦截器绑定类型，这些类型是将拦截器与目标 bean 或方法相关联的注释

```oac_no_warn
@Inherited
@InterceptorBinding
@Retention(RUNTIME)
@Target({METHOD, TYPE})
public @interface Logged {
}
```

一旦定义了拦截器和绑定类型，就可以用绑定类型对以下内容进行注释：

* bean
    * 这个 bean 的所有方法都会调用拦截器
* 某个方法
    * 这个方法会调用拦截器

如果应用程序使用多个拦截器，则按照 `beans.xml`文件中指定的顺序调用拦截器

* `beans.xml` 文件中指定的拦截器仅适用于同一归档文件中的类
* 使用 `@Priority` 注释为包含多个模块的应用程序指定全局拦截器
* 在使用 `@Priority` 注释时，不需要在 `beans.xml` 文件中指定拦截器

### 在 CDI 应用程序中使用 decorator

Decorator 是一个 `@javax.decorator.Decorator` 的 Java 类

* 在 `beans.xml` 文件中有一个对应的 `Decorator` 元素

    * ```oac_no_warn
        <decorators>
            <class>javaeetutorial.decorators.CoderDecorator</class>
        </decorators>
        ```

    * 如果应用程序使用多个装饰符，则按照在 beans.xml 文件中指定的顺序调用装饰符。

    * 如果应用程序同时具有拦截器和装饰器，则首先调用拦截器。实际上，这意味着您无法拦截装饰器。

    * beans.xml 文件中指定的装饰符只适用于同一归档文件中的类。使用@priority 注释为包含多个模块的应用程序全局指定 decorator

* 一个 decorator bean 类必须有一个以 `@javax.decorator.Delegate` 注解的委托注入点，此注入点可以是装饰类的字段、构造函数参数或初始化器方法参数

* 装饰器在外表上类似于拦截器。然而，它们实际上执行的任务是对拦截器执行的任务的补充

    * 拦截器执行与方法调用和 bean 生命周期相关的横切任务，但不能执行任何业务逻辑
    * decorator 通过拦截 bean 的业务方法来执行业务逻辑
    * 这意味着不像拦截器那样可重用于不同类型的应用程序，它们的逻辑特定于特定的应用程序。

### Stereotypes 原型

本质：注释

功能：将多个其它注释打包在一起

它指定了以下内容：

* 默认作用域
* 零个或多个拦截器绑定
* （可选）`@Named` 注解，保证可以使用默认 EL 命名
* （可选）`@Alternative` 注解

```oac_no_warn
@RequestScoped
@Secure
@Transactional
@Named
@Stereotype
@Target(TYPE)
@Retention(RUNTIME)
public @interface Action {}
```







## Bootstrapping a CDI Container in Java SE

## 运行高级上下文和依赖注入例子

