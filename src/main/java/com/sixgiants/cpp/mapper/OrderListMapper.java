package com.sixgiants.cpp.mapper;

import com.sixgiants.cpp.entity.Order;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderListMapper {
    @Select("select * from orders")
    List<Order> findOrders();
}
