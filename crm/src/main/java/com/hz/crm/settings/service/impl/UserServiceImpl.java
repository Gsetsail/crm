package com.hz.crm.settings.service.impl;

import com.hz.crm.exception.LoginException;
import com.hz.crm.settings.dao.UserDao;
import com.hz.crm.settings.domain.User;
import com.hz.crm.settings.service.UserService;
import com.hz.crm.utils.DateTimeUtil;
import com.hz.crm.utils.SqlSessionUtil;

import java.sql.ResultSet;
import java.util.List;

public class UserServiceImpl implements UserService {




    @Override
    public User selectOneByActAndPwd(User user) throws LoginException{
           UserDao dao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);
           User userN =  dao.selectOneByActAndPwd(user);
           String currentTime = DateTimeUtil.getSysTime();
                if(null == userN){
                     throw new LoginException("用户名密码错误!");
               }else if("0".equals(userN.getLockState())){
                    throw new LoginException("登录状态已被锁定");
               }else if(currentTime.compareTo(userN.getExpireTime()) >0){
                    throw new LoginException("登录已失效");
               }else if(userN.getAllowIps().contains(user.getAllowIps())){
                   throw new LoginException("ip地址不符合");
               }

                     return userN;

    }

    @Override
    public List<User> selectAllUser() {
        UserDao dao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

       List<User> userList =  dao.selectAllUser();

        return userList;
    }
}
