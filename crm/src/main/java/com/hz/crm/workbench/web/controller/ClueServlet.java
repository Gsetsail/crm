package com.hz.crm.workbench.web.controller;

import com.hz.crm.settings.domain.User;
import com.hz.crm.settings.service.UserService;
import com.hz.crm.settings.service.impl.UserServiceImpl;
import com.hz.crm.utils.DateTimeUtil;
import com.hz.crm.utils.PrintJson;
import com.hz.crm.utils.ServiceFactory;
import com.hz.crm.utils.UUIDUtil;
import com.hz.crm.workbench.domain.Activity;
import com.hz.crm.workbench.domain.Clue;
import com.hz.crm.workbench.domain.Tran;
import com.hz.crm.workbench.service.ActivityService;
import com.hz.crm.workbench.service.ClueService;
import com.hz.crm.workbench.service.impl.ActivityServiceImpl;
import com.hz.crm.workbench.service.impl.ClueServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ClueServlet extends HttpServlet {
    @Override
    public void service(ServletRequest req, ServletResponse resp)
            throws ServletException, IOException {
        System.out.println("进入到线索模块");
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        String path = request.getServletPath();

        if("/workbench/clue/getUserList.do".equals(path)){
            getUserList(request,response);

        }else if("/workbench/clue/insertOneClue.do".equals(path)) {
            insertOneClue(request,response);
        }else if("/workbench/clue/detail.do".equals(path)){
            clueDetail(request,response);
        }else if ("/workbench/clue/getActivityListByClueId.do".equals(path)){
            getActivityListByClueId(request,response);
        } else if ("/workbench/clue/unbund.do".equals(path)) {
            unbund(request,response);
        }else if("/workbench/clue/getActivityList.do".equals(path)){
            getActivityListByLike(request,response);
        }else if("/workbench/clue/insertRelation.do".equals(path)){
            insertRelation(request,response);
        }else if("/workbench/clue/getActivityListByLike.do".equals(path)){
            getActivityListByNameLike(request,response);
        }else if("/workbench/clue/convert.do".equals(path)){
            convert(request,response);
        }

    }

    public void convert(HttpServletRequest request, HttpServletResponse response) {

            String clueId = request.getParameter("clueId");
            ClueService service =(ClueService)ServiceFactory.getService(new ClueServiceImpl());
            String flag = request.getParameter("flag");
            Tran tran = null;
            User user =(User)request.getSession(false).getAttribute("user");
        // 接收是否需要创建交易的标记
                if("a".equals(flag)){
                    tran = new Tran();
                    String activityId = request.getParameter("activityId");
                    String money = request.getParameter("money");
                    String name = request.getParameter("name");
                    String expectedDate = request.getParameter("expectedDate");
                    String stage = request.getParameter("stage");
                    String id = UUIDUtil.getUUID();
                    String createBy = user.getName();
                    String createTime = DateTimeUtil.getSysTime();
                    tran.setId(id);
                    tran.setActivityId(activityId);
                    tran.setMoney(money);
                    tran.setName(name);
                    tran.setExpectedDate(expectedDate);
                    tran.setStage(stage);
                    tran.setCreateBy(createBy);
                    tran.setCreateTime(createTime);
                }
                 boolean f = service.insertCustomerAndContactsByClueId(clueId,tran,user);
            if(f){
                try {
                    response.sendRedirect(request.getContextPath()+"/workbench/clue/index.jsp");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


    }

    public void getActivityListByNameLike(HttpServletRequest request, HttpServletResponse response) {
            String name = request.getParameter("name");
            ActivityService service = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
            List<Activity> aList = service.getActivityListByNameLike(name);
            PrintJson.printJsonObj(response,aList);
    }

    public void insertRelation(HttpServletRequest request, HttpServletResponse response) {
            String [] ActivityId = request.getParameterValues("ActivityId");
            String clueId = request.getParameter("clueId");
            ClueService service = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
            boolean flag = service.insertRelation(ActivityId,clueId);
            PrintJson.printJsonFlag(response,flag);
    }

    public void getActivityListByLike(HttpServletRequest request, HttpServletResponse response) {
             String name = request.getParameter("name");
             String clueId =request.getParameter("clueId");
             ActivityService service = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
             List<Activity> aList = service.likeSelectAllByName(name,clueId);
             PrintJson.printJsonObj(response,aList);
    }

    public void unbund(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        ClueService service = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        boolean flag = service.unbund(id);
        PrintJson.printJsonFlag(response,flag);
    }

    public void getActivityListByClueId(HttpServletRequest request, HttpServletResponse response) {
         String clueId = request.getParameter("clueId");
        ActivityService service = (ActivityService)ServiceFactory.getService(new ActivityServiceImpl());
        List<Activity> activityList = service.getActivityListByClueId(clueId);
        PrintJson.printJsonObj(response,activityList);

    }

    public void clueDetail(HttpServletRequest request, HttpServletResponse response) {
            String id = request.getParameter("id");
            ClueService service = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
            Clue clue = service.clueDetail(id);
            request.setAttribute("clue",clue);
        try {
            request.getRequestDispatcher("/workbench/clue/detail.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertOneClue(HttpServletRequest request, HttpServletResponse response) {
       String id = UUIDUtil.getUUID();
       String fullname = request.getParameter("fullname");
       String appellation = request.getParameter("appellation");
       String owner = request.getParameter("owner");
       String company = request.getParameter("company");
       String job = request.getParameter("job");
       String email = request.getParameter("email");
       String phone = request.getParameter("phone");
       String website = request.getParameter("website");
       String mphone = request.getParameter("mphone");
       String state = request.getParameter("state");
       String source = request.getParameter("source");
       User user  = (User) request.getSession(false).getAttribute("user");
       String createBy = user.getName();
       String createTime = DateTimeUtil.getSysTime();
       String description = request.getParameter("description");
       String contactSummary = request.getParameter("contactSummary");
       String nextContactTime = request.getParameter("nextContactTime");
       String address = request.getParameter("address");

        Clue clue = new Clue();
        clue.setId(id);
        clue.setFullname(fullname);
        clue.setAppellation(appellation);
        clue.setOwner(owner);
        clue.setCompany(company);
        clue.setJob(job);
        clue.setEmail(email);
        clue.setPhone(phone);
        clue.setWebsite(website);
        clue.setMphone(mphone);
        clue.setState(state);
        clue.setSource(source);
        clue.setCreateBy(createBy);
        clue.setCreateTime(createTime);
        clue.setDescription(description);
        clue.setContactSummary(contactSummary);
        clue.setNextContactTime(nextContactTime);
        clue.setAddress(address);

        ClueService service = (ClueService)ServiceFactory.getService(new ClueServiceImpl());
        boolean flag = service.insertOneClue(clue);
        PrintJson.printJsonFlag(response,flag);
    }

    public void getUserList(HttpServletRequest request, HttpServletResponse response) {
        UserService service = (UserService)ServiceFactory.getService(new UserServiceImpl());
       List<User> user = service.selectAllUser();
        PrintJson.printJsonObj(response,user);
    }
}
