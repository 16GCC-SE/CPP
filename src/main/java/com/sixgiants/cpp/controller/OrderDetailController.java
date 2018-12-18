package com.sixgiants.cpp.controller;

import com.sixgiants.cpp.entity.Order;
import com.sixgiants.cpp.service.impl.OrderDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderDetailController {
    @Autowired
    public OrderDetailServiceImpl orderDetailServiceImpl;

    @PostMapping("/orderdetail")
    @ResponseBody
    public Order getOrderDetail(@RequestBody String id) {
        return orderDetailServiceImpl.getOrderDetail(id);
    }
}
