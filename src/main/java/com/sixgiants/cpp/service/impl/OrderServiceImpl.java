package com.sixgiants.cpp.service.impl;

import com.sixgiants.cpp.dao.OrderDao;
import com.sixgiants.cpp.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl {
    @Autowired
    private OrderDao orderDao;

    public void release(Order order){
        order.setStatus(0);
        order.setCreateTime(new Date());
        orderDao.saveOrder(order);
    }
}
