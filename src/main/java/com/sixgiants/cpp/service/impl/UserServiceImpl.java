package com.sixgiants.cpp.service.impl;

import com.sixgiants.cpp.dao.UserDao;
import com.sixgiants.cpp.entity.User;
import com.sixgiants.cpp.util.DateFormatUtil;
import com.sixgiants.cpp.util.UUIDutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl {
    @Autowired
    private UserDao userDao;

    public User findByNameAndPsw(String name, String password){
        return userDao.findByNameAndPsw(name,password);
    }

    public User findByName(String name){
        return userDao.findByName(name);
    }

    public void saveUser(User user){
        user.setId(UUIDutil.getUUID());
        //user.setBirthday(DateFormatUtil.StringToDate());
        userDao.saveUser(user);
    }

    public void updateUser(User user){
        userDao.updateUser(user);
    }

    public User findById(String id){
        return userDao.findById(id);
    }
}
