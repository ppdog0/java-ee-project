package web.util;

import entity.*;

import netscape.javascript.JSObject;

import javax.json.JsonObject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;

public class JsonParser {
    private User user;
    private Bill bill;
    private Post post;
    private Notice notice;
    private Health health;
    private Complaint complaint;
    private Community community;

    private String jsonArea;

    // 使用jsonb
    public void toJson() {
        JsonbConfig config = new JsonbConfig()
                .withFormatting(true);
        Jsonb jsonb = JsonbBuilder.create(config);

        //to json 转化为json, 保存在json字符串中
        this.jsonArea = jsonb.toJson(this.user);

        //from json 处理json
        User example = jsonb.fromJson(this.jsonArea, User.class);
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Bill getBill() {
        return bill;
    }
    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
