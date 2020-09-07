## Bean 验证简介

### 使用 Bean 验证约束

使用方式：在以下地方添加 `注解`：

* 字段
* 方法
* 类

注解的类型：

* 系统内置，来自 `javax.validation.constraints` 包
* 用户自定义

例子：

* ```oac_no_warn
    public class Name {
        @NotNull
        @Size(min=1, max=16)
        private String firstname;
    
        @NotNull
        @Size(min=1, max=16)
        private String lastname;
        ...
    }
    ```

### 验证 null 字符串和 empty 字符串

默认情况下，JSF 实现将输入组件初始化为 empty 字符串，即 `""`

如果想令 Bean 验证模型将 `""` 视作 `null`，那么在 `web.xml`中加入上下文参数：

```oac_no_warn
<context-param>
    <param-name>
        javax.faces.INTERPRET_EMPTY_STRING_SUBMITTED_VALUES_AS_NULL
    </param-name>
    <param-value>true</param-value>
</context-param>
```

但要注意：如果该元素有一个 `@NotNull` 约束，则此时如果用户没有输入任何内容，传入的 `""` 被视作 `null`，则会导致 `@NotNull` 约束失败

### 对构造函数和方法进行验证

Bean 验证约束

* 可以放在：
    * 非静态方法和构造函数的参数上
    * 非静态方法的返回值上
* 不可以放在：
    * 静态方法和构造函数

跨参数约束

* 含义：应用在多个参数的约束

* 应用场景：方法或构造函数

* 示例：

    ```oac_no_warn
    @ConsistentPhoneParameters
    @NotNull
    public Employee (String name, String officePhone, String mobilePhone) {
      ...
    }
    ```

* 注意：

    * 跨参数约束 和 返回值约束 都直接应用于方法或构造函数，为了避免混淆约束应用的位置、参数或返回值，一定要选择好合适的名称

    * 为了避免这种模糊性，将 `validationAppliesTo` 元素添加到约束注释定义中

        * `ConstraintTarget.RETURN_VALUE`

        * `ConstraintTarget.PARAMETERS`

        * ```oac_no_warn
            @Manager(validationAppliesTo=ConstraintTarget.RETURN_VALUE)
            public Employee getManager(Employee employee) { ... }
            ```

向方法返回值添加约束

* 若要验证方法的返回值，可以直接将约束应用于方法或构造函数声明

* ```oac_no_warn
    @NotNull
    public Employee getEmployee() { ... }
    ```





## Bean 验证: 高级主题

### 创建自定义约束

创建方式

* 将若干个内置约束组合起来，变成一个自定义约束
* 其它

**其它内容自行查看doc**

