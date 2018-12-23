package com.sixgiants.cpp.service.impl;

import com.sixgiants.cpp.dao.OrderDetailDao;
import com.sixgiants.cpp.entity.Order;
import com.sixgiants.cpp.entity.User;
import com.sixgiants.cpp.util.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl {
    @Autowired
    private OrderDetailDao orderDetailDao;

    public Order getOrderDetail(String id) {
        Order order = orderDetailDao.findOrderById(id);
        order.setCreateTime(java.sql.Date.valueOf(DateFormatUtil.dateFormat(order.getCreateTime())));
        return order;
    }

    public Order findOrderById(String orderId){
        return orderDetailDao.findOrderById(orderId);
    }
}
