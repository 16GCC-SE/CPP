package com.sixgiants.cpp.mapper;

import com.sixgiants.cpp.entity.Order;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface OrderDetailMapper {
    @Select("select * from orders where id=#{id}")
    Order findOrderById(String id);
}
