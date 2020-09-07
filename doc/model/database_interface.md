##### 使用User数据库
```
注册：username, password, identity(默认为居民), communityid(待讨论) userid(自增)

登录：username, password

接口：
Integer searchUserId(String username)：返回userid
void createUser(String username, String password)
User findUser(String userid) :返回查询结果: 用户实体
```



##### 使用notice数据库
```

发布公告：userName, title, detail, communityId (noticeid 自增 noticetime更新) 

查看公告：communityid (按时间排列)

修改公告：noticeid, userid, title, detail, communityId (noticetime更新)

接口：
Integer searchNoticeId(Notice notice): 返回noticeid
void createNotice(String userId, String title, String text, String communityId)
List<Notice> findAllNotice(String communityId): 返回公告实体的列表
void updateNotice(Integer noticeid, Integer userId, String title, String text, Integer communityId)
```



##### 使用message数据库
```

发布帖子：userid, title, detail, communityid (postid 自增 posttime更新) 

查看帖子：communityId (按时间排列)

修改帖子：postid, title, detail, userid, communityid (messagetime更新)

删除帖子：postId, userId, communityId

接口:
Integer searchPostId(Post post): 返回posteId
void createPost(Integer userId, String title, String text, Integer communityId)
List<Post> findAllPost(Integer communityId)
void updatePost(Integer postId, Integer userId, Integer communityId, String titile, String text)
void deletePost(Integer postId, Integer userId)
```


##### 使用complaint数据库
```

发布投诉：userid, title, text, communityid (complaintid 自增 complainttime更新) (使用complaint数据库)

回复和处理投诉不知道怎么弄
```


##### 使用bills数据库
```

发布收费：adminid, userid, price, type, status, communityid (billid 自增 billtime更新)

更新收费：billid, status, adminid

接口:
Integer searchBillId(Bill bill): 返回billId
void createBill(Integer amdinId, Integer userId, Integer communityId, Integer price, String type, Boolean status)
List<Bill> findBills(Integer communityId, Integer userId)
void updateBill(Integer billId, Integer adminId, Boolean status)
```


##### 使用order数据库
```

发布订单：userid, good, buyerid, price (orderid自增 ordertime更新)

也不知道怎么弄
```



##### 使用health数据库
```

添加信息：userid, status, currposition (healthid自增, healthtime更新, prevpostion保存之前的curr_postion)

接口:
Integer searchHealthId(Health health): 返回healthId
void createHealth(Integer userId, String status, String curr_position)
void updateHealth(Integer healthId, Integer userId, String status, String curr_postion)
Health findUserHealth(Integer userId)
```


##### community数据库
```

添加社区：communityname, adminid (communityid自增)

接口:
Integer searchCommunityId(Community community): 返回communityId
void createCommunity(Integer adminId, String communityName)
Lisr<Community> findAllCommunity()
```

##### admins数据库
```

添加管理员：userid, communityid (adminid自增)

接口:
Integer searchAdminId(Integer userId): 返回adminId
void createAdmin(Integer userId, Integer communityId)
```

##### habitants数据库
```

添加居民：userid, communityid (hid自增)

接口:
Integer searchHid(Integer userId): 返回hid
void creatHabitant(Integer userId, Integer communityId)
```
