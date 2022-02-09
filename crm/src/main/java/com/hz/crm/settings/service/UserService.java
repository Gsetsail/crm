package com.hz.crm.settings.service;

import com.hz.crm.exception.LoginException;
import com.hz.crm.settings.domain.User;

import java.util.List;

public interface UserService {

    User selectOneByActAndPwd(User user) throws LoginException;

    List<User> selectAllUser();
}
