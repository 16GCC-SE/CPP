package com.sixgiants.cpp.dao;

import com.sixgiants.cpp.entity.User;
import com.sixgiants.cpp.mapper.UserMapper;
import com.sixgiants.cpp.util.UUIDutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    @Autowired
    private UserMapper userMapper;

    public User findByNameAndPsw(String name, String password){
        return userMapper.findByNameAndPsw(name,password);
    }

    public User findByName(String name){
        return userMapper.findByName(name);
    }

    public void saveUser(User user){
        user.setId(UUIDutil.getUUID());
        userMapper.saveUser(user);
    }

    public void updateUser(User user){
        userMapper.updateUser(user);
    }

    public User findById(String id){
        return userMapper.findById(id);
    }
}
