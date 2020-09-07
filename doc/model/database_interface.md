##### 使用User数据库
```
注册：username, password, identity(默认为居民), communityid(待讨论) userid(自增)

登录：username, password
```



##### 使用notice数据库
```

发布公告：userid, title, text, communityid (noticeid 自增 noticetime更新) 

查看公告：communityid (按时间排列)

修改公告：noticeid, title, text, communityid (noticetime更新)
```



##### 使用message数据库
```

发布帖子：userid, title, text, communityid (messageid 自增 messagetime更新) 

查看帖子：communityid (按时间排列)

修改帖子：messageid, title, text, userid, communityid (messagetime更新)

删除帖子：messageid, userid, communityid
```


##### 使用complaint数据库
```

发布投诉：userid, title, text, communityid (complaintid 自增 complainttime更新) (使用complaint数据库)

回复和处理投诉不知道怎么弄
```


##### 使用bills数据库
```

发布收费：userid, price, type, status, communityid (billid 自增 billtime更新)

更新收费：billid, status
```


##### 使用order数据库
```

发布订单：userid, good, buyerid, price (orderid自增 ordertime更新)

也不知道怎么弄
```



##### 使用health数据库
```

添加信息：userid, status, curr_position, prev_postion (healthid自增, healthtime更新)
```


##### community数据库
```

添加社区：communityname, adminid (communityid自增)
```
