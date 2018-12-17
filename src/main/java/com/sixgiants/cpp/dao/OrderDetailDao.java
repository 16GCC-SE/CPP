package com.sixgiants.cpp.dao;

import com.sixgiants.cpp.entity.Order;
import com.sixgiants.cpp.mapper.OrderDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDetailDao {
    @Autowired
    private OrderDetailMapper orderDeatilMapper;
    public Order findOrderById(String id) {
        return orderDeatilMapper.findOrderById(id);
    }
}
