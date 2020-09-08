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

```json
// 无发送数据

// 返回数据
{
	{ //社区1
		"communityname": "云社区",
    		"communityid": "1",
		"notice": 
		{
			{ //公告1
    				"postid": 1,
				"title": "Yellow lives matter!",
				"details": "我们社区真是太厉害啦!",
				"data": "09/08/2020",
				"username": "pp"
			},
			{ //公告2
                		"postid": 2,
				"title": "Yellow lives matter!",
				"details": "我们社区真是太厉害啦!",
				"data": "09/08/2020",
				"username": "pp"
			}
		}
		"post": 
		{
			{ // 帖子1
	            		"noticeid": 1,
				"title": "Yellow lives matter!",
				"details": "我们社区真是太厉害啦!",
				"data": "09/08/2020",
				"username": "pp"
			},
			{ // 帖子2
                		"noticeid": 2,
				"title": "Yellow lives matter!",
				"details": "我们社区真是太厉害啦!",
				"data": "09/08/2020",
				"username": "pp"
			}
		}
	},
	{ //社区2
		"communityname": "云社区",
    		"communityid": "1",
		"notice": 
		{
			{ //公告1
    				"postid": 1,
				"title": "Yellow lives matter!",
				"details": "我们社区真是太厉害啦!",
				"data": "09/08/2020",
				"username": "pp"
			},
			{ //公告2
                		"postid": 2,
				"title": "Yellow lives matter!",
				"details": "我们社区真是太厉害啦!",
				"data": "09/08/2020",
				"username": "pp"
			}
		}
		"post": 
		{
			{ // 帖子1
	            		"noticeid": 1,
				"title": "Yellow lives matter!",
				"details": "我们社区真是太厉害啦!",
				"data": "09/08/2020",
				"username": "pp"
			},
			{ // 帖子2
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

```json
// 发送数据
{
    "communityid":1
}

// 返回数据
{
	{ //公告1
    		"noticeid": 1,
		"title": "Yellow lives matter!",
		"details": "我们社区真是太厉害啦!",
		"date": "09/08/2020",
		"username": "pp"
	},
	{ //公告2
        	"noticeid": 2,
		"title": "Yellow lives matter!",
		"details": "我们社区真是太厉害啦!",
		"date": "09/08/2020",
		"username": "pp"
	}
}
```



### 公告修改

- url:`localhost:8080/notice/update?noticeid=?`
- method: `GET`
- data:`communityid`,`noticeid`,`title`,`details`
- return: `communityName`,`title`,`details`,`date`,`username`

```json
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
	{ //公告1
    		"noticeid": 1,
		"title": "Yellow lives matter!",
		"details": "我们社区真是太厉害啦!",
		"date": "09/08/2020",
		"username": "pp"
	},
	{ //公告2
        	"noticeid": 2,
		"title": "Yellow lives matter!",
		"details": "我们社区真是太厉害啦!",
		"date": "09/08/2020",
		"username": "pp"
	}
}
```



## 帖子

### 帖子

