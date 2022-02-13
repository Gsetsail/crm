package com.hz.crm.workbench.dao;


import org.apache.ibatis.annotations.Param;

public interface ClueActivityRelationDao {


    int deleteOneById(String id);

    int insertRelation(@Param("activityId") String activityId, @Param("clueId") String clueId, @Param("id") String id);
}
