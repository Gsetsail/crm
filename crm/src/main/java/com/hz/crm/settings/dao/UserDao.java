package com.hz.crm.settings.dao;

import com.hz.crm.settings.domain.User;

import java.util.List;

public interface UserDao {

        User selectOneByActAndPwd(User user);

         List<User> selectAllUser();

    User selectOneById(String owner);
}
