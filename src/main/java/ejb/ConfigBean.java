package ejb;

import entity.Community;
import entity.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private static final Logger logger = Logger.getLogger("java.ejb.ConfigBean");

    @PostConstruct
    public void createData() {
        // 社区
        List<String> communitiesInfo = new ArrayList();
        communitiesInfo.add("Generals' Tomb");
        //communitiesInfo.add("Princesses' Tomb");
        //communitiesInfo.add("3rd Floor");
        communitiesInfo.forEach(c -> {
            request.createCommunity(c);
        });

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
        
        Map<String, String> complaintsInfo = new HashMap();
        complaintsInfo.put("楼上为什么天天螺狮粉？", "考虑过饥肠辘辘楼下的感受。");
        complaintsInfo.put("为什么Github还不死？", "张学友提交了*800\n赵薇提交了*1000\n张国荣提交了*666\n王家卫提交了*233\n任正非提交了*1Merge");
        complaintsInfo.put("没人比我们更懂", "干最重的活，睡最少的觉，堆最臭的山，填最多的坑！");
        User[] userArray = new User[users.size()];
        userArray = users.toArray(userArray);
        User u = userArray[0];
        complaintsInfo.keySet().forEach(c -> {
           request.createComplaint(u.getId(), request.searchCommunityId(communitiesInfo.get(0)), c, complaintsInfo.get(c));
        });
        
        

//        Map<String, String> complaintsInfo = new HashMap();
//        complaintsInfo.put("楼上为什么天天螺狮粉？", "考虑过饥肠辘辘楼下的感受。");
//        complaintsInfo.put("为什么Github还不死？", "张学友提交了*800\n赵薇提交了*1000\n张国荣提交了*666\n王家卫提交了*233\n任正非提交了*1Merge");
//        complaintsInfo.put("没人比我们更懂", "干最重的活，睡最少的觉，堆最臭的山，填最多的坑！");
//        User[] userArray = new User[users.size()];
//        userArray = users.toArray(userArray);
//        User u = userArray[0];
//        complaintsInfo.keySet().forEach(c -> {
//            request.createComplaint(u.getId(), request.searchCommunityId(communitiesInfo.get(0)), c, complaintsInfo.get(c));
//        });

//        request.createComplaint(2, 0, "世上和尚这么多", "我决定出家");
//        request.createComplaint(2, 0, "为甚恶魔", "我要崩了");
//        request.createComplaint(2, 0, "爱你一万年，楼上倒垃圾那位", "f***");
    }

    @PreDestroy
    public void deleteData() {
    }
}
