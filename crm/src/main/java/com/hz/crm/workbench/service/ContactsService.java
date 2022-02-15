package com.hz.crm.workbench.service;

import com.hz.crm.workbench.domain.Contacts;

import java.util.List;

public interface ContactsService {
    List<Contacts> selectLikeContactsByName(String fullname);
}
