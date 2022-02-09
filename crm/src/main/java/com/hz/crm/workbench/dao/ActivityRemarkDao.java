package com.hz.crm.workbench.dao;

import com.hz.crm.workbench.domain.ActivityRemark;

import java.util.List;

public interface ActivityRemarkDao {

    List<ActivityRemark> selectAllActivityRemark();

    ActivityRemark selectOneActivityRemarkById(String id);

    int updateActivityRemarkById(ActivityRemark activityRemark);

    int deleteActivityRemarkById(String [] id);

    int insertOneActivityRemark(ActivityRemark activityRemark);

    int selectCountById(String [] id);

    List<ActivityRemark> selectActivityRemarks(String id);


}
