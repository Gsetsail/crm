package com.hz.crm.workbench.web.controller;

import com.hz.crm.settings.domain.User;
import com.hz.crm.settings.service.UserService;
import com.hz.crm.settings.service.impl.UserServiceImpl;
import com.hz.crm.utils.DateTimeUtil;
import com.hz.crm.utils.PrintJson;
import com.hz.crm.utils.ServiceFactory;
import com.hz.crm.utils.UUIDUtil;
import com.hz.crm.workbench.domain.Activity;
import com.hz.crm.workbench.domain.Contacts;
import com.hz.crm.workbench.domain.Tran;
import com.hz.crm.workbench.service.ActivityService;
import com.hz.crm.workbench.service.ContactsService;
import com.hz.crm.workbench.service.CustomerService;
import com.hz.crm.workbench.service.TranService;
import com.hz.crm.workbench.service.impl.ActivityServiceImpl;
import com.hz.crm.workbench.service.impl.ContactsServiceImpl;
import com.hz.crm.workbench.service.impl.CustomerServiceImpl;
import com.hz.crm.workbench.service.impl.TranServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TranServlet extends HttpServlet {
    @Override
    public void service(ServletRequest req, ServletResponse resp)
            throws ServletException, IOException {
        System.out.println("进入到交易模块");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String path = request.getServletPath();

        if ("/workbench/tran/getUserList.do".equals(path)) {
            getUserList(request,response);
        } else if ("/workbench/tran/getActivityList.do".equals(path)) {
            getActivityListByName(request,response);
        }else if("/workbench/tran/getContactsList.do".equals(path)){
            getContactsListByFullName(request,response);
        }else if("/workbench/tran/insertTran.do".equals(path)){
            insertTran(request,response);
        }else if("/workbench/tran/getCustomerName.do".equals(path)){
            getCustomerName(request,response);
        }
    }

    public void getCustomerName(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        CustomerService service = (CustomerService) ServiceFactory.getService(new CustomerServiceImpl());
        List<String> cList = service.getCustomerName(name);
        PrintJson.printJsonObj(response,cList);
    }

    public void insertTran(HttpServletRequest request, HttpServletResponse response) {
        String id = UUIDUtil.getUUID();
        String activityId = request.getParameter("activityId");
        String contactsId = request.getParameter("contactsId");
        String owner = request.getParameter("owner");
        String money = request.getParameter("money");
        String name = request.getParameter("name");
        String expectedDate = request.getParameter("expectedDate");
        String stage = request.getParameter("stage");
        String type = request.getParameter("transactionType");
        String source = request.getParameter("source");
        String description = request.getParameter("description");
        String contactSummary = request.getParameter("contactSummary");
        String nextContactTime = request.getParameter("nextContactTime");
        User user = (User)request.getSession(false).getAttribute("user");
        String createBy =user.getName();
        String createTime = DateTimeUtil.getSysTime();
        Tran tran = new Tran();
        tran.setId(id);
        tran.setActivityId(activityId);
        tran.setContactsId(contactsId);
        tran.setOwner(owner);
        tran.setMoney(money);
        tran.setName(name);
        tran.setExpectedDate(expectedDate);
        tran.setStage(stage);
        tran.setType(type);
        tran.setSource(source);
        tran.setDescription(description);
        tran.setContactSummary(contactSummary);
        tran.setNextContactTime(nextContactTime);
        tran.setCreateBy(createBy);
        tran.setCreateTime(createTime);
        TranService service =(TranService) ServiceFactory.getService(new TranServiceImpl());
        String customerName = request.getParameter("customerName");
        boolean flag = service.insertTran(tran,user,customerName);


        if(flag){
            try {
                response.sendRedirect(request.getContextPath()+"/workbench/transaction/index.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void getContactsListByFullName(HttpServletRequest request, HttpServletResponse response) {
        String fullname = request.getParameter("fullname");
        ContactsService service = (ContactsService) ServiceFactory.getService(new ContactsServiceImpl());
        List<Contacts> cList = service.selectLikeContactsByName(fullname);
        PrintJson.printJsonObj(response,cList);
    }

    public void getActivityListByName(HttpServletRequest request, HttpServletResponse response) {
        String name  = request.getParameter("name");
        ActivityService service = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        List<Activity> aList = service.getActivityListByNameLike(name);
        PrintJson.printJsonObj(response,aList);
    }

    public void getUserList(HttpServletRequest request, HttpServletResponse response) {
        UserService service = (UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> uList = service.selectAllUser();
        PrintJson.printJsonObj(response,uList);
    }


}
