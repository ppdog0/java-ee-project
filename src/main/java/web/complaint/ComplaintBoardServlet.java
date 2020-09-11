/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.complaint;

import ejb.JsonBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gwan
 */
@Stateful
@WebServlet(urlPatterns = {"/complaint"})
public class ComplaintBoardServlet extends HttpServlet {
    @EJB
    private JsonBean jsonbean;
    private static final long serialVersionUID = 7903037019848392847L;

    public void completeResponse(Integer comId, HttpServletResponse response) throws IOException {

        String jsonString = jsonbean.generateJsonStringComplaint(comId);

        try (PrintWriter out = response.getWriter();) {
            out.print(jsonString);
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        JsonReader reader = Json.createReader(new InputStreamReader(request.getInputStream()));
//        JsonObject object = reader.readObject();
        Integer comId = 1;

        jsonbean.initResponseAsJson(response);

        completeResponse(comId, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
