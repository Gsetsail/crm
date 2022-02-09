package com.hz.crm.workbench.service.impl;

import com.hz.crm.utils.SqlSessionUtil;
import com.hz.crm.workbench.dao.ActivityDao;
import com.hz.crm.workbench.dao.ActivityRemarkDao;
import com.hz.crm.workbench.domain.ActivityRemark;
import com.hz.crm.workbench.service.ActivityRemarkService;

import java.util.List;

public class ActivityRemarkServiceImpl implements ActivityRemarkService {

    @Override
    public List<ActivityRemark> selectAllActivityRemark() {
        ActivityRemarkDao dao = SqlSessionUtil.getSqlSession().getMapper(ActivityRemarkDao.class);
        return null;
    }

    @Override
    public ActivityRemark selectOneActivityRemarkById(String id) {
        ActivityRemarkDao dao = SqlSessionUtil.getSqlSession().getMapper(ActivityRemarkDao.class);
        return null;
    }

    @Override
    public int updateActivityRemarkById(ActivityRemark activityRemark) {
        ActivityRemarkDao dao = SqlSessionUtil.getSqlSession().getMapper(ActivityRemarkDao.class);
        return 0;
    }

    @Override
    public int deleteActivityRemarkById(String id) {
        ActivityRemarkDao dao = SqlSessionUtil.getSqlSession().getMapper(ActivityRemarkDao.class);
        return 0;
    }

    @Override
    public int insertOneActivityRemark(ActivityRemark activityRemark) {
        ActivityRemarkDao dao = SqlSessionUtil.getSqlSession().getMapper(ActivityRemarkDao.class);
        return 0;
    }
}
