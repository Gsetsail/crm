package com.hz.crm.workbench.service;

import com.hz.crm.settings.domain.User;
import com.hz.crm.workbench.domain.Clue;

import java.util.List;

public interface ClueService {

    boolean insertOneClue(Clue clue);

    Clue clueDetail(String id);
}
