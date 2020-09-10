package ejb;

import entity.Bill;
import entity.Community;
import entity.Complaint;
import entity.Health;
import entity.Notice;
import entity.Post;
import entity.User;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    // private String username;

    private static final Logger logger = Logger.getLogger("java.ejb.JsonBean");

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

    public Set userCommunitiesIds(Integer userId) {
        User user = account.findUser(account.searchUserName(userId));
        Set<Integer> communities = new HashSet<>();
        user.getAdmincommuintys().forEach(com -> {
            communities.add(com.getId());
        });
        user.getHabitantcommunities().forEach(com -> {
            communities.add(com.getId());
        });
        return communities;
    }

    // 调用方：CommunityServlet
    public String generateJsonStringCommunity(Integer comId) {
        // 1. Collect all communities.
        // User user = account.findUser(account.getCurrentUsername());
        // this.username = user.getUsername();

        JsonObject cbObject = communityBuilder(comId).build();
        StringWriter stWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stWriter);) {
            jsonWriter.writeObject(cbObject);
        }

        return stWriter.toString();
    }
    

    public JsonObjectBuilder communityBuilder(Integer comId) {
        JsonObjectBuilder communityBuilder = Json.createObjectBuilder();
        JsonArrayBuilder noticeAB = Json.createArrayBuilder();
        JsonArrayBuilder postAB = Json.createArrayBuilder();

        List<Notice> notices = account.findAllNotice(comId);
        notices.forEach(nt -> {
            noticeAB.add(noticeBuilder(nt));
        });

        List<Post> posts = account.findAllPosts(comId);
        posts.forEach(pt -> {
            postAB.add(postBuilder(pt));
        });

        communityBuilder.add("communityname", account.findCommunityName(comId))
                .add("notice", noticeAB)
                .add("post", postAB);

        return communityBuilder;

    }

    public JsonObjectBuilder noticeBuilder(Notice nt) {
        JsonObjectBuilder noticeBuilder = Json.createObjectBuilder()
                .add("noticeid", Integer.toString(nt.getNoticeid()))
                .add("title", nt.getTitle())
                .add("details", nt.getDetails())
                .add("date", AccountBean.mdyNow());
//                .add("username", this.username);
        return noticeBuilder;
    }

    public JsonObjectBuilder postBuilder(Post pt) {
        JsonObjectBuilder postBuilder = Json.createObjectBuilder()
                .add("postid", Integer.toString(pt.getPostid()))
                .add("title", pt.getTitle())
                .add("details", pt.getDetails())
                .add("date", AccountBean.mdyNow())
                .add("username", pt.getUser().getUsername());
//                .add("username", this.username);
        return postBuilder;
    }

    public JsonObjectBuilder complaintBuilder(Complaint cp) {
        JsonObjectBuilder complaintBuilder = Json.createObjectBuilder()
                .add("postid", Integer.toString(cp.getComplaintid()))
                .add("title", cp.getTitle())
                .add("details", cp.getDetails())
                .add("date", AccountBean.mdyNow());
               //.add("username", this.username);
        return complaintBuilder;
    }

    public JsonObjectBuilder billBuilder(Bill bill) {
        JsonObjectBuilder billBuilder = Json.createObjectBuilder()
                .add("billid", Integer.toString(bill.getBillid()))
                .add("title", bill.getTitle())
                .add("details", bill.getDetails())
                .add("price", bill.getPrice())
                .add("status", bill.getSatus())
                .add("date", AccountBean.mdyNow());
        return billBuilder;
    }

    // 调用方：NoticeBoardServlet, NoticeModifyServlet
    public String generateJsonStringNotice(Integer comId) {
        String comName = account.findCommunityName(comId);
        JsonObjectBuilder communityBuilder = Json.createObjectBuilder();
        JsonArrayBuilder noticeAB = Json.createArrayBuilder();

        List<Notice> notices = account.findAllNotice(comId);
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

    public String generateJsonStringHealth(Integer healthid) {
        Health health = account.findUserHealth(healthid);

        //logger.log(Level.INFO, "find health {0}--{1}", new Object[]{health.getHealthid(), health.getUser().getUsername()});

        JsonObjectBuilder healthBuilder = Json.createObjectBuilder();

        healthBuilder.add("healthid", health.getHealthid())
                .add("username", health.getUser().getUsername())
                .add("status", health.getStatus())
                .add("temperature", health.getTemperature())
                .add("position", health.getPosition())
                .add("date", AccountBean.mdyNow());


        JsonObject healthJsonObject = healthBuilder.build();
        StringWriter stWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stWriter);) {
            jsonWriter.writeObject(healthJsonObject);
        }

        return stWriter.toString();
    }

    public String generateJsonStringBill(Integer userId, Integer comId) {
        List<Bill> bills = account.findBills(comId, userId);

        JsonArrayBuilder billAB = Json.createArrayBuilder();

        bills.forEach(bill -> {
            billAB.add(billBuilder(bill));
        });

        JsonArray billJsonArray = billAB.build();
        StringWriter stWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stWriter);) {
            jsonWriter.writeArray(billJsonArray);
        }

        return stWriter.toString();
    }

//    public String generateJsonStringBill(Integer userId, Set<Integer> comIds) {
//
//        JsonArrayBuilder billAB = Json.createArrayBuilder();
//        comIds.forEach(comId -> {
//            List<Bill> bills = account.findBills(comId, userId);
//
//            bills.forEach(bill -> {
//                billAB.add(billBuilder(bill));
//            });
//
//        });
//
//        JsonArray billJsonArray = billAB.build();
//        StringWriter stWriter = new StringWriter();
//        try (JsonWriter jsonWriter = Json.createWriter(stWriter);) {
//            jsonWriter.writeArray(billJsonArray);
//        }
//        return stWriter.toString();
//    }

//    public String generateJsonStringOrder(Integer userId, Integer communityId) {
//        List<Order> orders = account.findOrders(userId);
//
//        JsonArrayBuilder orderAB = Json.createArrayBuilder();
//
//        orders.forEach(order -> {
//            orderAB.add(orderBuilder(order, true));
//        });
//
//        JsonArray orderJsonArray = orderAB.build();
//        StringWriter stWriter = new StringWriter();
//        try (JsonWriter jsonWriter = Json.createWriter(stWriter);) {
//            jsonWriter.writeArray(orderJsonArray);
//        }
//
//        return stWriter.toString();
//    }

}
