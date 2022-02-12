package com.hz.crm.workbench.service.impl;

import com.hz.crm.settings.dao.UserDao;
import com.hz.crm.settings.domain.User;
import com.hz.crm.utils.SqlSessionUtil;
import com.hz.crm.workbench.dao.ClueDao;
import com.hz.crm.workbench.dao.ClueRemarkDao;
import com.hz.crm.workbench.domain.Clue;
import com.hz.crm.workbench.service.ClueService;

import java.util.List;

public class ClueServiceImpl implements ClueService {
    private ClueDao clueDao = SqlSessionUtil.getSqlSession().getMapper(ClueDao.class);
    private ClueRemarkDao clueRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ClueRemarkDao.class);


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
}
