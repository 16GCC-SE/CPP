package com.sixgiants.cpp.dao;

import com.sixgiants.cpp.entity.Order;
import com.sixgiants.cpp.entity.User;
import com.sixgiants.cpp.mapper.OrderMapper;
import com.sixgiants.cpp.util.UUIDutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class OrderDao {
    @Autowired
    private OrderMapper orderMapper;

    public void saveOrder(Order order){
        order.setId(UUIDutil.getUUID());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User user = (User)authentication.getPrincipal();
        order.setEmployerId(user.getId());
        order.setStatus(0);
        order.setCreateTime(new Date());
        orderMapper.saveOrder(order);
    }
}
