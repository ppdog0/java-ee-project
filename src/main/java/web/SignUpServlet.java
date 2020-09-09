package web;

import ejb.RequestBean;
import entity.User;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonWriter;

/**
 *
 * @author Gwan
 */
@WebServlet(name = "SignInServlet", urlPatterns = {"/login"})
public class SignInServlet extends HttpServlet {

    @EJB
    private RequestBean request;
    private static final long serialVersionUID = 7908187011456392847L;

    private boolean hasUser(String userName) {
        User user = request.findUser(userName);
        return user != null;
    }

    private boolean rightPassword(String userName, String password) {
        User user = request.findUser(userName);
        return user.getUsername().equals(userName) && user.getPassword().equals(password);
    }

    private void initResponseAsJson(HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }

    private String generateJsonString(Map<String, String> map) {
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

    private void completeResponse(HttpServletResponse response, String status, String userid) throws IOException {
        Map<String, String> config = new HashMap<>();           
        config.put("status", status);
        
        if (status.equals("success")) {
            config.put("userid", userid);
        }

        String jsonString = generateJsonString(config);

        try (PrintWriter out = response.getWriter();) {
            out.print(jsonString);
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. json -> userName & password
        JsonReader reader = Json.createReader(new InputStreamReader(request.getInputStream()));
        JsonObject object = reader.readObject();
        String username = object.getString("username");
        String password = object.getString("password");
        initResponseAsJson(response);

        // 2. validate user's existence
        if (!hasUser(username)) {
            completeResponse(response, "fail", null);
            return;
        }

        // 3. validate password
        if (!rightPassword(username, password)) {
            completeResponse(response, "fail", null);
            return;
        }

        // 4. log in successfully, redirect to index.html
        completeResponse(response, "success", this.request.searchUserId(username).toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
