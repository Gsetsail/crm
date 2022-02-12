package com.hz.crm.settings.service;

import com.hz.crm.settings.domain.DicValue;

import java.util.List;
import java.util.Map;

public interface DicService {

    Map<String, List<DicValue>> selectDicValueByType();
}
