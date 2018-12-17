package com.sixgiants.cpp.service.impl;

import com.sixgiants.cpp.dao.OrderDetailDao;
import com.sixgiants.cpp.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl {
    @Autowired
    private OrderDetailDao orderDetailDao;

    public Order getOrderDetail(String id) {
        return orderDetailDao.findOrderById(id);
    }
}
