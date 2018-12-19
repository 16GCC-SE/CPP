package com.sixgiants.cpp.controller;

import com.sixgiants.cpp.entity.User;
import com.sixgiants.cpp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/update")
public class UpdateController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/update_email_and_phone")
    @ResponseBody
    public boolean updateEmailAndPhone(@RequestPart User user,Model model){
        userService.updateUser(user);
        userService.login(model);
        return true;
    }

    @RequestMapping("/oldPassword")
    @ResponseBody
    public int OldPassword(@RequestBody String password){
        return userService.OldPassword(password);
    }

    @RequestMapping("/newPassword")
    @ResponseBody
    public boolean NewPassword(@RequestBody User user){
        userService.NewPassword(user);
        return true;
    }

    @RequestMapping("/back")
    public String back(){
        return "visitor/login.html";
    }
}
