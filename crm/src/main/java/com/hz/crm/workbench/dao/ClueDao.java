package com.hz.crm.workbench.dao;


import com.hz.crm.workbench.domain.Clue;

public interface ClueDao {


    int insertClue(Clue clue);

    Clue clueDetail(String id);

    Clue selectOneClueById(String clueId);

    int deleteClueById(String clueId);
}
