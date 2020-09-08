# API

## 用户

### 用户登录

- url:`localhost:8080/user/login`
- method:`POST`
- data:`username`,`passord`
- return:`status`,`userid`

示例

```json
// 发送数据
{
	"username": "pp",
	"password": "123456"
}

// 返回数据(成功)
{
	"status": "success",
    	"userid": 1
}

// 返回数据(失败)
{
	"status": "fail"
}
```



### 用户注册

- url:`localhost:8080/user/register`
- method:`POST`
- data:`username`,`passord`
- return:`status`

```json
// 发送数据
{
	"username": "pp",
	"password": "123456"
}

// 返回数据(成功)
{
	"status": "success"
}

// 返回数据(失败)
{
	"status": "fail"
}
```



## 社区

### 社区页面

- url:`localhost:8080/community`
- method: `GET`
- data:`None`
- return: `communityname`,`title`,`details`,`date`,`username`

```
// 无发送数据

// 返回数据
{
    // 社区1
	{
		"communityname": "云社区",
    		"communityid": "1",
		"notice": 
		{
    			//公告1
			{
    				"postid": 1,
				"title": "Yellow lives matter!",
				"details": "我们社区真是太厉害啦!",
				"data": "09/08/2020",
				"username": "pp"
			},
			// 公告2
			{
              		"postid": 2,
				"title": "Yellow lives matter!",
				"details": "我们社区真是太厉害啦!",
				"data": "09/08/2020",
				"username": "pp"
			}
		}
		"post": 
		{
            		// 帖子1
			{ 
	           		"noticeid": 1,
				"title": "Yellow lives matter!",
				"details": "我们社区真是太厉害啦!",
				"data": "09/08/2020",
				"username": "pp"
			},
			// 帖子2
			{
                		"noticeid": 2,
				"title": "Yellow lives matter!",
				"details": "我们社区真是太厉害啦!",
				"data": "09/08/2020",
				"username": "pp"
			}
		}
	},
	//社区2
	{
		"communityname": "云社区",
    	"communityid": "1",
		"notice": 
		{
            // 公告1
			{ 
    			"postid": 1,
				"title": "Yellow lives matter!",
				"details": "我们社区真是太厉害啦!",
				"data": "09/08/2020",
				"username": "pp"
			},
	        // 公告2
			{
                "postid": 2,
				"title": "Yellow lives matter!",
				"details": "我们社区真是太厉害啦!",
				"data": "09/08/2020",
				"username": "pp"
			}
		}
		"post": 
		{
            // 帖子1
			{
	            "noticeid": 1,
				"title": "Yellow lives matter!",
				"details": "我们社区真是太厉害啦!",
				"data": "09/08/2020",
				"username": "pp"
			},
			// 帖子2
			{
                "noticeid": 2,
				"title": "Yellow lives matter!",
				"details": "我们社区真是太厉害啦!",
				"data": "09/08/2020",
				"username": "pp"
			}
		}
	},
}
```



## 公告

### 公告栏

- url:`localhost:8080/notice?communityid=`
- method: `GET`
- data:`communityid`
- return: `communityname`,`title`,`details`,`date`,`username`

```
// 发送数据
{
    "communityid":1
}

// 返回数据
{
    "communityname":"cloud",
    "communityid":1,
    "notice": 
    {
        // 公告1
     	{
    		"noticeid": 1,
		"title": "Yellow lives matter!",
		"details": "我们社区真是太厉害啦!",
		"date": "09/08/2020",
		"username": "pp"
	},
	// 公告2
	{
        	"noticeid": 2,
		"title": "Yellow lives matter!",
		"details": "我们社区真是太厉害啦!",
		"date": "09/08/2020",
		"username": "pp"
	}   
    }
}
```

### 发布公告

- url:`localhost:8080/notice/publish?communityid=`
- method: `POST`
- data:``communityid`,`noticeid`,`title`,`details``,`userid`
- return: `communityname`,`title`,`details`,`date`,`username`

```
// 发送数据
{
    "communityid": 1,
    "noticeid": 2,
    "userid": 3,
    "title": "example",
    "details": "example",
}

// 返回数据
{
    "communityname":"cloud",
    "communityid":1,
    "notice": 
    {
        // 公告1
     	{
    		"noticeid": 1,
			"title": "Yellow lives matter!",
			"details": "我们社区真是太厉害啦!",
			"date": "09/08/2020",
			"username": "pp"
		},
	    // 公告2
		{
        	"noticeid": 2,
			"title": "Yellow lives matter!",
			"details": "我们社区真是太厉害啦!",
			"date": "09/08/2020",
			"username": "pp"
		}   
    }
}
```

### 公告修改

- url:`localhost:8080/notice/update?noticeid=`
- method: `GET`
- data:`communityid`,`noticeid`,`title`,`details`,`userid`
- return: `communityname`,`title`,`details`,`date`,`username`

```
// 发送数据
{
    "communityid": 1,
    "noticeid": 2,
    "userid": 3,
    "title": "example",
    "details": "example",
    "date": "09/08/2020",
}

// 返回数据
{
    "communityname":"cloud",
    "communityid":1,
    "notice": 
    {
        // 公告1
     	{
    		"noticeid": 1,
			"title": "Yellow lives matter!",
			"details": "我们社区真是太厉害啦!",
			"date": "09/08/2020",
			"username": "pp"
		},
	    // 公告2
		{
        	"noticeid": 2,
			"title": "Yellow lives matter!",
			"details": "我们社区真是太厉害啦!",
			"date": "09/08/2020",
			"username": "pp"
		}   
    }
}
```



## 论坛

### 帖子

- url:`localhost:8080/post?communityid=`
- method:`GET`
- data:`communityid`
- return:`communityname`,`title`,`details`,`date`,`username`

```
// 发送数据
{
	"communityid":1
}

// 返回数据
{
	"communityname":"cloud",
    "communityid":1,
    "post": 
    {
        // 帖子1
     	{
    		"noticeid": 1,
			"title": "Yellow lives matter!",
			"details": "我们社区真是太厉害啦!",
			"date": "09/08/2020",
			"username": "pp"
		},
	    // 帖子2
		{
        	"noticeid": 2,
			"title": "Yellow lives matter!",
			"details": "我们社区真是太厉害啦!",
			"date": "09/08/2020",
			"username": "pp"
		}   
    }
}
```

### 帖子发布

- url:`localhost:8080/post/publish`
- method: `POST`
- data:`communityid`,`postid`,`title`,`details`,`userid`
- return: `communityname`,`title`,`details`,`date`,`username`

```
// 发送数据
{
    "communityid": 1,
    "postid": 2,
    "userid": 3,
    "title": "example",
    "details": "example",
}

// 返回数据
{
    "communityname":"cloud",
    "communityid":1,
    "post": 
    {
        // 公告1
     	{
    		"postid": 1,
			"title": "Yellow lives matter!",
			"details": "我们社区真是太厉害啦!",
			"date": "09/08/2020",
			"username": "pp"
		},
	    // 公告2
		{
        	"postid": 2,
			"title": "Yellow lives matter!",
			"details": "我们社区真是太厉害啦!",
			"date": "09/08/2020",
			"username": "pp"
		}   
    }
}
```

### 帖子修改

- url:`localhost:8080/post/update`
- method: `GET`
- data:`communityid`,`postid`,`title`,`details`
- return: `communityname`,`title`,`details`,`date`,`username`

```
// 发送数据
{
    "communityid": 1,
    "postid": 2,
    "userid": 3,
    "title": "example",
    "details": "example",
    "date": "09/08/2020",
}

// 返回数据
{
	"communityname":"cloud",
    	"communityid":1,
    	"post": 
    	{
        	// 帖子1
     		{
    			"postid": 1,
			"title": "Yellow lives matter!",
			"details": "我们社区真是太厉害啦!",
			"date": "09/08/2020",
			"username": "pp"
		},
		// 帖子2
		{
        		"postid": 2,
			"title": "Yellow lives matter!",
			"details": "我们社区真是太厉害啦!",
			"date": "09/08/2020",
			"username": "pp"
		}   
    	}
}
```



## 投诉区

### 投诉

- url:`localhost:8080/complaint?complaintid=`
- method:`GET`
- data:`communityid`
- return:`communityname`,`title`,`details`,`date`,`username`

```
// 发送数据
{
	"communityid":1
}

// 返回数据
{
	"communityname":"cloud",
	"communityid":1,
	"post": 
	{
        	// 投诉1
     		{
    			"complaintid": 1,
			"title": "Yellow lives matter!",
			"details": "我们社区真是太厉害啦!",
			"date": "09/08/2020",
			"username": "pp"
		},
		// 投诉2
		{
        		"complaintid": 2,
			"title": "Yellow lives matter!",
			"details": "我们社区真是太厉害啦!",
			"date": "09/08/2020",
			"username": "pp"
		}   
   	}
}
```

### 发布投诉

- url:`localhost:8080/post/publish`
- method: `POST`
- data:`communityid`,`postid`,`title`,`details`,`userid`
- return: `communityname`,`title`,`details`,`date`,`username`

```
// 发送数据
{
    "communityid": 1,
    "complaintid": 2,
    "userid": 3,
    "title": "example",
    "details": "example",
}

// 返回数据
{
    "communityname":"cloud",
    "communityid":1,
    "complaint": 
    {
        // 投诉1
     	{
    		"complaintid": 1,
		"title": "Yellow lives matter!",
		"details": "我们社区真是太厉害啦!",
		"date": "09/08/2020",
		"username": "pp"
	},
	// 投诉2
	{
        	"complaintid": 2,
		"title": "Yellow lives matter!",
		"details": "我们社区真是太厉害啦!",
		"date": "09/08/2020",
		"username": "pp"
	}   
    }
}
```

### 

## 健康

## 缴费
