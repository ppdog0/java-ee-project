package ejb;

import ejb.AccountBean;
import entity.Community;
import entity.Complaint;
import entity.Health;
import entity.Notice;
import entity.Post;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gwan
 */
@Stateful
public class JsonBean {

    @EJB
    private AccountBean account;
    private String username;

    public void initResponseAsJson(HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }

    // 调用方：SignInServlet 和 SignOutServlet
    public String generateJsonStringSign(Map<String, Object> map) {
        JsonObjectBuilder modelBuilder = Json.createObjectBuilder();

        map.keySet().forEach(key -> {
            Object value = map.get(key);
            if (value.getClass().equals(String.class)) {
                modelBuilder.add(key, (String) value);
            } else if (value.getClass().equals(Integer.class)) {
                modelBuilder.add(key, (Integer) value);
            } else if (value.getClass().equals(Boolean.class)) {
                modelBuilder.add(key, (Boolean) value);
            }
            
        });

        JsonObject model = modelBuilder.build();

        StringWriter stWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stWriter);) {
            jsonWriter.writeObject(model);
        }
        return stWriter.toString();
    }

    public Set userCommunities(User user) {
        Set<Community> communities = new HashSet<>();
        user.getAdmincommuintys().forEach(com -> {
            communities.add(com);
        });
        user.getHabitantcommunities().forEach(com -> {
            communities.add(com);
        });
        return communities;
    }

    // 调用方：CommunityServlet
    public String generateJsonStringCommunity() {
        // 1. Collect all communities.
        User user = account.findUser(account.getCurrentUsername());
        this.username = user.getUsername();
        Set<Community> communities = userCommunities(user);

        // 2. Iterate every communities.
        JsonArrayBuilder communitiesBuilder = Json.createArrayBuilder();
        communities.forEach((Community com) -> {
            communitiesBuilder.add(communityBuilder(com));
        });

        JsonArray array = communitiesBuilder.build();
        StringWriter stWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stWriter);) {
            jsonWriter.writeArray(array);
        }

        return stWriter.toString();
    }

    public JsonObjectBuilder communityBuilder(Community com) {
        JsonObjectBuilder communityBuilder = Json.createObjectBuilder();
        JsonArrayBuilder noticeAB = Json.createArrayBuilder();
        JsonArrayBuilder postAB = Json.createArrayBuilder();

        List<Notice> notices = account.findAllNotice(Integer.toString(com.getId()));
        notices.forEach(nt -> {
            noticeAB.add(noticeBuilder(nt));
        });

        List<Post> posts = account.findAllPosts(com.getId());
        posts.forEach(pt -> {
            postAB.add(postBuilder(pt));
        });

        communityBuilder.add("communityname", com.getCommunityname())
                .add("communityid", Integer.toString(com.getId()))
                .add("notice", noticeAB)
                .add("post", postAB);

        return communityBuilder;

    }

    public JsonObjectBuilder noticeBuilder(Notice nt) {
        JsonObjectBuilder noticeBuilder = Json.createObjectBuilder()
                .add("noticeid", Integer.toString(nt.getNoticeid()))
                .add("title", nt.getTitle())
                .add("details", nt.getDetails())
                .add("date", AccountBean.mdyNow())
                .add("username", this.username);
        return noticeBuilder;
    }

    public JsonObjectBuilder postBuilder(Post pt) {
        JsonObjectBuilder postBuilder = Json.createObjectBuilder()
                .add("postid", Integer.toString(pt.getPostid()))
                .add("title", pt.getTitle())
                .add("details", pt.getDetails())
                .add("date", AccountBean.mdyNow())
                .add("username", this.username);
        return postBuilder;
    }
    
    public JsonObjectBuilder complaintBuilder(Complaint cp) {
        JsonObjectBuilder complaintBuilder = Json.createObjectBuilder()
                .add("postid", Integer.toString(cp.getComplaintid()))
                .add("title", cp.getTitle())
                .add("details", cp.getDetails())
                .add("date", AccountBean.mdyNow())
                .add("username", this.username);
        return complaintBuilder;
    }

    // 调用方：NoticeBoardServlet, NoticeModifyServlet
    public String generateJsonStringNotice(Integer comId) {
        String comName = account.findCommunityName(comId);
        JsonObjectBuilder communityBuilder = Json.createObjectBuilder();
        JsonArrayBuilder noticeAB = Json.createArrayBuilder();

        List<Notice> notices = account.findAllNotice(Integer.toString(comId));
        notices.forEach(nt -> {
            noticeAB.add(noticeBuilder(nt));
        });
        
        communityBuilder.add("communityname", comName)
                .add("communityid", comId)
                .add("notice", noticeAB);
        
        JsonObject postboardJsonObject = communityBuilder.build();
        StringWriter stWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stWriter);) {
            jsonWriter.writeObject(postboardJsonObject);
        }

        return stWriter.toString();
    }
    
    public String generateJsonStringPost(Integer comId) {
        String comName = account.findCommunityName(comId);
        JsonObjectBuilder communityBuilder = Json.createObjectBuilder();
        JsonArrayBuilder postAB = Json.createArrayBuilder();

        List<Post> posts = account.findAllPosts(comId);
        posts.forEach(pt -> {
            postAB.add(postBuilder(pt));
        });
        
        communityBuilder.add("communityname", comName)
                .add("communityid", comId)
                .add("post", postAB);
        
        JsonObject postboardJsonObject = communityBuilder.build();
        StringWriter stWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stWriter);) {
            jsonWriter.writeObject(postboardJsonObject);
        }

        return stWriter.toString();
    }
    
    public String generateJsonStringComplaint(Integer comId) {
        String comName = account.findCommunityName(comId);
        JsonObjectBuilder complaintBuilder = Json.createObjectBuilder();
        JsonArrayBuilder complaintAB = Json.createArrayBuilder();

        List<Complaint> complaints = account.findComplaints(comId);
        complaints.forEach(cp -> {
            complaintAB.add(complaintBuilder(cp));
        });
        
        complaintBuilder.add("communityname", comName)
                .add("communityid", comId)
                .add("complaint", complaintAB);
        
        JsonObject postboardJsonObject = complaintBuilder.build();
        StringWriter stWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stWriter);) {
            jsonWriter.writeObject(postboardJsonObject);
        }

        return stWriter.toString();
    }
    
    public String generateJsonStringHealth(Integer userId) {
        String userName = account.searchUserName(userId);
        Health health = account.findUserHealth(userId);
        
        JsonObjectBuilder healthBuilder = Json.createObjectBuilder();
        
        healthBuilder.add("healthid", health.getHealthid())
                .add("username", userName)
                .add("status", health.getStatus())
                .add("temperature", health.getTemperature())
                .add("position", health.getPosition())
                .add("date", account.mdyNow());
        
        JsonObject healthJsonObject = healthBuilder.build();
        StringWriter stWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stWriter);) {
            jsonWriter.writeObject(healthJsonObject);
        }

        return stWriter.toString();
    }
    
}
