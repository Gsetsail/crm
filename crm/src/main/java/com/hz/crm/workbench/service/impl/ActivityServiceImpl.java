package com.hz.crm.workbench.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.hz.crm.settings.dao.UserDao;
import com.hz.crm.settings.domain.User;
import com.hz.crm.utils.SqlSessionUtil;
import com.hz.crm.utils.UUIDUtil;
import com.hz.crm.vo.PaginationVo;
import com.hz.crm.workbench.dao.ActivityDao;
import com.hz.crm.workbench.dao.ActivityRemarkDao;
import com.hz.crm.workbench.domain.Activity;
import com.hz.crm.workbench.domain.ActivityRemark;
import com.hz.crm.workbench.service.ActivityService;

import java.text.SimpleDateFormat;
import java.util.*;

public class ActivityServiceImpl implements ActivityService {
    ActivityDao dao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);
    ActivityRemarkDao remarkDaoDao = SqlSessionUtil.getSqlSession().getMapper(ActivityRemarkDao.class);
    UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);
    @Override
    public List<Activity> selectAllActivity() {


        List<Activity>  activityList  = dao.selectAllActivity();

            return activityList;
    }

    @Override
    public Activity selectOneActivityById(String id) {

        Activity activity = dao.selectOneActivityById(id);

        if (activity != null) {

            return activity;
        }

          return activity;
    }

    @Override
    public List<Activity> likeSelectAllByName(String name) {

        return null;
    }

    @Override
    public boolean updateActivityById(Activity activity) {
        boolean flag = true;

        int count = dao.updateActivityById(activity);

        if(count != 1){

            flag = false;
        }

          return flag;
    }

    @Override
    public boolean deleteActivityById(String[] id) {
        boolean flag = true;
        // 查询出需要删除的备注的数量

        int count1 = remarkDaoDao.selectCountById(id);
        // 删除备注,返回删除的数量(实际删除的数量)
        int count2 =  remarkDaoDao.deleteActivityRemarkById(id);

        if(count1 != count2){
            flag = false;
            return flag;
        }


        int count = dao.deleteActivityById(id);
        if(count != id.length){

            flag = false;
            return flag;
        }

            return flag;
    }

    @Override
    public boolean insertOneActivity(Activity activity) {
        boolean flag = false;
        int count = 0;

        count = dao.insertOneActivity(activity);

            if (count != 0) {

                flag = true;

            }

             return  flag;

    }

    @Override
    public PaginationVo<Activity> selectPageList(Activity activity, String pageNo, String pageSize) {

            int total =  dao.selectCount(activity);
            PageHelper.startPage(Integer.valueOf(pageNo),Integer.valueOf(pageSize));
            List<Activity> activityList = dao.selectPageList(activity);

            PaginationVo paginationVo = new PaginationVo();
            paginationVo.setDataList(activityList);
            paginationVo.setTotal(total);

            return paginationVo;
    }

    @Override
    public Activity selectOneActivityByIds(String id) {


      Activity activity = dao.selectOneActivityByIds(id);

         return activity;
    }

    @Override
    public Map<String, Object> getUserListAndActivity(String id) {

        List<User> userList = userDao.selectAllUser();

        Activity activity =  dao.selectOneActivityById(id);

        Map<String,Object> map = new HashMap<>();

        map.put("uList",userList);
        map.put("activity",activity);

        return map;
    }

    @Override
    public Activity detail(String id) {

        Activity activity = dao.detail(id);

        return activity;



    }

    @Override
    public List<ActivityRemark> getRemarkListById(String id) {

      List<ActivityRemark> list =  remarkDaoDao.selectActivityRemarks(id);
        return list;
    }

    @Override
    public boolean deleteRemarkById(String activityRemarkId) {
        boolean flag = true;

       int count =  remarkDaoDao.deleteRemarkById(activityRemarkId);

       if(count != 1){
           flag = false;
       }

        return flag;
    }

    @Override
    public boolean insertActivityRemark(ActivityRemark activityRemark) {
            boolean flag = true;
       int count =  remarkDaoDao.insertOneActivityRemark(activityRemark);
        if(count != 1){

            flag = false;
       }

        return flag;
    }
}
