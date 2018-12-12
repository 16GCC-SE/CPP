package com.sixgiants.cpp.mapper;

import org.apache.ibatis.annotations.Select;

public interface OrderMapper {

    @Select("insert into orders(id,employer_id,title,content,deadline,classification,status,need_number,salary,place,sex,created_time,settlement_method) values(#{id},#{employer_id},#{title},#{content},#{deadline},#{classification},#{status},#{need_number},#{salary},#{place},#{sex},#{created_time},#{settlement_method})")
    void saveOrder(Order order);
}
