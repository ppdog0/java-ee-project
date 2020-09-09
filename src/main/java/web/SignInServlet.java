package web;

import ejb.JsonBean;
import ejb.AccountBean;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.http.HttpServlet;


/**
 *
 * @author Gwan
 */
@WebServlet(urlPatterns = {"/user/login"})
public class SignInServlet extends HttpServlet {
    @EJB private AccountBean account;
    @EJB private JsonBean jsonbean;
    private static final long serialVersionUID = 7908187019848392847L;
    
    
    private void completeResponse(HttpServletResponse response, String status, Integer userid) throws IOException {
        Map<String, Object> map = new HashMap<>();           
        map.put("status", status);
        if (status.equals("success")) {
            map.put("userid", userid);
            map.put("admin", true);
        }

        String jsonString = jsonbean.generateJsonStringSign(map);

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
        jsonbean.initResponseAsJson(response);

        // 2. validate user's existence and validate password
        if (account.hasUser(username) || !account.rightPassword(username, password)) {
            completeResponse(response, "fail", null);
            return;
        }

        // 3. log in successfully, redirect to index.html
        account.signIn(username);
        completeResponse(response, "success", account.searchUserId(username));
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
