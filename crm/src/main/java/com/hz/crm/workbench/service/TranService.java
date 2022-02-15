package com.hz.crm.workbench.service;

import com.hz.crm.settings.domain.User;
import com.hz.crm.workbench.domain.Tran;

public interface TranService {


    boolean insertTran(Tran tran, User user, String customerName);
}
