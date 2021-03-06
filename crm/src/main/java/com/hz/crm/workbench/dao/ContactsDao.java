package com.hz.crm.workbench.dao;

import com.hz.crm.workbench.domain.Contacts;

import java.util.List;

public interface ContactsDao {

    int insertCustomer(Contacts contacts);

    List<Contacts> selectLikeContactsByName(String fullname);
}
