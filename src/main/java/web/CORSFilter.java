package web;

import javax.persistence.PersistenceContext;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * [Project]:whymoy  <br/>
 * [Email]:moy25@foxmail.com  <br/>
 * [Date]:2018/3/14  <br/>
 * [Description]:  <br/>
 * 允许指定ip使用Ajax跨域调用
 *
 * @author YeXiangYang
 */
@WebFilter(value = "/*")
class CORSFilter implements Filter {

    private static final Logger logger = Logger.getLogger("java.entity.WebFilter");
    private static String ALLOW_ORIGIN = "";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ALLOW_ORIGIN = filterConfig.getInitParameter("origin");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//            response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,Authorization");
            response.setHeader("Access-Control-Allow-Headers", " Origin, X-Requested-With, Content-Type, Accept");
            response.setHeader("Access-Control-Allow-Credentials", "true");
//            response.setHeader("Access-Control-Allow-Origin", "*");
        filterChain.doFilter(servletRequest, servletResponse);
    }
    @Override
    public void destroy() {
    }
}