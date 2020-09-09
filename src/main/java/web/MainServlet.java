package web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet()
public class MainServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        resp.setHeader("content-type", "text/html;charset=UTF-8");
        try {
            PrintWriter out = resp.getWriter();
            String resJSON = "[{id:1, name:'奔驰'},{id:2, name:'宝马'}]";
            out.print(resJSON);
            out.flush();
            out.close();
        } catch (IOException e) {

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StringBuffer jb = new StringBuffer();
        String line = null;
        String result = "";
        try {
            //读取输入流到StringBuffer中
            BufferedReader reader = req.getReader();
            while((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {

        }

//        try {
//            // 使用JSONObject的parseObject方法解析JSON字符串
//            JsonObject jsonObject = JsonObject.
//        }
    }
}
