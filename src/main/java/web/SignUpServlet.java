package web;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
import javax.json.JsonReader;

/**
 *
 * @author Gwan
 */
@WebServlet(name = "SignUpServlet")
public class SignUpServlet extends HttpServlet {

    @EJB
    private AccountBean account;
    @EJB
    private JsonBean jsonbean;
    private static final long serialVersionUID = 7908187011456392847L;

    
    private void completeResponse(HttpServletResponse response, String status) throws IOException {
        Map<String, String> map = new HashMap<>();           
        map.put("status", status);

        String jsonString = jsonbean.generateJsonString(map);

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

        // 2. validate user's existence and password
        if (!account.legalUsername(username) || !account.legalPassword(password)) {
            completeResponse(response, "fail");
            return;
        }
        
        // 3. create user
        account.createUser(username, password);
        // 4. sign up successfully, redirect to login.html
        completeResponse(response, "success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
