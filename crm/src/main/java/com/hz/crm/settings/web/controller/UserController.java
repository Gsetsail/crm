package com.hz.crm.settings.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hz.crm.exception.LoginException;
import com.hz.crm.settings.domain.User;
import com.hz.crm.settings.service.UserService;
import com.hz.crm.settings.service.impl.UserServiceImpl;
import com.hz.crm.utils.MD5Util;
import com.hz.crm.utils.PrintJson;
import com.hz.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class UserController extends HttpServlet {

   private UserService  userService = (UserService) ServiceFactory.getService(new UserServiceImpl());
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            System.out.println("进入到用户控制器");

            String path = request.getServletPath();
                System.out.println(path);
            if("/setting/user/login.do".equals(path)){
                 doLogin(request,response);
            }else if("/setting/user/xxx.do".equals(path)){
                // doxxx(request,response);
            }
    }

    public void doLogin(HttpServletRequest request, HttpServletResponse response)
             {


             String loginAct = request.getParameter("loginAct");
             String loginPwd = request.getParameter("loginPwd");

                // 将密码的铭文形式转成MD5的密文形式
             loginPwd = MD5Util.getMD5(loginPwd);
             String ip = request.getRemoteAddr();
            System.out.println("ip地址----------"+ip);
             User user = new User();
             user.setLoginAct(loginAct);
             user.setLoginPwd(loginPwd);
             user.setAllowIps(ip);

            try {
                    User use = userService.selectOneByActAndPwd(user);
                // 如果程序执行到此处,说明业务层没有为控制器抛出任何异常,表示登录成功.
                    HttpSession session = request.getSession();
                    session.setAttribute("user",use);
                    PrintJson.printJsonFlag(response,true);
            } catch (Exception e) {
                 e.printStackTrace();
                // 程序执行到此处,说明业务层为我们验证登录失败,为控制层抛出了异常,表示登录失败.
                String msg = e.getMessage();
               Map<String,Object> map = new HashMap<>();
               map.put("success",false);
               map.put("msg",msg);
               PrintJson.printJsonObj(response,map);
            }


    }
}
