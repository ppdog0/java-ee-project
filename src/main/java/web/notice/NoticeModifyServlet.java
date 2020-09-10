/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.notice;

import ejb.JsonBean;
import ejb.AccountBean;
import web.notice.NoticeBoardServlet;

import java.io.IOException;
import java.io.InputStreamReader;
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
@WebServlet(urlPatterns = {"/notice/update"})
public class NoticeModifyServlet extends HttpServlet {

    @EJB
    private AccountBean account;
    @EJB
    private JsonBean jsonbean;
    @EJB
    private NoticeBoardServlet nbs;
    private static final long serialVersionUID = 7903037019848392847L;


    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JsonReader reader = Json.createReader(new InputStreamReader(request.getInputStream()));
        JsonObject object = reader.readObject();
        Integer comId = object.getInt("communityid");
        Integer noticeId = object.getInt("noticeid");
        Integer userId = object.getInt("userid");
        String title = object.getString("title");
        String details = object.getString("details");
        
        account.updateNotice(noticeId, userId, title, details, comId);

        jsonbean.initResponseAsJson(response);

        nbs.completeResponse(comId, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
