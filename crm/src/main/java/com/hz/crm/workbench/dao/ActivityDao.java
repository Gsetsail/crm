package com.hz.crm.workbench.dao;

import com.hz.crm.workbench.domain.Activity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityDao {

  List<Activity> getActivityListByClueId(String clueId);

    List<Activity> selectAllActivity();

    Activity selectOneActivityById(String id);

    List<Activity> likeSelectAllByName(@Param("name")String name, @Param("clueId") String clueId);

    int updateActivityById(Activity activity);

    int deleteActivityById(String[] id);

    int insertOneActivity(Activity activity);

    List<Activity> selectPageList(Activity activity);

    int selectCount(Activity activity);

    Activity selectOneActivityByIds(String id);

    Activity detail(String id);

    List<Activity> getActivityListByNameLike(String name);
}
