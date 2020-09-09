package ejb;

import entity.Community;
import entity.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class ConfigBean {

    @EJB
    private RequestBean request;

    @PostConstruct
    public void createData() {
        // 账户与密码
        Map<String, String> userInfo = new HashMap();
        List<User> users = new ArrayList();
        userInfo.put("xiaoming", "xiaoming");
        userInfo.put("donaldtrump", "donaldtrump");
        userInfo.put("sakamoto", "sakamoto");
        userInfo.keySet().forEach(u -> {
            request.createUser(userInfo.get(u), u);
            users.add(request.findUser(u));
        });
        
        // 社区
        List<String> communitiesInfo = new ArrayList();
        communitiesInfo.add("Generals' Tomb");
        communitiesInfo.add("Princesses' Tomb");
        communitiesInfo.add("3rd Floor");
        communitiesInfo.forEach(c -> {
            request.createCommunity(c);
        });
        
        // 通知
        Map<String, String> noticesInfo = new HashMap();
        noticesInfo.put("我们社区真是太厉害啦!", "小破站居然跑起来啦!");
        noticesInfo.put("母猪产量喜翻倍！", "今年，在四川农业大学母猪学院的李教授指导下，我市母猪产量喜翻倍！");
        noticesInfo.put("明天开始每晚11点半断电", "全区一盘棋，我小区11点半准时断电。");
        noticesInfo.keySet().forEach(title -> {
            request.createNotice(users.get(0).getId(), title, 
                    noticesInfo.get(title), request.searchCommunityId(communitiesInfo.get(0)));
        });
        
        // 论坛
        Map<String, String> postsInfo = new HashMap();
        postsInfo.put("Make Generals' Tomb Great Again", "Vote me this November!");
        postsInfo.put("哪里有叉烧包卖？", "咱小区不会连叉烧包都莫得卖吧？");
        postsInfo.put("【组队】周末求喜爱福队友一枚", "不会吧不会吧不会真的有人没玩过世界上最逼真的FPS游戏吧？");
        postsInfo.keySet().forEach(title -> {
            request.createPost(users.get(1).getId(), title, 
                    postsInfo.get(title), request.searchCommunityId(communitiesInfo.get(0)));
        });
        
        // 健康
        userInfo.keySet().forEach(u -> {
            request.createHealth(request.searchUserId(u), "健康", "海淀", new Float(36.1));
        });
        
        // 账单
        userInfo.keySet().forEach(u -> {
            request.createBill(request.searchUserId(u), request.searchCommunityId(communitiesInfo.get(0)), 
                    16550, "0202年物业费", "Property", false);
        });

    }

    @PreDestroy
    public void deleteData() {
    }
}
