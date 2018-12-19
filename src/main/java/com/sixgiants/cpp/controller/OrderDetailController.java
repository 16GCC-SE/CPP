package com.sixgiants.cpp.controller;

import com.sixgiants.cpp.dao.UserDao;
import com.sixgiants.cpp.entity.Order;
import com.sixgiants.cpp.entity.User;
import com.sixgiants.cpp.service.impl.OrderDetailServiceImpl;
import com.sixgiants.cpp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;

@Controller
public class OrderDetailController {
    @Autowired
    public OrderDetailServiceImpl orderDetailServiceImpl;

    @Autowired
    private UserDao userDao;

    private String employerId;

    @PostMapping("/orderdetail")
    @ResponseBody
    public Order getOrderDetail(@RequestBody String id) {
        Order order = orderDetailServiceImpl.getOrderDetail(id);
        employerId = order.getEmployerId();
        return orderDetailServiceImpl.getOrderDetail(id);
    }

//    @RequestMapping("/detail_user")
//    public void detail_user(Model model){
//        userService.login(model);
//    }

    @RequestMapping("/detail_action")
    public String login_action(Model model){
        User user = userDao.findById(employerId);
        model.addAttribute("user",user);
        return "visitor/detail.html";
    }
}
