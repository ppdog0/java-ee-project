/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gwan
 */
/*
public class NoticeBoardServlet extends HttpServlet {
    @EJB
    private AccountBean account;
    @EJB
    private JsonBean jsonbean;
    private Integer comId;
    private String comName;
    private static final long serialVersionUID = 7903037019848392847L;

    private void completeResponse(HttpServletResponse response) throws IOException {

        String jsonString = jsonbean.generateJsonStringNotice(account);

        try (PrintWriter out = response.getWriter();) {
            out.print(jsonString);
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JsonReader reader = Json.createReader(new InputStreamReader(request.getInputStream()));
        JsonObject object = reader.readObject();
        comId = object.getInt("communityid");
        comName = account.findCommunityName(comId);
        
        jsonbean.initResponseAsJson(response);

        completeResponse(comId, comName, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
*/