/**
 * 功能：
 *      * 登录账户
 *      * 注销账户
 *      * 创建账户
 *      * 加入社区
 *      * 退出社区
 * @author Gwan
 */

package web;

import ejb.RequestBean;
import ejb.AccountStatusBean;
import entity.User;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class AccountBean implements Serializable{
    
    @EJB
    private RequestBean request;
    @EJB
    private AccountStatusBean currentAccount;
    private final int passwordMinLength = 8;
    
    public String signIn(String userName, String password) {
        User user = request.findUser(userName);
        if (user == null) {
            return "signin";
        }
        
        if (user.getUsername().equals(userName) && user.getPassword().equals(password)) {
            currentAccount.signIn(userName, request.searchUserId(userName));
            return "index";     // 如果登录成功，则进入主页 index.xhtml
        } else {
            return "signin";    // 如果登录失败，则重载登录页面 signin.xhtml
        }
    }
    
    public String signOut() {
        // 调用前提：用户已登录。因此，无需检查用户的登录状况。
        currentAccount.signOut();
        return "signin";
    }
    
    public String signUp(String userName, String password, String passwordAgain) {
        User user = request.findUser(userName);
        if (user != null) {
            // 警告：已存在该用户名
            return "signup";
        } else if (password.length() < passwordMinLength) {
            // 警告：密码长度过短，应大于 8 位
            return "signup";
        } else if (!password.equals(passwordAgain)) {
            // 警告：两次密码不相同
            return "signup";
        } else {
            request.createUser(password, userName);
            return "signin";
        }
    }
    
    public String joinCommunity(Integer communityId, Integer ) {
        // 加入社区
        
        return "index";
    }
    
    public String quitCommunity() {
        return "index";
    }
    
    public String createNotice(String noticeTitle, String noticeDetail) {
        // 前提条件：只有管理员，才可调用该方法
        if (noticeTitle.isEmpty()) {
            // 报错：通知标题不可为空
        } else {
            request.createNotice(currentAccount.getUserId(), 
                    noticeTitle, noticeDetail, currentAccount.getCurrentCommunityId());
        }
        return "notice";
    }
    
    public String modifyNotice(Integer noticeId, String noticeTitle, String noticeDetail) {
        // 前提条件：只有管理员，才可调用该方法
        if (noticeTitle.isEmpty()) {
            // 报错：通知标题不可为空
        } else {
            request.updateNotice(noticeId, currentAccount.getUserId(), 
                    noticeTitle, noticeDetail, currentAccount.getCurrentCommunityId());
        }
        return "notice";
    }
    
    public String deleteNotice(Integer noticeId) {
        // 前提条件：只有管理员，才可调用该方法
        // 删除通知
        return "notice";
    }
    
    public String createPost(String postTitle, String postDetail) {
        if (postTitle.isEmpty()) {
            // 报错：通知标题不可为空
        } else {
            request.createPost(currentAccount.getUserId(), 
                    postTitle, postDetail, currentAccount.getCurrentCommunityId());
        }
        return "post";
    }
    
    public String modifyPost(Integer postId, String postTitle, String postDetail) {
        // 前提条件：只有创建者，才可调用该方法
        // Q:管理员怎么办？
        
        if (postTitle.isEmpty()) {
            // 报错：通知标题不可为空
        } else {
            request.updatePost(postId, currentAccount.getUserId(), 
                    currentAccount.getCurrentCommunityId(), postTitle, postDetail);
        }
        return "post";
    }
    
    public String deletePost(Integer postId) {
        // 前提条件：只有创建者，才可调用该方法
        // Q:管理员怎么办？
        request.deletePost(postId, currentAccount.getUserId());
        return "post";
    }
    
    public String createBill(Integer price, String title, String type, Boolean status) {
        // 前提条件：只有管理员，才可调用该方法
        request.createBill(currentAccount.getUserId(), currentAccount.getCurrentCommunityId(), 
                    price, title, type, status);
        return "bill";
    }
    
    public String modifyBill() {
        
    }
    
    public String deleteBill() {
        
    }
    
    
}
