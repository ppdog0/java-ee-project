package web.post;

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

@WebServlet(urlPatterns = "/post/id")
public class PostItemServlet extends HttpServlet {
    @EJB
    private JsonBean jsonbean;

    public void completeResponse(Integer postid, HttpServletResponse response) throws IOException {

        String jsonString = jsonbean.generateJsonStringPostItem(postid);

        try (PrintWriter out = response.getWriter();) {
            out.print(jsonString);
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JsonReader reader = Json.createReader(new InputStreamReader(request.getInputStream()));
        JsonObject object = reader.readObject();
        Integer comId = 1;
        Integer postid = object.getInt("postid");

        jsonbean.initResponseAsJson(response);

        completeResponse(postid, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
