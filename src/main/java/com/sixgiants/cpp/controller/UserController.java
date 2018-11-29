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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/test")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/login")
    public String toLogin(){
        return "login.html";
    }

    @PostMapping("/login_action")
    public String login(String name, String password,Model model,HttpServletRequest request) {
        String passwordMD5 = MD5Util.md5(password);
        User user = userServiceImpl.findByNameAndPsw(name, passwordMD5);
        if (user!= null) {
            System.out.println(user.getName()+user.getPassword());
            System.out.println("登录成功！");
            request.getSession().setAttribute("user",user);
            model.addAttribute("user",user);
            return "success.html";
        }else {
            if (userServiceImpl.findByName(name)!=null){
                System.out.println("登录失败！");
                return "redirect:/login.html";
            }else {
                return "sign.html";
            }
        }
    }

    @GetMapping("/register")
    public String register(User user){
        String username = user.getName();
        if (userServiceImpl.findByName(username)==null){
            user.setPassword(MD5Util.md5(user.getPassword()));
            System.out.println(user.getName()+user.getPassword());
            userServiceImpl.saveUser(user);
            return "test.html";
        }else {
            System.out.println("该用户已经存在");
            return "sign.html";
        }
    }

    @GetMapping("/update")
    public String toUpdate(HttpServletRequest request, Model model){
        //String id =  request.getParameter("id");
        HttpSession ids = request.getSession();
        User getIds = (User)ids.getAttribute("user");

        String id = getIds.getId();
        User user = userServiceImpl.findById(id);
        user.setPassword("");

        model.addAttribute("user",user);
        return "update.html";
    }

    @GetMapping("/update_action")
    public String updateUser(User user,Model model){

        user.setPassword(MD5Util.md5(user.getPassword()));
        userServiceImpl.updateUser(user);

        model.addAttribute("user",user);

        return "success.html";
    }

    @GetMapping("/loginOut")
    public String loginOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/login.html";
    }
}
