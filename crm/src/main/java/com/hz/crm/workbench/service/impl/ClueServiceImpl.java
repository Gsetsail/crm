package com.hz.crm.workbench.service.impl;

import com.hz.crm.settings.domain.User;
import com.hz.crm.utils.DateTimeUtil;
import com.hz.crm.utils.SqlSessionUtil;
import com.hz.crm.utils.UUIDUtil;
import com.hz.crm.workbench.dao.*;
import com.hz.crm.workbench.domain.*;
import com.hz.crm.workbench.service.ClueService;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ClueServiceImpl implements ClueService {
    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);

    private ClueDao clueDao = SqlSessionUtil.getSqlSession().getMapper(ClueDao.class);
    private ClueRemarkDao clueRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ClueRemarkDao.class);
    private ClueActivityRelationDao clueActivityRelationDao = SqlSessionUtil.getSqlSession().getMapper(ClueActivityRelationDao.class);

    private CustomerDao customerDao = SqlSessionUtil.getSqlSession().getMapper(CustomerDao.class);
    private CustomerRemarkDao customerRemarkDao = SqlSessionUtil.getSqlSession().getMapper(CustomerRemarkDao.class);

    private ContactsDao contactsDao = SqlSessionUtil.getSqlSession().getMapper(ContactsDao.class);
    private ContactsRemarkDao contactsRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ContactsRemarkDao.class);
    private ContactsActivityRelationDao contactsActivityRelationDao = SqlSessionUtil.getSqlSession().getMapper(ContactsActivityRelationDao.class);

    private TranDao tranDao = SqlSessionUtil.getSqlSession().getMapper(TranDao.class);
    private TranHistoryDao tranHistoryDao = SqlSessionUtil.getSqlSession().getMapper(TranHistoryDao.class);



    @Override
    public boolean insertOneClue(Clue clue) {
        boolean flag = true;
        int count = clueDao.insertClue(clue);
        if (count != 1 ) {
            flag = false;
        }
        return flag;
    }

    @Override
    public Clue clueDetail(String id) {

        Clue clue = clueDao.clueDetail(id);

        if(clue != null){

            return clue;
        }
         return null;
    }

    @Override
    public boolean unbund(String id) {
        boolean flag = true;

       int count = clueActivityRelationDao.deleteOneById(id);

       if(count != 1){
           flag = false;
       }

        return flag;
    }

    @Override
    public boolean insertRelation(String[] activityIds, String clueId) {
        boolean flag = true;
        int count ;
        for(int i = 0; i<activityIds.length;i++){
            String id = UUIDUtil.getUUID();
            String activityId = activityIds[i];
             count =  clueActivityRelationDao.insertRelation(activityId,clueId,id);

             if(count != 1){
                 flag = false;
             }
        }

        return flag;
    }



    @Override
    public boolean insertCustomerAndContactsByClueId(String clueId, Tran tran, User user) {
        boolean flag = true;
        Clue clue = clueDao.selectOneClueById(clueId);
        if(null != clue){
           String company = clue.getCompany();
           Customer customer = customerDao.selectOneByCompany(company);
           if(null == customer){
               String customerId  = UUIDUtil.getUUID();
               String owner = clue.getOwner();
               String name = clue.getCompany();
               String website = clue.getWebsite();
               String phone = clue.getPhone();
               String createBy = user.getName();
               String createTime = DateTimeUtil.getSysTime();
               String contactSummary = clue.getContactSummary();
               String nextContactTime = clue.getNextContactTime();
               String description = clue.getDescription();
               String address = clue.getAddress();
               customer = new Customer();
               customer.setId(customerId);
               customer.setOwner(owner);
               customer.setName(name);
               customer.setWebsite(website);
               customer.setPhone(phone);
               customer.setCreateBy(createBy);
               customer.setCreateTime(createTime);
               customer.setContactSummary(contactSummary);
               customer.setNextContactTime(nextContactTime);
               customer.setDescription(description);
               customer.setAddress(address);
               int count = customerDao.insertCustomer(customer);
               if(count != 1){
                    flag = false;
               }
           }


                    String id = UUIDUtil.getUUID();
                    String source = clue.getSource();
                    String fullname = clue.getFullname();
                    String appellation = clue.getAppellation();
                    String email = clue.getEmail();
                    String mphone = clue.getMphone();
                    String job = clue.getJob();
                    String createTime = DateTimeUtil.getSysTime();
                    Contacts contacts = new Contacts();
                    contacts.setId(id);
                    contacts.setOwner(clue.getOwner());
                    contacts.setSource(source);
                    contacts.setCustomerId(customer.getId());
                    contacts.setFullname(fullname);
                    contacts.setAppellation(appellation);
                    contacts.setEmail(email);
                    contacts.setMphone(mphone);
                    contacts.setJob(job);
                    contacts.setCreateBy(user.getName());
                    contacts.setCreateTime(createTime);
                    contacts.setDescription(clue.getDescription());
                    contacts.setContactSummary(clue.getContactSummary());
                    contacts.setNextContactTime(clue.getNextContactTime());
                    contacts.setAddress(clue.getAddress());
                    int count = contactsDao.insertCustomer(contacts);
                    if(count != 1){
                        flag = false;
                    }

                    List<ClueRemark> clueRemarkList = clueRemarkDao.selectClueRemarkByClueId(clueId);
                    for(ClueRemark clueRemark : clueRemarkList){

                        String customerRemarkId = UUIDUtil.getUUID();
                        String customerRemarkCreateBy = user.getName();
                        String customerRemarkCreateTime = DateTimeUtil.getSysTime();
                        String customerRemarkEditFlag = "0";
                        String customerRemarkCustomerId = customer.getId();
                        String customerRemarkNoteContent = clueRemark.getNoteContent();

                        CustomerRemark customerRemark = new CustomerRemark();
                        customerRemark.setId(customerRemarkId);
                        customerRemark.setCreateBy(customerRemarkCreateBy);
                        customerRemark.setCreateTime(customerRemarkCreateTime);
                        customerRemark.setEditFlag(customerRemarkEditFlag);
                        customerRemark.setCustomerId(customerRemarkCustomerId);
                        customerRemark.setNoteContent(customerRemarkNoteContent);

                        int count1 =  customerRemarkDao.insertCustomerRemark(customerRemark);

                        if(count1 != 1){
                            flag = false;
                        }


                        ContactsRemark contactsRemark = new ContactsRemark();
                        String contactsRemarkId = UUIDUtil.getUUID();
                        String contactsRemarkCreateTime = DateTimeUtil.getSysTime();
                        String contactsRemarkCreateBy = user.getName();
                        String contactsRemarkEditFlag = "0";
                        String contactsRemarkContactsId =  id;
                        String contactsRemarkNoteContent = clueRemark.getNoteContent();
                        contactsRemark.setId(contactsRemarkId);
                        contactsRemark.setCreateTime(contactsRemarkCreateTime);
                        contactsRemark.setCreateBy(contactsRemarkCreateBy);
                        contactsRemark.setEditFlag(contactsRemarkEditFlag);
                        contactsRemark.setContactsId(contactsRemarkContactsId);
                        contactsRemark.setNoteContent(contactsRemarkNoteContent);
                        int count2 = contactsRemarkDao.insertOneContactsRemark(contactsRemark);
                        if(count2 != 1){
                            flag = false;
                        }
                        int count3 = clueRemarkDao.deleteRemarkById(clueRemark.getId());
                        if(count3 != 1){
                            flag = false;
                        }
                    }

                 List<ClueActivityRelation> clueActivityRelationList =  clueActivityRelationDao.selectActivityId(clueId);
                    for(ClueActivityRelation ClueActivityRelation : clueActivityRelationList){

                        ContactsActivityRelation contactsActivityRelation = new ContactsActivityRelation();
                        String contactsActivityRelationId = UUIDUtil.getUUID();
                        String contactsActivityRelationContactsId = id;
                        String contactsActivityRelationActivity = ClueActivityRelation.getActivityId();
                        contactsActivityRelation.setId(contactsActivityRelationId);
                        contactsActivityRelation.setContactsId(contactsActivityRelationContactsId);
                        contactsActivityRelation.setActivityId(contactsActivityRelationActivity);
                        int count4 = contactsActivityRelationDao.insertOneContactsActivityRelation(contactsActivityRelation);
                        if(count4 != 1){
                            flag = false;
                        }
                        ClueActivityRelation clueActivityRelation = new ClueActivityRelation();
                        clueActivityRelation.setActivityId(ClueActivityRelation.getActivityId());
                        clueActivityRelation.setClueId(clueId);
                        int count5 = clueActivityRelationDao.deleteOneByActivityIdAndClueId(clueActivityRelation);
                        if(count5 != 1){
                            flag = false;
                        }
                    }

                       int count6 =  clueDao.deleteClueById(clueId);
                        if(count6 != 1){
                            flag = false;
                        }


                    if(tran != null){

                     /*   tran.setId(id);
                        tran.setActivityId(activityId);
                        tran.setMoney(money);
                        tran.setName(name);
                        tran.setExpectedDate(expectedDate);
                        tran.setStage(stage);
                        tran.setCreateBy(createBy);
                        tran.setCreateTime(createTime); */
                        tran.setOwner(clue.getOwner());
                        tran.setCustomerId(customer.getId());
                        tran.setSource(clue.getSource());
                        tran.setContactsId(contacts.getId());
                        tran.setContactSummary(clue.getContactSummary());
                        tran.setNextContactTime(clue.getNextContactTime());
                       int count7 =  tranDao.insertTran(tran);
                       if(count7 != 1){
                           flag = false;
                       }
                       TranHistory tranHistory = new TranHistory();
                        tranHistory.setTranId(tran.getId());
                        tranHistory.setId(UUIDUtil.getUUID());
                        tranHistory.setStage(tran.getStage());
                        tranHistory.setMoney(tran.getMoney());
                        tranHistory.setExpectedDate(tran.getExpectedDate());
                        tranHistory.setCreateTime(DateTimeUtil.getSysTime());
                        tranHistory.setCreateBy(user.getName());
                        int count8 = tranHistoryDao.insertTranHistory(tranHistory);
                        if(count8 != 1){
                            flag = false;
                        }
                    }
        }

                return flag;
    }



}
