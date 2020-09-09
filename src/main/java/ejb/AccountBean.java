/**
 * 功能：
 *      * 登录账户
 *      * 注销账户
 *      * 创建账户
 *      * 加入社区
 *      * 退出社区
 * @author Gwan
 */

package ejb;

import ejb.RequestBean;
import entity.Complaint;
import entity.Health;
import entity.Notice;
import entity.Post;
import entity.User;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

@Stateful
@SessionScoped
public class AccountBean implements Serializable{
    
    @EJB private RequestBean request;
    private String currentUsername = null;
    private Integer currentUserid = null;
    private static final long serialVersionUID = 7908187125656392847L;
    
    public boolean hasUser(String userName) {
        User user = request.findUser(userName);
        return user != null;
    }
    
    public boolean rightPassword(String userName, String password) {
        User user = request.findUser(userName);
        return user.getUsername().equals(userName) && user.getPassword().equals(password);
    }
    
    public Integer searchUserId(String username) {
        return this.request.searchUserId(username);
    }
    
    public void signIn(String username) {
        this.setCurrentUsername(username);
        this.currentUserid = request.searchUserId(username);
    }
    
    public boolean legalPassword(String password) {
        return password != null && password.length() > 0;
    }
    
    public boolean legalUsername(String username) {
        return (!hasUser(username)) && username.length() > 4 && username.length() < 21;
    }
    
    public void createUser(String username, String password) {
        request.createUser(password, username);
    }
    
    public User findUser(String username) {
        return request.findUser(username);
    }
    
    public String getCurrentUsername() {
        return this.currentUsername;
    }
    
    private void setCurrentUsername(String username) {
        this.currentUsername = username;
    }
    
    public Integer getCurrentUserid() {
        return this.currentUserid;
    }
    
    private void setCurrentUserid(Integer userid) {
        this.currentUserid = userid;
    }
    
    public List<Notice> findAllNotice(String communityId) {
        return request.findAllNotice(communityId);
    }
    
    public List<Post> findAllPosts(Integer communityId) {
        return request.findAllPosts(communityId);
    }
    
    public static String mdyNow() {
       Date date = new Date();
       SimpleDateFormat ft = new SimpleDateFormat("MM/dd/yyyy");
       return ft.format(date);
    }
    
    public String findCommunityName(Integer communityId) {
        return request.findCommunityName(communityId);
    }
    
    public String searchUserName(Integer userId) {
        return request.searchUserName(userId);
    }
    
    public void createNotice(Integer userId, String title, String text, Integer communityId) {
        createNotice(userId, title, text, communityId);
    }
    
    public void updateNotice(Integer noticeid, Integer userId, String title, String text, Integer communityId) {
        request.updateNotice(noticeid, userId, title, text, communityId);
    }
    
    public void updatePost(Integer postId, Integer userId, Integer communityId, String titile, String text) {
        request.updatePost(postId, userId, communityId, titile, text);
    }

    public void createPost(Integer userId, String title, String text, Integer communityId) {
        request.createPost(userId, title, text, communityId);
    }
    
    public List<Complaint> findComplaints(Integer communityid) {
        return request.findComplaints(communityid);
    }
    
    public void createComplaint(Integer userid, Integer communityid, String title, String details) {
        request.createComplaint(userid, communityid, title, details);
    }

    public void createHealth(Integer userId, String status, String position, float temperature) {
        request.createHealth(userId, status, position, temperature);
    }
    
    public Health findUserHealth(Integer userId) {
        return request.findUserHealth(userId);
    }
    
    public void updateHealth(Integer healthId, String status, Float temperature, String curr_position) {
        request.updateHealth(healthId, status, temperature, curr_position);
    }
}
