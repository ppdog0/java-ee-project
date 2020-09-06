### User

#### 用户信息

主键：

* userID（数据库自增）

外键：

* 密码 password
* 用户名 account（被 nickname 笑到）
* 社区ID communityID

### Community

#### 社区信息

主键：

* communityID（数据库自增）

外键：

* 社区名称 communityName
* 管理员ID adminID

### Announcement（建议改为Notice）

#### 公告信息

主键：

* noticeID（数据库自增）

外键：

* 所属的社区 communityID
* 所属的用户 userID

其它属性：

* 标题 title
* 正文 detail
* **发布时间 noticeTime （建议加入）**

### Complaint

#### 投诉信息

主键：

* complaintID（数据库自增）

外键：

* 所属的社区 communityID
* 所属的用户 userID

其它属性：

* 标题 title
* 正文 detail
* 日期 complaintTime

### Fare

#### 缴费信息

主键：

* fareID（数据库自增）

外键：

* 所属的社区 communityID
* 所属的用户 userID

属性：

* 金额 cost
* 标题 title
* 正文 detail

### ~~fare~~（建议改为Health，内部具体构造需要进一步思考）

#### 疫情信息

主键：

* id（数据库自增）

外键：

* 所属的社区
* 所属的用户

属性

* 日期
* 各项情况

**-----------------------------一条头发------------------------------------**


## Questions 疑问

1. 一个用户可以是多个社区的业主，在此如何对应？
2. 一个社区可以有多个管理员，但是目前只能有一个，如何解决？
3. 对于疫情助手，
    1. 如何记录每周一次的健康信息填写？
    2. 如何记录突发的身体不适或生病情况？
    3. 如何记录离市轨迹信息？
