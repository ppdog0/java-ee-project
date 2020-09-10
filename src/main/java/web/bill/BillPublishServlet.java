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
@WebServlet(urlPatterns = {"/bill/create"})
public class BillPublishServlet extends HttpServlet {
    @EJB
    private AccountBean account;
    @EJB
    private JsonBean jsonbean;

    public void completeResponse(Integer userId, Integer communityId, HttpServletResponse response) throws IOException {

        String jsonString = jsonbean.generateJsonStringBill(userId, communityId);

        try (PrintWriter out = response.getWriter();) {
            out.print(jsonString);
        }
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JsonReader reader = Json.createReader(new InputStreamReader(request.getInputStream()));
        JsonObject object = reader.readObject();
        Integer communityId = 1;
        Integer userId = object.getInt("userid");
        String type = object.getString("type");
        String details = object.getString("details");
        Integer price = object.getInt("price");
        Boolean status = object.getBoolean("status");
        //Boolean status = false;
        
        this.account.createBill(userId, communityId, price, details, type, status);
        
        jsonbean.initResponseAsJson(response);

        completeResponse(userId, communityId, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
