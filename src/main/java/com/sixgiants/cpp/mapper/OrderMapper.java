package com.sixgiants.cpp.mapper;

import com.sixgiants.cpp.entity.Order;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper {

    @Select("insert into orders(id,employer_id,title,content,deadline,classification,status,need_number,salary,place,sex,create_time,settlement_method) values(#{id},#{employerId},#{title},#{content},#{deadline},#{classification},#{status},#{needNumber},#{salary},#{place},#{sex},#{createTime},#{settlementMethod})")
    void saveOrder(Order order);

    @Select("select * from orders where employer_id=#{employerId}")
    List<Order> getOrdersList(String employerId);

    @Select("select * from orders where id=#{id}")
    Order findById(String id);

    @Select("update orders set status=#{status} where id=#{id}")
    void updateStatus(Order order);
}
