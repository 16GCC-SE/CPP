package com.sixgiants.cpp.controller;

import com.sixgiants.cpp.entity.Order;
import com.sixgiants.cpp.service.impl.OrderListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderListController {
    @Autowired
    private OrderListServiceImpl orderListServiceImpl;

    @GetMapping("/orders")
    @ResponseBody
    public List<Order> getOrders() {
        List<Order> orders = orderListServiceImpl.getOrders();
        return orders;
    }
    @GetMapping("/length")
    @ResponseBody
    public int getLengthOfUsers() {
        return orderListServiceImpl.getLengthOfOrders();
    }

    @PostMapping("/settlement")
    @ResponseBody
    public  List<Order> getSettlement(@RequestBody String param) {
        return orderListServiceImpl.getSettlementMethod(param);
    }

    @PostMapping("/sex")
    @ResponseBody
    public  List<Order> getSex(@RequestBody String param) {
        return orderListServiceImpl.getSexNeed(param);
    }


    @PostMapping("/salary")
    @ResponseBody
    public  List<Order> getSalary(@RequestBody String param) {
        return orderListServiceImpl.getSalarySort(param);
    }

    @PostMapping("/jobtype")
    @ResponseBody
    public  List<Order> getJobType(@RequestBody String param) {
        return orderListServiceImpl.getJobType(param);
    }

    @PostMapping("/release")
    @ResponseBody
    public  List<Order> getRelease(@RequestBody String param) {
        return orderListServiceImpl.getReleaseSort(param);
    }

    @PostMapping("/classification")
    @ResponseBody
    public  List<Order> getClassification(@RequestBody String param) {
        return orderListServiceImpl.getClassification(param);
    }

}
