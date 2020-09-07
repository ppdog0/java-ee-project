## Java持久化API

JPA 的本质：对象/关系映射工具

功能：管理 Java 应用程序中的关系数据

Java Persistence 包括四个方面：

* JPA
* 查询语言
* 持久性标准 API（Java Persistence Criteria API）
* 对象/关系映射元数据

### 实体 Entity

实体是轻量级持久性域对象

一个**实体**代表关系数据库中的一个表

每个**实体实例**对应于该表中的一行

### 实体类的要求

* 类必须使用 `javax.persistence.Entity` 注解
* 类可以有多个构造方法，但是必须有一个无参、public 或 protect 的构造方法
* 该类不能被声明为 final。不能声明任何方法或持久实例变量为 final
* 如果实体实例通过值作为分离对象传递，比如通过会话 bean 的远程业务接口，则该类必须实现 Serializable 接口
* 实体可以扩展实体类和非实体类，非实体类可以扩展实体类
* 持久实例变量必须声明为 private、 protected 或 package-private，并且只能由实体类的方法直接访问。客户端必须通过访问器业务方法访问实体的状态。

### 实体类中的持久字段和属性

可以通过实体的**字段**或**属性**访问实体的持久状态

* 这些字段或属性使用对象/关系映射注释将实体和实体关系映射到底层数据存储区中的关系数据

持久性字段

* 直接访问实体类实例变量
* 凡是没有 注解为`@javax.persistence.Transient` 或标记为 `transient` 的字段，都会被持久化

持久性属性

* 必须遵循 javabean 组件的方法约定

* ```oac_no_warn
    Type getProperty()
    void setProperty(Type type)
    ```

如果要使用 Collection:

* 那么只能使用以下 Collection 接口
    * `java.util.Collection`
    * `java.util.Set`
    * `java.util.List`
    * `java.util.Map`

* 使用 `javax.persistence.ElementCollection` 注解
    * 2 个属性：

        * `targetClass`
            * 指定基本类或可嵌入类的类名
            * 如果字段或属性是使用 Java 编程语言泛型定义的，则该属性是可选的。
        * `fetch`
            * 可选
            * 指定使用哪个 `javax.persistence.FetchType` 常量，`LAZY`（默认） 还是`EAGER`

    * ```oac_no_warn
        @Entity
        public class Person {
            ...
            @ElementCollection(fetch=EAGER)
            protected Set<String> nickname = new HashSet();
            ...
        }
        ```

### 验证持久字段和属性

* `@NotNull`

    ```oac_no_warn
    @NotNull
    protected String firstName;
    ```

* `Pattern`

    ```oac_no_warn
    @Pattern.List({
        @Pattern(regexp="..."),
        @Pattern(regexp="...")
    })
    ```

* `Temporal`

    ```oac_no_warn
    @Temporal(javax.persistence.TemporalType.DATE)
    @Past
    protected Date birthday;
    ```

### 实体中的主键

* 如果主键只有一个属性，使用 `javax.persistence.Id` 注解来表示主键属性或字段
* 如果主键有多个属性，那么这些属性必须定义在一个**主键类**里
    * `javax.persistence.EmbeddedId` 和 `javax.persistence.IdClass` 注解

### 实体关系中的多重性（Multiplicity）

* One-to-one
* One-to-many
* Many-to-one
* Many-to-many

### 实体关系的方向

单向关系

双向关系

* 拥有方（owning side）
    * 关系的拥有方决定了持久性运行时如何更新数据库中的关系
    * 双向关系的反向方必须通过使用以下之一的 `mappedBy` 元素注解，来指出拥有方
        * `@OneToOne`
        * `@OneToMany`
        * `@ManyToMany`
    * 多对一双向关系的多方不能定义 mappedBy 元素。多方总是关系的拥有方
    * 对于一对一的双向关系，所属端对应于包含相应外键的那一端。
    * 对于多对多双向关系，任何一方都可能是拥有方。
* 反向方（inverse side）

### 管理实体

实体管理器

* 功能：管理实体
* 由一个 `javax.persistence.EntityManager` 表示
* 每个 EntityManager 实例都与一个**持久性上下文**关联
    * 持久性上下文的本质：一组存在于特定数据存储中的托管实体实例
    * 功能：定义创建、持久化和删除特定实体实例的范围

#### EntityManager 接口

功能：

* 创建并删除持久的实体实例
* 通过实体的主键查找实体
* 允许在实体上运行查询

2 种实体管理器：

* 由容器管理的实体管理器

    * 容器会自动将 EntityManager 实例的持久化上下文传播给在单个 Java Transaction API (JTA)事务中使用 EntityManager 实例的所有应用程序组件

    * JTA 事务通常涉及跨应用程序组件的调用

    * 要获得 EntityManager 实例，请将实体管理器注入到应用程序组件中

        ```
        @PersistenceContext
        EntityManager em;
        ```

* 由应用程序管理的实体管理器

    * 使用应用程序管理的实体管理器，持久性上下文不会传播到应用程序组件，EntityManager 实例的生命周期由应用程序管理。

    * `EntityManagerFactory` 实例是线程安全的

    * ```oac_no_warn
        @PersistenceUnit
        EntityManagerFactory emf;
        EntityManager em = emf.createEntityManager();
        ```

    * ```oac_no_warn
        @Resource
        UserTransaction utx;
        try {
            utx.begin();
            em.persist(SomeEntity);
            em.merge(AnotherEntity);
            em.remove(ThirdEntity);
            utx.commit();
        } catch (Exception e) {
            utx.rollback();
        }
        ```

使用 EntityManager 查找实体

* `EntityManager.find` 方法通过实体的主键查找数据存储中的实体

* ```oac_no_warn
    @PersistenceContext
    EntityManager em;
    public void enterOrder(int custID, CustomerOrder newOrder) {
        Customer cust = em.find(Customer.class, custID);
        cust.getOrders().add(newOrder);
        newOrder.setCustomer(cust);
    }
    ```

持久化实体实例

```oac_no_warn
@PersistenceContext
EntityManager em;
...
public LineItem createLineItem(CustomerOrder order, Product product,
        int quantity) {
    LineItem li = new LineItem(order, product, quantity);
    order.getLineItems().add(li);
    em.persist(li);
    return li;
}
```

删除实体实例

```oac_no_warn
public void removeOrder(Integer orderId) {
    try {
        CustomerOrder order = em.find(CustomerOrder.class, orderId);
        em.remove(order);
    }...
```

持久化单元

* 功能：

    * 定义了一组由应用程序中 EntityManager 实例管理的所有实体类
    * 表示单个数据存储区中包含的数据

* 由 Persistence.xml 配置文件定义

    ```oac_no_warn
    <persistence>
        <persistence-unit name="OrderManagement">
            <description>This unit manages orders and customers.
                It does not rely on any vendor-specific features and can
                therefore be deployed to any persistence provider.
            </description>
            <jta-data-source>jdbc/MyOrderDB</jta-data-source>
            <jar-file>MyOrderApp.jar</jar-file>
            <class>com.widgets.CustomerOrder</class>
            <class>com.widgets.Customer</class>
        </persistence-unit>
    </persistence>
    ```

### JPA 提供的 2 种查询实体的方法

* JPQL
    * 基于字符串
    * 简单
    * 类型不安全
* Criteria API
    * 使用 Java 编程语言 API 创建类型安全查询，以查询实体及其关系























## 运行持久化示例

## Java 持久性查询语言

## 使用 Criteria API 创建查询

## 创建和使用基于字符串的条件查询

## 用锁控制对实体数据的并发访问

## 使用实体图创建获取计划

## 使用二级缓存 Java持久化API 应用程序