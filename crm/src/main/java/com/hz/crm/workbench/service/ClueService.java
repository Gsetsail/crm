package com.hz.crm.workbench.service;

import com.hz.crm.workbench.domain.Clue;

public interface ClueService {

    boolean insertOneClue(Clue clue);

    Clue clueDetail(String id);

    boolean unbund(String id);

    boolean insertRelation(String[] activityId,String clueId);
}
