package com.sixgiants.cpp.controller;

import com.sixgiants.cpp.entity.Order;
import com.sixgiants.cpp.entity.User;
import com.sixgiants.cpp.service.impl.OrderServiceImpl;
import com.sixgiants.cpp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/release")
    @ResponseBody
    public boolean release(@RequestBody Order order){
        if (order.getTitle()!=null || order.getTitle()!=""){
            if (order.getContent()!=null || order.getContent()!=""){
                if (order.getPlace()!=null || order.getPlace()!=""){
                    if (order.getNeedNumber()!=null || order.getDeadline()!=null){
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
}
