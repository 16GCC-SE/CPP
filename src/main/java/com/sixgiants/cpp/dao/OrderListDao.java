package com.sixgiants.cpp.dao;

import com.sixgiants.cpp.entity.Order;
import com.sixgiants.cpp.mapper.OrderListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderListDao {
    @Autowired
    private OrderListMapper orderListMapper;
    public List<Order> findOrders() {
        return orderListMapper.findOrders();
    }
}
