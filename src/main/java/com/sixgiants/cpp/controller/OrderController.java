package com.sixgiants.cpp.controller;

import com.sixgiants.cpp.entity.Order;
import com.sixgiants.cpp.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/test")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/release")
    @ResponseBody
    public boolean release(@RequestBody Order order){
        orderService.release(order);
        return true;
    }

    @RequestMapping("/goRelease")
    public String goRelease(){
        return "order.html";
    }
}
