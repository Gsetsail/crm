package com.hz.crm.workbench.service;

import com.hz.crm.settings.domain.User;
import com.hz.crm.vo.PaginationVo;
import com.hz.crm.workbench.domain.Activity;
import com.hz.crm.workbench.domain.ActivityRemark;

import java.util.List;
import java.util.Map;

public interface ActivityService {

    List<Activity> selectAllActivity();

    Activity selectOneActivityById(String id);

    List<Activity> likeSelectAllByName(String name);

    boolean updateActivityById(Activity activity);

    boolean deleteActivityById(String[] id);

    boolean insertOneActivity(Activity activity);

    PaginationVo<Activity> selectPageList(Activity activity, String pageNo, String pageSize);

    Activity selectOneActivityByIds(String id);

    Map<String, Object> getUserListAndActivity(String id);

    Activity detail(String id);

    List<ActivityRemark> getRemarkListById(String id);

    boolean deleteRemarkById(String activityRemarkId);

    boolean insertActivityRemark(ActivityRemark activityRemark);
}
