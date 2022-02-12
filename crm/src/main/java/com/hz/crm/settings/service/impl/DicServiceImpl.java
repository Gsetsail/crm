package com.hz.crm.settings.service.impl;

import com.hz.crm.settings.dao.DicTypeDao;
import com.hz.crm.settings.dao.DicValueDao;
import com.hz.crm.settings.domain.DicType;
import com.hz.crm.settings.domain.DicValue;
import com.hz.crm.settings.service.DicService;
import com.hz.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DicServiceImpl implements DicService {
    DicTypeDao dicTypeDao = SqlSessionUtil.getSqlSession().getMapper(DicTypeDao.class);
    DicValueDao dicValueDao = SqlSessionUtil.getSqlSession().getMapper(DicValueDao.class);

    @Override
    public Map<String, List<DicValue>> selectDicValueByType() {

      List<DicType>  dicTypes =  dicTypeDao.getTypeList();
        Map<String,List<DicValue>> map = new HashMap<>();
        for(DicType dicType : dicTypes){
           List<DicValue> dicValues = dicValueDao.selectDivValueByType(dicType.getCode());
           map.put(dicType.getCode(),dicValues);
      }

        return map;
    }
}