package com.hz.crm.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse reps, FilterChain filterChain)
            throws IOException, ServletException {

            req.setCharacterEncoding("utf-8");
            reps.setContentType("application/json;charset=utf-8");

            filterChain.doFilter(req, reps);

    }
}
