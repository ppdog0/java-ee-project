package web.util;

import ejb.RequestBean;
import entity.User;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Validator {
    @PersistenceContext
    private EntityManager em;
    @EJB
    private RequestBean request;

    public String userNameValidate(String username) {
        if (username.length() < 5 || username.length() > 20) {
            return "用户名长度应为5-20个字符";
        }
        if (request.searchUserId(username) != null) {
            return "该用户名已注册";
        } else return "用户名合法";
    }

//    public String communityNameValidate(String communityname) {
//        if (communityname.length() < 5 || communityname.length() > 20) {
//            return "用户名长度应为5-20个字符";
//        }
//        if (request.searchUserId(communityname) != null) {
//            return "该用户名已注册";
//        } else return "用户名合法";
//    }

    public boolean userLogIn(String username, String password) {
        Integer userid = request.searchUserId(username);
        User user = em.find(User.class, userid);
        if (user.getPassword() == password)
            return true;
        else
            return false;
    }


//    public bool adminValidate(String username,Integer communityid){
//        if
//    }
}