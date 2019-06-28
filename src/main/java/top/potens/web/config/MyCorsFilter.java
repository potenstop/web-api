package top.potens.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className CorsFilter
 * @projectName web-api
 * @date: 2019/6/24 16:10
 */
@Configuration
@WebFilter(urlPatterns = "/*", filterName = "validate")
public class MyCorsFilter implements Filter {
    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        res.addHeader("Access-Control-Allow-Credentials", "true");
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Access-Control-Allow-Methods", "*");
        res.addHeader("Access-Control-Allow-Headers", "*");
        if (HttpMethod.OPTIONS.name().equals(req.getMethod())) {
            response.getWriter().println("ok");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
