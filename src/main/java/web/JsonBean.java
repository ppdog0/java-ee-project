package web;

import ejb.AccountBean;
import entity.Community;
import entity.Notice;
import entity.Post;
import entity.User;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
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
public class JsonBean {

    @EJB
    private AccountBean account;
    private String username;

    protected void initResponseAsJson(HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }

    // 调用方：SignInServlet 和 SignOutServlet
    protected String generateJsonStringSign(Map<String, String> map) {
        JsonObjectBuilder modelBuilder = Json.createObjectBuilder();

        map.keySet().forEach(key -> {
            modelBuilder.add(key, map.get(key));
        });

        JsonObject model = modelBuilder.build();

        StringWriter stWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stWriter);) {
            jsonWriter.writeObject(model);
        }
        return stWriter.toString();
    }

    protected Set userCommunities(User user) {
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
    protected String generateJsonStringCommunity() {
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

    protected JsonObjectBuilder communityBuilder(Community com) {
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

    protected JsonObjectBuilder noticeBuilder(Notice nt) {
        JsonObjectBuilder noticeBuilder = Json.createObjectBuilder()
                .add("noticeid", Integer.toString(nt.getNoticeid()))
                .add("title", nt.getTitle())
                .add("details", nt.getDetails())
                .add("date", AccountBean.mdyNow())
                .add("username", this.username);
        return noticeBuilder;
    }

    protected JsonObjectBuilder postBuilder(Post pt) {
        JsonObjectBuilder postBuilder = Json.createObjectBuilder()
                .add("postid", Integer.toString(pt.getPostid()))
                .add("title", pt.getTitle())
                .add("details", pt.getDetails())
                .add("date", AccountBean.mdyNow())
                .add("username", this.username);
        return postBuilder;
    }

    // 调用方：NoticeBoardServlet, NoticeModifyServlet
    protected String generateJsonStringNotice(Integer comId) {
        String comName = account.findCommunityName(comId);
        JsonObjectBuilder communityBuilder = Json.createObjectBuilder();
        JsonArrayBuilder noticeAB = Json.createArrayBuilder();

        List<Notice> notices = account.findAllNotice(Integer.toString(comId));
        notices.forEach(nt -> {
            noticeAB.add(noticeBuilder(nt));
        });
        
        communityBuilder.add("communityname", comName)
                .add("communityid", Integer.toString(comId))
                .add("notice", noticeAB);
        
        JsonObject postboardJsonObject = communityBuilder.build();
        StringWriter stWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stWriter);) {
            jsonWriter.writeObject(postboardJsonObject);
        }

        return stWriter.toString();
    }
    

}
