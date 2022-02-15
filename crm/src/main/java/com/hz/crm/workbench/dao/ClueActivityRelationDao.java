package com.hz.crm.workbench.dao;


import com.hz.crm.workbench.domain.ClueActivityRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClueActivityRelationDao {


    int deleteOneById(String id);

    int insertRelation(@Param("activityId") String activityId, @Param("clueId") String clueId, @Param("id") String id);


    int deleteOneByActivityIdAndClueId(ClueActivityRelation clueActivityRelation);

    ClueActivityRelation selectOneActivityIdById();

    List<ClueActivityRelation> selectActivityId(String clueId);
}
