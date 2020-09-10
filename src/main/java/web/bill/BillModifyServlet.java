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
@WebServlet(name = "BillModifyServlet")
public class BillModifyServlet extends HttpServlet {

    @EJB
    private AccountBean account;
    @EJB
    private JsonBean jsonbean;
    private static final long serialVersionUID = 7903037019848392847L;

    protected void completeResponse(Integer userId, HttpServletResponse response) throws IOException {

        //Integer comId = jsonbean.userCommunitiesIds(userId);
        //String jsonString = jsonbean.generateJsonStringBill(userId, comId);

//        try (PrintWriter out = response.getWriter();) {
//            out.print(jsonString);
//        }
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JsonReader reader = Json.createReader(new InputStreamReader(request.getInputStream()));
        JsonObject object = reader.readObject();
        Integer billId = object.getInt("billid");
        Boolean status = object.getBoolean("status");
        Integer userId = object.getInt("userid");
        
        account.updateBill(billId, userId, status);

        jsonbean.initResponseAsJson(response);

        completeResponse(userId, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
