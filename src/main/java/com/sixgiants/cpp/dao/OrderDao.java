package com.sixgiants.cpp.dao;

import com.sixgiants.cpp.entity.Order;
import com.sixgiants.cpp.mapper.OrderMapper;
import com.sixgiants.cpp.util.UUIDutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao {
    @Autowired
    private OrderMapper orderMapper;

    public void saveOrder(Order order){
        order.setId(UUIDutil.getUUID());
        order.setEmployerId(UUIDutil.getUUID());
        orderMapper.saveOrder(order);
    }
}
