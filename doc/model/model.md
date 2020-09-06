---
## 数据库命名规范
* 变量一律采用小写
* 对于易混淆的属性名一律采用表名加属性名写法
---

### User

#### 用户信息

主键：

* userid（数据库自增）

外键：

属性：
* 密码 password
* 用户名 name（👴就用name）
* 社区ID communityid

### Community

#### 社区信息

主键：

* communityid（数据库自增）

外键：

属性：
* 社区名称 communityname
* 管理员ID adminID (delete)多对多关系不能使用外键

### Notice

#### 公告信息

主键：

* noticeid（数据库自增）

外键：

* 所属的社区 communityid
* 所属的用户 userid

属性：

* 标题 title
* 正文 detail
* 发布时间 noticetime

### Complaint

#### 投诉信息

主键：

* complaintID（数据库自增）

外键：

* 所属的社区 communityid
* 所属的用户 userid

属性：

* 标题 title
* 正文 detail
* 日期 complainttime

### Fare

#### 缴费信息

主键：

* fareid（数据库自增）

外键：

* 所属的社区 communityid
* 所属的用户 userid

属性：

* 金额 cost
* 标题 title
* 正文 detail

### Health

#### 疫情信息

主键：

* healthid（数据库自增）

外键：

* 所属的社区 communityID
* 所属的用户 userID

属性：

* 日期 healthdate
* 各项情况

**-----------------------------一条头发------------------------------------**


## Questions 疑问

1. 一个用户可以是多个社区的业主，在此如何对应？
是多对多关系
2. 一个社区可以有多个管理员，但是目前只能有一个，如何解决？
是多对多关系
3. 对于疫情助手，
    1. 如何记录每周一次的健康信息填写？
    2. 如何记录突发的身体不适或生病情况？
    3. 如何记录离市轨迹信息？
