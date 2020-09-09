# API

<!-- TOC -->

- [API](#api)
    - [用户](#%E7%94%A8%E6%88%B7)
        - [用户登录](#%E7%94%A8%E6%88%B7%E7%99%BB%E5%BD%95)
        - [用户注册](#%E7%94%A8%E6%88%B7%E6%B3%A8%E5%86%8C)
    - [社区](#%E7%A4%BE%E5%8C%BA)
        - [社区页面](#%E7%A4%BE%E5%8C%BA%E9%A1%B5%E9%9D%A2)
        - [加入社区](#%E5%8A%A0%E5%85%A5%E7%A4%BE%E5%8C%BA)
        - [创建社区](#%E5%88%9B%E5%BB%BA%E7%A4%BE%E5%8C%BA)
    - [公告](#%E5%85%AC%E5%91%8A)
        - [公告栏](#%E5%85%AC%E5%91%8A%E6%A0%8F)
        - [发布公告](#%E5%8F%91%E5%B8%83%E5%85%AC%E5%91%8A)
        - [公告修改](#%E5%85%AC%E5%91%8A%E4%BF%AE%E6%94%B9)
    - [论坛](#%E8%AE%BA%E5%9D%9B)
        - [帖子](#%E5%B8%96%E5%AD%90)
        - [帖子发布](#%E5%B8%96%E5%AD%90%E5%8F%91%E5%B8%83)
        - [帖子修改](#%E5%B8%96%E5%AD%90%E4%BF%AE%E6%94%B9)
    - [投诉区](#%E6%8A%95%E8%AF%89%E5%8C%BA)
        - [投诉](#%E6%8A%95%E8%AF%89)
        - [发布投诉](#%E5%8F%91%E5%B8%83%E6%8A%95%E8%AF%89)
    - [健康](#%E5%81%A5%E5%BA%B7)
        - [创建记录](#%E5%88%9B%E5%BB%BA%E8%AE%B0%E5%BD%95)
        - [查看记录](#%E6%9F%A5%E7%9C%8B%E8%AE%B0%E5%BD%95)
        - [更新记录](#%E6%9B%B4%E6%96%B0%E8%AE%B0%E5%BD%95)
    - [缴费](#%E7%BC%B4%E8%B4%B9)
        - [发布账单](#%E5%8F%91%E5%B8%83%E8%B4%A6%E5%8D%95)
        - [查看账单](#%E6%9F%A5%E7%9C%8B%E8%B4%A6%E5%8D%95)
        - [更新账单](#%E6%9B%B4%E6%96%B0%E8%B4%A6%E5%8D%95)
    - [订单](#%E8%AE%A2%E5%8D%95)
        - [发布订单](#%E5%8F%91%E5%B8%83%E8%AE%A2%E5%8D%95)
        - [查看订单](#%E6%9F%A5%E7%9C%8B%E8%AE%A2%E5%8D%95)

<!-- /TOC -->

## 用户

### 用户登录

- url:`localhost:8080/user/login`
- method:`POST`
- data:`username`,`passord`
- return:`status`,`userid`, `admin`

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
    "userid": 1,
    "admin": true
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

- url:`localhost:8080/community?userid=`
- method: `GET`
- data:`userid`
- return: `communityname`,`title`,`details`,`date`,`username`

```
// 发送数据
{
	"userid": 1
}

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

### 加入社区

- url:`localhost:8080/community/add`
- method: `POST`
- data:`userid`, `communityid`
- return: `status`, `username`, `communityname`

```
// 发送数据
{
	"userid": 1,
	"communityid": 2
}

// 接收数据
{
	"username": "pp",
	"communityname": "BUPT",
	"status": "success"
}
```

### 创建社区

- url:`localhost:8080/community/create`
- method: `POST`
- data:`userid`, `communityname`
- return: `status`, `username`, `communityid`

```
// 发送数据
{
	"userid": 1,
	"communityname": "BUPT"
}

// 接收数据
{
	"username": "pp", (admin 管理员)
	"communityid": 2,
	"status": "success"
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

- url:`localhost:8080/notice/publish`
- method: `POST`
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

- url:`localhost:8080/notice/update`
- method: `POST`
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
- method: `POST`
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

### 发布投诉

- url:`localhost:8080/post/publish`
- method: `POST`
- data:`communityid`,`title`,`details`,`userid`
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



## 健康

### 创建记录

url: `localhost:8080/health/create`

method: `POST`

data: `userid`, `status`, `temporature`, `position`

return:`healthid`, `username`, `status`, `temporature`, `position`, `date`

```
// 发送数据
{
	"userid": 1,
	"status": "healthy",
	"temporture": "36.5",
	"position": "Beijing",
}

// 返回数据
{
	"healthid": 1,
	"username": "pp",
	"status": "healthy",
	"temporture": "36.5",
	"position": "Beijing",
	"date": "09/08/2020"
}
```

### 查看记录

url: `localhost:8080/health?userid=`

method: `GET`

data: `userid`

return: `username`, `status`, `temporature`, `position`, `date`

```
// 发送数据
{
	"userid": 1
}

// 返回数据
{
    "healthid": 1,
	"username": "pp",
	"status": "healthy",
	"temporature": "36.5",
	"position": "Beijing",
	"date": "09/08/2020"
}
```

### 更新记录

url:  `localhost:8080/health/update`

method:  `POST`

data:  `healthid`, `status`, `temporature`, `position`, `date`

return:  `healthid`, `username`, `status`, `temporature`, `position`, `date`

```
// 发送数据
{
	"healthid": 1,
	"status": "healthy",
	"temporture": "36.5",
	"position": "Beijing"
}

// 返回数据
{
	"healthid": 1,
	"username": "pp",
	"status": "healthy",
	"temporture": "36.5",
	"position": "Beijing",
	"date": "09/08/2020"
}
```



## 缴费

### 发布账单

url: `localhost:8080/bill/create`

method: `POST`

data: `userid`, `communityid`,`type`,`details`, `price`, `status`

return:`billid`, `username`, `communityid`,`type`,`details`, `price`, `status`, `date`

```
// 发送数据
{
	"userid": 1,
	"communityid": 2,
	"type": "tuition",
	"details": "grade three"
	"price": 16550,
	"status": false,
}

// 返回数据
{
	"billid": 1,
	"username": "pp",
	"communityname": "BUPT",
	"type": "tuition",
	"details": "grade three"
	"price": 16550,
	"status": false,
	"date": "08/28/2020"
}
```

### 查看账单

url: `localhost:8080/health?userid=`

method: `GET`

data: `userid`

return: `billid`, `username`, `communityid`, `type`, `details`, `price`, `status`, `date`

```
// 发送数据
{
	"userid": 1
}

// 返回数据
{
	{
		"billid": 1,
		"username": "pp",
		"communityname": "BUPT",
		"type": "tuition",
		"details": "grade three"
		"price": 16000,
		"status": false,
		"date": "08/28/2020"
	},
	{
		"billid": 2,
		"username": "pp",
		"communityname": "BUPT",
		"type": "dormitory",
		"details": "grade three"
		"price": 550,
		"status": false,
		"date": "08/28/2020"
	}
}
```

### 更新账单

url:  `localhost:8080/health/update`

method:  `POST`

data: `billid`,   `status`

return:`billid`, `username`, `communityid`, `type`, `details`, `price`, `status`, `date`

```
// 发送数据
{
	"billid": 1,
	"status": "true",
	"communityid": 2
}

// 返回数据
{
	"billid": 1,
	"username": "pp",
	"communityname": "BUPT",
	"type": "tuition",
	"details": "grade three"
	"price": 16000,
	"status": true,
	"date": "08/28/2020"
}
```



## 订单

### 发布订单

url: `localhost:8080/order/create`

method: `POST`

data: `userid`, `agentid`, `storeid`, `communityid`, `good`

return:`orderid`, `username`, `agentname`,`storename`, `communityname`, `phonenumber`, `price`, `date`

```
// 发送数据
{
	"userid": 1,
	"agentid": 2,
	"storeid": 3,
	"good": "food, fruits"
}

// 返回数据
{
	"orderid": 2,
	"username": "pp",
	"agentname": "mm",
	"storename": "BUPT",
	"communityname": "hotel",
	"phonenumber": 1234567891,
	"price": 60,
	"date": "09/08/2020"
}
```

### 查看订单

url: `localhost:8080/order?orderid=`

method: `GET`

data: `orderid`

return:`username`, `agentname`,`storename`,`phonenumber`, `price`, `date`

```
// 发送数据
{
	"orderid": 1
}

// 返回数据
{
	"username": "pp",
	"agentname": "mm",
	"storename": "BUPT",
	"communityname": "hotel",
	"phonenumber": 1234567891,
	"price": 60,
	"date": "09/08/2020"
}
```
