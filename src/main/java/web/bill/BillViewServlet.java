/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.bill;

import ejb.AccountBean;
import ejb.JsonBean;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Set;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gwan
 */
@WebServlet(urlPatterns = "/bill")
public class BillViewServlet extends HttpServlet {
    
    @EJB
    private JsonBean jsonbean;

    protected void completeResponse(Integer userId, HttpServletResponse response) throws IOException {

        // Set<Integer> comIds = jsonbean.userCommunitiesIds(userId);
        Integer comId = 1;
        String jsonString = jsonbean.generateJsonStringBill(userId, comId);

        try (PrintWriter out = response.getWriter();) {
            out.print(jsonString);
        }
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JsonReader reader = Json.createReader(new InputStreamReader(request.getInputStream()));
        JsonObject object = reader.readObject();
        //Integer userId = Integer.valueOf(object.getString("userid"));
        Integer userId = object.getInt("userid");

        //Integer userId = 2;
        
        jsonbean.initResponseAsJson(response);

        completeResponse(userId, response);
    }

    private void processGetRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userid"));

        //Integer userId = 2;

        jsonbean.initResponseAsJson(response);

        completeResponse(userId, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processGetRequest(request, response);
    }
}
