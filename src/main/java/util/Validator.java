package util;
import ejb.RequestBean;

import javax.ejb.EJB;

public class Validator {
    @EJB
    private RequestBean request;
    public String userNameValidate(String username) {
        if (username.length() < 5 || username.length() > 20) {
            return "用户名长度应为5-20个字符";
        }
        if (request.searchUserId(username)!=null) {
            return "该用户名已注册";
        } else return "用户名合法";
    }

    public bool adminValidate(String username,Integer communityid){
        if
    }
}