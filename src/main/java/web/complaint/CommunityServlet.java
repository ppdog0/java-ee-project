/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.complaint;

import ejb.JsonBean;
import ejb.AccountBean;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Logger;
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
@WebServlet(urlPatterns = {"/community"})
public class CommunityServlet extends HttpServlet {

    @EJB
    private AccountBean account;
    @EJB
    private JsonBean jsonbean;
    private static final long serialVersionUID = 7903037019848392847L;

    private static final Logger logger = Logger.getLogger("java.ejb.CommunityServlet");

    private void completeResponse(Integer comId, HttpServletResponse response) throws IOException {

        String jsonString = jsonbean.generateJsonStringCommunity(comId);

        try (PrintWriter out = response.getWriter();) {
            out.print(jsonString);
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // JsonReader reader = Json.createReader(new InputStreamReader(request.getInputStream()));
        // JsonObject object = reader.readObject();
        // String str = object.getString("communityid");
        //Integer comId = comId = object.getInt("communityid");
        jsonbean.initResponseAsJson(response);

        Integer comId = 1;

        completeResponse(comId, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
