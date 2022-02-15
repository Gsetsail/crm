package com.hz.crm.workbench.dao;

import com.hz.crm.workbench.domain.Customer;

import java.util.List;

public interface CustomerDao {

    int insertCustomer(Customer customer);

    Customer selectOneByCompany(String company);

    List<Customer> likeQueryCustomerByName(String name);
}
