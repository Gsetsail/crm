package com.hz.crm.workbench.service;

import com.hz.crm.settings.domain.User;
import com.hz.crm.workbench.domain.Clue;
import com.hz.crm.workbench.domain.Tran;

public interface ClueService {

    boolean insertOneClue(Clue clue);

    Clue clueDetail(String id);

    boolean unbund(String id);

    boolean insertRelation(String[] activityId,String clueId);

    boolean insertCustomerAndContactsByClueId(String clueId, Tran tran, User user);


}
