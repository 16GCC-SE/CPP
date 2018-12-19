package com.sixgiants.cpp.service.impl;

import com.sixgiants.cpp.dao.OrderDao;
import com.sixgiants.cpp.entity.Order;
import com.sixgiants.cpp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl {
    @Autowired
    private OrderDao orderDao;

    public void release(Order order){
        orderDao.saveOrder(order);
    }
}
