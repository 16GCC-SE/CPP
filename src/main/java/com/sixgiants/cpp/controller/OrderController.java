package com.sixgiants.cpp.controller;

import com.sixgiants.cpp.dao.EmployeeDao;
import com.sixgiants.cpp.entity.Employee;
import com.sixgiants.cpp.entity.Order;
import com.sixgiants.cpp.entity.User;
import com.sixgiants.cpp.service.impl.OrderDetailServiceImpl;
import com.sixgiants.cpp.service.impl.OrderServiceImpl;
import com.sixgiants.cpp.service.impl.UserServiceImpl;
import com.sixgiants.cpp.util.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    public OrderDetailServiceImpl orderDetailService;

    @PostMapping("/release")
    @ResponseBody
    public boolean release(@RequestBody Order order){
        if (order.getTitle() != null && order.getTitle() != "") {
            if (order.getContent() != null && order.getContent() != "") {
                if (order.getPlace() != null && order.getPlace() != "") {
                    if (order.getNeedNumber() > 0 && order.getDeadline() != null && order.getSalary() > 0) {
                        orderService.release(order);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @RequestMapping("/goRelease")
    public String goRelease(Model model){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User user = (User)authentication.getPrincipal();
        model.addAttribute("user",user);
        if (user!=null){
            return "some/job_publish.html";
        }
        return "visitor/login.html";
    }

    @RequestMapping("/goMain")
    public String goMain(){
        return "visitor/main1.html";
    }

    @RequestMapping("/goFirst")
    public String goFirst(Model model){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User user = (User)authentication.getPrincipal();
        model.addAttribute("user",user);
        if (user!=null){
            return "some/update_information";
        }
        return "visitor/login.html";
    }

    @RequestMapping("/isreceive")
    @ResponseBody
    public Boolean isReceive(@RequestBody String orderId){
        boolean isReceive = false;
        int count;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User user = (User)authentication.getPrincipal();
        count = employeeDao.findEmployeeByOrderIdAndEmployeeId(orderId, user.getId());
        if (count == 1) {
            isReceive = true;
        }
        return isReceive;
    }

    @RequestMapping("/history")
    public String goHistory(Model model){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User user = (User)authentication.getPrincipal();
        model.addAttribute("user",user);
        if (user!=null){
            return "some/historyorders.html";
        }
        return "visitor/login.html";
    }

    @GetMapping("/length")
    @ResponseBody
    public int getLengthOfUsers() {

        return getOrdersList().size();
    }

    @GetMapping("/ordersList")
    @ResponseBody
    public List<Order> getOrdersList() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User user = (User)authentication.getPrincipal();
        List<Order> orders = orderService.getOrdersList(user.getId());
        for (Order order : orders){
            order.setCreateTime(java.sql.Date.valueOf(DateFormatUtil.dateFormat(order.getCreateTime())));
            order.setCount(employeeDao.CountOrder(order.getId()));
        }
        return orders;
    }
}
