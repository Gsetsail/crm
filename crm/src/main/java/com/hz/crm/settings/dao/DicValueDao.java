package com.hz.crm.settings.dao;

import com.hz.crm.settings.domain.DicValue;

import java.util.List;

public interface DicValueDao {
    List<DicValue> selectDivValueByType(String typeCode);
}
