package com.sixgiants.cpp.mapper;

import com.sixgiants.cpp.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from users where name=#{name} and password=#{password}")
    User findByNameAndPsw(@Param("name")String name, @Param("password") String password);

    @Select("select * from users where name=#{name}")
    User findByName(String name);

    @Select("insert into users(id,name,password,email,birthday) values(#{id},#{name},#{password},#{email},#{birthday})")
    void saveUser(User user);

    @Select("select * from users where id=#{id}")
    User findById(String id);

    @Select("update users set name=#{name},password=#{password},email=#{email},birthday=#{birthday} where id=#{id}")
    void updateUser(User user);
}
