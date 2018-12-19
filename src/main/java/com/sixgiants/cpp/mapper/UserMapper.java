package com.sixgiants.cpp.mapper;

import com.sixgiants.cpp.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from users where name=#{name} and password=#{password}")
    User findByNameAndPsw(@Param("name")String name, @Param("password") String password);

    @Select("select * from users where name=#{name}")
    User findByName(String name);

    @Select("insert into users(id,name,password,email,phone,sex,createTime,role,head_icon) values(#{id},#{name},#{password},#{email},#{phone},#{sex},#{createTime},#{role},#{headIcon})")
    void saveUser(User user);

    @Select("select * from users where id=#{id}")
    User findById(String id);

    @Select("update users set name=#{name},email=#{email},phone=#{phone},sex=#{sex} where id=#{id}")
    void updateUser(User user);

    @Select("update users set password=#{password} where id=#{id}")
    void updatePassword(User user);
}
