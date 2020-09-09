/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

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
@WebServlet
public class OrderPublishServlet extends HttpServlet {
    @EJB
    private AccountBean account;
    @EJB
    private JsonBean jsonbean;
    @EJB
    private static final long serialVersionUID = 7903037019848392847L;

    protected void completeResponse(Integer userId, Integer communityId, HttpServletResponse response) throws IOException {

        String jsonString = jsonbean.generateJsonStringOrder(userId, communityId);

        try (PrintWriter out = response.getWriter();) {
            out.print(jsonString);
        }
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JsonReader reader = Json.createReader(new InputStreamReader(request.getInputStream()));
        JsonObject object = reader.readObject();
        Integer userId = object.getInt("userid");
        Integer agentId = object.getInt("agentId");
        Integer storeId = object.getInt("storeId");
        Integer comId = object.getInt("communityId");
        String good = object.getString("good");
        String status = Boolean.toString(object.getBoolean("status"));
        
        this.account.createOrder(userId, comId, storeId, agentId, good, status);
        
        jsonbean.initResponseAsJson(response);

        completeResponse(userId, comId, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
