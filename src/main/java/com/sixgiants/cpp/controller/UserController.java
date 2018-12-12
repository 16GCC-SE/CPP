package com.sixgiants.cpp.controller;

import com.sixgiants.cpp.entity.User;
import com.sixgiants.cpp.service.impl.UserServiceImpl;
import com.sixgiants.cpp.util.MD5Util;
import com.sixgiants.cpp.util.UUIDutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.plugin.util.UIUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/test")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    //登录验证处理
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


    //注册验证处理
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


    @Value("${CPP.upload.save-location}")
    private String saveLocation;
    @PostMapping("/register")
    @ResponseBody
    public void register(@RequestPart User user, @RequestPart MultipartFile[] files) throws IOException {
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String childDirectory  = df.format(now);

        String Directory = saveLocation + childDirectory;
        File storeDirectory = new File(Directory);

        if (!storeDirectory.exists()) {
            storeDirectory.mkdir();
            for (MultipartFile file : files) {
                Path path = Paths.get(Directory +"/" + UUIDutil.getUUID() + file.getOriginalFilename());
                System.out.println(path);
                file.transferTo(path);

            }
        }

        userServiceImpl.register(user);
    }

    @RequestMapping("/register_action")
    public String register_action(){
        return "redirect:/login.html";
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
