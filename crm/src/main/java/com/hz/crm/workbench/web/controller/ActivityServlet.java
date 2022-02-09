package com.hz.crm.workbench.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hz.crm.settings.domain.User;
import com.hz.crm.settings.service.UserService;
import com.hz.crm.settings.service.impl.UserServiceImpl;
import com.hz.crm.utils.PrintJson;
import com.hz.crm.utils.ServiceFactory;
import com.hz.crm.utils.TransactionInvocationHandler;
import com.hz.crm.utils.UUIDUtil;
import com.hz.crm.vo.PaginationVo;
import com.hz.crm.workbench.domain.Activity;
import com.hz.crm.workbench.service.ActivityService;
import com.hz.crm.workbench.service.impl.ActivityServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Service;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityServlet extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse resp)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        String path = request.getServletPath();

         if("/workbench/activity/getUserList.do".equals(path)){
                doSelect(request,response);
         }else if("/workbench/activity/insertActivity.do".equals(path)){
                insertActivity(request,response);
         }else if("/workbench/activity/pageList.do".equals(path)){
                pageList(request,response);
         }else if("/workbench/activity/deleteActivity.do".equals(path)){
             deleteActivity(request,response);
         }else if("/workbench/activity/getUserListAndActivity.do".equals(path)){
             getUserListAndActivity(request,response);
         }else if ("/workbench/activity/updateActivity.do".equals(path)){
             updateActivity(request,response);
         }
    }

    public void updateActivity(HttpServletRequest request, HttpServletResponse response) {

           String id = request.getParameter("id");
           String name = request.getParameter("name");
           String startDate = request.getParameter("startDate");
           String endDate = request.getParameter("endDate");
           String cost = request.getParameter("cost");
           String description = request.getParameter("description");
           String owner = request.getParameter("owner");
           SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           String editTime = spf.format(new Date());

         User user = (User) request.getSession(false).getAttribute("user");

            Activity activity = new Activity();
           activity.setId(id);
           activity.setName(name);
           activity.setStartDate(startDate);
           activity.setEndDate(endDate);
           activity.setCost(cost);
           activity.setDescription(description);
           activity.setOwner(owner);
           activity.setEditTime(editTime);
           activity.setEditBy(user.getName());
           activity.setCreateBy(user.getCreateBy());
           activity.setCreateTime(user.getCreateTime());
          ActivityService service = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

          boolean flag = service.updateActivityById(activity);
          PrintJson.printJsonFlag(response,flag);

    }

    public void getUserListAndActivity(HttpServletRequest request, HttpServletResponse response) {

                String id = request.getParameter("id");
                ActivityService service = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
                Map<String,Object> map = service.getUserListAndActivity(id);
                PrintJson.printJsonObj(response,map);
    }

    public void deleteActivity(HttpServletRequest request, HttpServletResponse response) {

            ActivityService service = (ActivityService)ServiceFactory.getService(new ActivityServiceImpl());
            String[] id = request.getParameterValues("id");

            boolean flag =  service.deleteActivityById(id);

            PrintJson.printJsonFlag(response,flag);

    }

    public void pageList(HttpServletRequest request, HttpServletResponse response) {

            ActivityService service =(ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

                 String pageNo = request.getParameter("pageNo");
                 String pageSize = request.getParameter("pageSize");
                 String name = request.getParameter("name");
                 String owner = request.getParameter("owner");
                 String startDat = request.getParameter("startDat");
                 String endDate = request.getParameter("endDate");

                 Activity activity = new Activity();

                 activity.setName(name);
                 activity.setOwner(owner);
                 activity.setStartDate(startDat);
                 activity.setEndDate(endDate);

                PaginationVo<Activity> activityList =  service.selectPageList(activity,pageNo,pageSize);

                PrintJson.printJsonObj(response,activityList);


    }

    public void doSelect(HttpServletRequest request, HttpServletResponse response) {

        UserService service = (UserService) ServiceFactory.getService(new UserServiceImpl());

        List<User> userList = service.selectAllUser();

        PrintJson.printJsonObj(response,userList);

    }

    public void insertActivity(HttpServletRequest request, HttpServletResponse response) {

        ActivityService service = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cost = request.getParameter("cost");
        String description = request.getParameter("describe");
        String id = UUIDUtil.getUUID();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = sdf.format(new Date());
        User user = (User)(request.getSession().getAttribute("user"));

        Activity activity = new Activity();
        activity.setOwner(owner);
        activity.setName(name);
        activity.setStartDate(startDate);
        activity.setEndDate(endDate);
        activity.setCost(cost);
        activity.setDescription(description);
        activity.setId(id);
        activity.setCreateTime(createTime);
        activity.setCreateBy(user.getName());


        boolean flag =  service.insertOneActivity(activity);

        PrintJson.printJsonFlag(response,flag);

    }
}
