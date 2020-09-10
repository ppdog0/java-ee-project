/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.health;

import ejb.AccountBean;
import ejb.JsonBean;
import entity.Health;
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
@WebServlet(urlPatterns = {"/health"})
public class HealthViewServlet extends HttpServlet {
    @EJB
    private AccountBean account;
    @EJB
    private JsonBean jsonbean;

    protected void completeResponse(Integer userId, HttpServletResponse response) throws IOException {

        String jsonString = jsonbean.generateJsonStringHealth(userId);

        try (PrintWriter out = response.getWriter();) {
            out.print(jsonString);
        }
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JsonReader reader = Json.createReader(new InputStreamReader(request.getInputStream()));
        JsonObject object = reader.readObject();
        Integer userId = object.getInt("userid");

        jsonbean.initResponseAsJson(response);

        completeResponse(userId, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
