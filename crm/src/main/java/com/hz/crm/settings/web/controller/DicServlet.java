package com.hz.crm.settings.web.controller;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DicServlet extends HttpServlet {
    @Override
    public void service(ServletRequest req, ServletResponse resp)
            throws ServletException, IOException {
        System.out.println("进入到字典模块");
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        String path = request.getServletPath();

        if("/settings/Dic/xxx.do".equals(path)){

        }else if("/settings/Dic/xxx.do".equals(path)) {

        }

    }
}
