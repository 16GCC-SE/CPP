package com.sixgiants.cpp.controller;

import com.sixgiants.cpp.entity.User;
import com.sixgiants.cpp.service.impl.UserServiceImpl;
import com.sixgiants.cpp.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/confirmName")
    @ResponseBody
    public int confirmName(@RequestBody String username){
        return userServiceImpl.confirmName(username);
    }
    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody String[] message,HttpServletRequest request,Model model){
        return userServiceImpl.login(message,request,model);
    }

    @RequestMapping("/login_action")
    public String login_action(HttpSession session,Model model){
        User user = (User)session.getAttribute("user");
        model.addAttribute("user",user);
        return "success.html";
    }

    @PostMapping("/confirmPassword")
    @ResponseBody
    public int confirmPassword(@RequestBody String[] message){
        return userServiceImpl.confirmPassword(message);
    }

    @PostMapping("/confirmEmail")
    @ResponseBody
    public int confirmEmail(@RequestBody String email){
        return userServiceImpl.confirmEmail(email);
    }

    @PostMapping("/confirmPhone")
    @ResponseBody
    public int confirmPhone(@RequestBody String phone){
        return userServiceImpl.confirmPhone(phone);
    }

//    @PostMapping("/login_action")
//    public String login(String name, String password,Model model,HttpServletRequest request) {
//        return userServiceImpl.login(name,password,model,request);
//    }

    @PostMapping("/register")
    public String register(User user){
        return userServiceImpl.register(user);
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
