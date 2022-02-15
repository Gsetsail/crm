package com.hz.crm.workbench.dao;

import com.hz.crm.workbench.domain.ClueRemark;

import java.util.List;

public interface ClueRemarkDao {

    List<ClueRemark> selectClueRemarkByClueId(String clueId);

    int deleteRemarkById(String id);
}
