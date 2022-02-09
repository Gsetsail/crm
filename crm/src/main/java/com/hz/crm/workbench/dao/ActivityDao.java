package com.hz.crm.workbench.dao;

import com.hz.crm.settings.domain.User;
import com.hz.crm.vo.PaginationVo;
import com.hz.crm.workbench.domain.Activity;

import java.util.List;

public interface ActivityDao {

    List<Activity> selectAllActivity();

    Activity selectOneActivityById(String id);

    List<Activity> likeSelectAllByName(String name);

    int updateActivityById(Activity activity);

    int deleteActivityById(String[] id);

    int insertOneActivity(Activity activity);

    List<Activity> selectPageList(Activity activity);

    int selectCount(Activity activity);

    Activity selectOneActivityByIds(String id);

    Activity detail(String id);
}
