package com.sixgiants.cpp.controller;

import com.sixgiants.cpp.entity.User;
import com.sixgiants.cpp.service.impl.UserServiceImpl;
import com.sixgiants.cpp.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/test")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/login_action")
    public String login(String name, String password,Model model,HttpServletRequest request) {
        return userServiceImpl.login(name,password,model,request);
    }

    @PostMapping("/register")
    public String register(User user, HttpServletRequest request){
        return userServiceImpl.register(user,request);
    }

    @GetMapping("/update")
    public String toUpdate(HttpServletRequest request, Model model){
        return userServiceImpl.toUpdate(request,model);
    }

    @GetMapping("/update_action")
    public String updateUser(User user,Model model,HttpServletRequest request){
        return userServiceImpl.updateUser(user,model,request);
    }

    @GetMapping("/loginOut")
    public String loginOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/login.html";
    }
}
