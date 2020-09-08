/**
 * 功能：
 *      * 记录当前账户信息
 * @author Gwan
 */
package ejb;

import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class AccountStatusBean implements Serializable {
    
    // 表示当前已登录的账户名
    // 如果尚未登录，则为 null
    String userName = null;
    Integer userId = null;
    // 表示当前所在社区的id
    Integer currentCommunityId = null;
    
    public void signIn(String userName, Integer userId) {
        this.setUserName(userName);
        this.setUserId(userId);
    }
    
    public void signOut() {
        this.setUserName(null);
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public Integer getUserId() {
        return this.userId;
    }
    
    public void setCurrentCommunityId(Integer currentCommunityId) {
        this.currentCommunityId = currentCommunityId;
    }
    
    public Integer getCurrentCommunityId() {
        return this.currentCommunityId;
    }
}
