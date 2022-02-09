package com.hz.crm.web.filter;

import com.hz.crm.settings.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

         HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        HttpSession session = request.getSession(false);
         String contextPath = request.getContextPath();
         String servletPath = request.getServletPath();
        System.out.println(servletPath+"====");
            if ("/setting/user/login.do".equals(servletPath) ||  "/login.jsp".equals(servletPath)) {
                    chain.doFilter(req,resp);
            }else if(session.getAttribute("user") != null){
                chain.doFilter(req,resp);

            }else {
                response.sendRedirect(contextPath+"/login.jsp");
            }


    }
}
