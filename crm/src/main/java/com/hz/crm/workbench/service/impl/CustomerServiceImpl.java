package com.hz.crm.workbench.service.impl;

import com.hz.crm.utils.SqlSessionUtil;
import com.hz.crm.workbench.dao.CustomerDao;
import com.hz.crm.workbench.domain.Customer;
import com.hz.crm.workbench.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
 private CustomerDao customerDao = SqlSessionUtil.getSqlSession().getMapper(CustomerDao.class);
    @Override
    public List<String> getCustomerName(String name) {

        List<Customer> cList = customerDao.likeQueryCustomerByName(name);
        List<String> companyList = new ArrayList<>();
        for(Customer customer : cList){
            companyList.add(customer.getName());
        }
        return companyList;
    }
}
