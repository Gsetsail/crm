package com.hz.crm.workbench.service.impl;

import com.hz.crm.utils.SqlSessionUtil;
import com.hz.crm.workbench.dao.ContactsDao;
import com.hz.crm.workbench.domain.Contacts;
import com.hz.crm.workbench.service.ContactsService;

import java.util.List;

public class ContactsServiceImpl implements ContactsService {
    private ContactsDao contactsDao = SqlSessionUtil.getSqlSession().getMapper(ContactsDao.class);
    @Override
    public List<Contacts> selectLikeContactsByName(String fullname) {

        List<Contacts> cList = contactsDao.selectLikeContactsByName(fullname);

        return cList;
    }
}
