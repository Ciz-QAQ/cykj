package com.car.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")//filterName = "EncodeFilter"//all web
//@WebFilter({"/html/*"})//start with html
//@WebFilter("*.html")//end with html
public class EncodeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {


        if (((HttpServletRequest)req).getRequestURI().contains(".css")||((HttpServletRequest)req).getRequestURI().contains(".js")){
            chain.doFilter(req, resp);//放行，to next filter
            System.out.println("Filtering");
        }else{
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
            chain.doFilter(req, resp);//放行，to next filter
            System.out.println("Filtered");
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
