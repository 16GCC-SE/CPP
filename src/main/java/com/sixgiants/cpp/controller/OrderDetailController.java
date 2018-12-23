package com.sixgiants.cpp.controller;

import com.sixgiants.cpp.dao.EmployeeDao;
import com.sixgiants.cpp.dao.UserDao;
import com.sixgiants.cpp.entity.Employee;
import com.sixgiants.cpp.entity.Order;
import com.sixgiants.cpp.entity.User;
import com.sixgiants.cpp.service.impl.OrderDetailServiceImpl;
import com.sixgiants.cpp.service.impl.OrderServiceImpl;
import com.sixgiants.cpp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private OrderServiceImpl orderService;

    private String employerId;//发布兼职用户id
    private String orderId;//该兼职id


    @PostMapping("/orderdetail")
    @ResponseBody
    public Order getOrderDetail(@RequestBody String id) {
        Order order = orderDetailServiceImpl.getOrderDetail(id);
        employerId = order.getEmployerId();
        orderId = order.getId();
        return orderDetailServiceImpl.getOrderDetail(id);
    }


    @RequestMapping("/detail_action")
    public String login_action(Model model){
        User user = userDao.findById(employerId);
        model.addAttribute("user",user);
        return "visitor/detail.html";
    }

    @RequestMapping("/save_order")
    @ResponseBody
    public int saveOrder(Employee employee){
        try {
            Order order = orderService.findById(orderId);
            if (employeeDao.CountOrder(orderId)>=order.getNeedNumber()){
                order.setStatus("禁止报名");
                orderService.updateStatus(order);
                return 2;
            }
            SecurityContext securityContext = SecurityContextHolder.getContext();
            Authentication authentication = securityContext.getAuthentication();
            User user = (User)authentication.getPrincipal();
            employee.setOrderId(orderId);
            employee.setEmployeeId(user.getId());
            employeeDao.saveOrder(employee);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }
}
