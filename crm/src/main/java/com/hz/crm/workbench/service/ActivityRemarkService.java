package com.hz.crm.workbench.service;

import com.hz.crm.workbench.domain.ActivityRemark;

import java.util.List;

public interface ActivityRemarkService {

    List<ActivityRemark> selectAllActivityRemark();

    ActivityRemark selectOneActivityRemarkById(String id);

    int updateActivityRemarkById(ActivityRemark activityRemark);

    int deleteActivityRemarkById(String id);

    int insertOneActivityRemark(ActivityRemark activityRemark);
}
