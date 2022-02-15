package com.hz.crm.workbench.service.impl;

import com.hz.crm.settings.domain.User;
import com.hz.crm.utils.DateTimeUtil;
import com.hz.crm.utils.SqlSessionUtil;
import com.hz.crm.utils.UUIDUtil;
import com.hz.crm.workbench.dao.CustomerDao;
import com.hz.crm.workbench.dao.TranDao;
import com.hz.crm.workbench.dao.TranHistoryDao;
import com.hz.crm.workbench.domain.Customer;
import com.hz.crm.workbench.domain.Tran;
import com.hz.crm.workbench.domain.TranHistory;
import com.hz.crm.workbench.service.TranService;

public class TranServiceImpl implements TranService {
    TranDao tranDao = SqlSessionUtil.getSqlSession().getMapper(TranDao.class);
    TranHistoryDao tranHistoryDao = SqlSessionUtil.getSqlSession().getMapper(TranHistoryDao.class);
    CustomerDao customerDao = SqlSessionUtil.getSqlSession().getMapper(CustomerDao.class);
    @Override
    public boolean insertTran(Tran tran, User user, String customerName) {
        boolean flag = true;
        Customer customer = customerDao.selectOneByCompany(customerName);
        tran.setCustomerId(customer.getId());
        int count =  tranDao.insertTran(tran);
       if(count != 1){
           flag = false;
       }
        TranHistory tranHistory = new TranHistory();
        tranHistory.setTranId(tran.getId());
        tranHistory.setCreateBy(user.getName());
        tranHistory.setCreateTime(DateTimeUtil.getSysTime());
        tranHistory.setId(UUIDUtil.getUUID());
        tranHistory.setMoney(tran.getMoney());
        tranHistory.setStage(tran.getStage());
        tranHistory.setExpectedDate(tran.getExpectedDate());
        int count1 = tranHistoryDao.insertTranHistory(tranHistory);
           if(count1 != 1){
               flag = false;
           }
                 return flag;
    }
}
