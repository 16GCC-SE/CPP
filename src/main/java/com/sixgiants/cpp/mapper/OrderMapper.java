package com.sixgiants.cpp.mapper;

import com.sixgiants.cpp.entity.Order;
import org.apache.ibatis.annotations.Select;

public interface OrderMapper {

    @Select("insert into orders(id,employer_id,title,content,deadline,classification,status,need_number,salary,place,sex,create_time,settlement_method) values(#{id},#{employerId},#{title},#{content},#{deadline},#{classification},#{status},#{needNumber},#{salary},#{place},#{sex},#{createTime},#{settlementMethod})")
    void saveOrder(Order order);
}
