package com.hz.crm.workbench.service.impl;

import com.hz.crm.utils.SqlSessionUtil;
import com.hz.crm.utils.UUIDUtil;
import com.hz.crm.workbench.dao.ActivityDao;
import com.hz.crm.workbench.dao.ClueActivityRelationDao;
import com.hz.crm.workbench.dao.ClueDao;
import com.hz.crm.workbench.dao.ClueRemarkDao;
import com.hz.crm.workbench.domain.Clue;
import com.hz.crm.workbench.service.ClueService;

public class ClueServiceImpl implements ClueService {
    private ClueDao clueDao = SqlSessionUtil.getSqlSession().getMapper(ClueDao.class);
    private ClueRemarkDao clueRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ClueRemarkDao.class);
    private ClueActivityRelationDao clueActivityRelationDao = SqlSessionUtil.getSqlSession().getMapper(ClueActivityRelationDao.class);
    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);


    @Override
    public boolean insertOneClue(Clue clue) {
        boolean flag = true;
        int count = clueDao.insertClue(clue);
        if (count != 1 ) {
            flag = false;
        }
        return flag;
    }

    @Override
    public Clue clueDetail(String id) {

        Clue clue = clueDao.clueDetail(id);

        if(clue != null){

            return clue;
        }
         return null;
    }

    @Override
    public boolean unbund(String id) {
        boolean flag = true;

       int count = clueActivityRelationDao.deleteOneById(id);

       if(count != 1){
           flag = false;
       }

        return flag;
    }

    @Override
    public boolean insertRelation(String[] activityIds, String clueId) {
        boolean flag = true;
        int count ;
        for(int i = 0; i<activityIds.length;i++){
            String id = UUIDUtil.getUUID();
            String activityId = activityIds[i];
             count =  clueActivityRelationDao.insertRelation(activityId,clueId,id);

             if(count != 1){
                 flag = false;
             }
        }

        return flag;
    }

}
