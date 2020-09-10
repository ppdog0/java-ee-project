package web.notice;

import ejb.JsonBean;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/notice/id")
public class NoticeItemServlet extends HttpServlet {
    @EJB
    private JsonBean jsonbean;

    public void completeResponse(Integer noticeid, HttpServletResponse response) throws IOException {

        String jsonString = jsonbean.generateJsonStringNoticeItem(noticeid);

        try (PrintWriter out = response.getWriter();) {
            out.print(jsonString);
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JsonReader reader = Json.createReader(new InputStreamReader(request.getInputStream()));
        JsonObject object = reader.readObject();
        Integer comId = 1;
        Integer noticeid = object.getInt("noticeid");

        jsonbean.initResponseAsJson(response);

        completeResponse(noticeid, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
