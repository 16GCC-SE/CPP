package com.sixgiants.cpp.controller;

import com.sixgiants.cpp.entity.User;
import com.sixgiants.cpp.service.impl.UserServiceImpl;
import com.sixgiants.cpp.util.MD5Util;
import com.sixgiants.cpp.util.UUIDutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;


    //登录
    @RequestMapping("/login")
    public String Login(){
        return "visitor/login.html";
    }
    @RequestMapping("/login_action")
    public String login_action(){
        return "visitor/main1.html";
    }
//    @RequestMapping("/not_user_login_action")
//    public String not_user_login_action(){
//        return "visitor/main1.html";
//    }


    @PostMapping("/confirmName")
    @ResponseBody
    public int confirmName(@RequestBody String username){
        return userServiceImpl.confirmName(username);
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


    @RequestMapping("/register")
    public String Register(Model model){
        return "visitor/register.html";
    }
    @Value("${image.store}")
    private String saveLocation;
    @PostMapping("/register_form")
    @ResponseBody
    public void register(@RequestPart User user, @RequestPart MultipartFile[] files) throws IOException {
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String childDirectory  = df.format(now);

        String Directory = ResourceUtils.getFile("classpath:static")+"\\\\"+"upload";
        File storeDirectory = new File(Directory);
        //String p = ResourceUtils.getFile("classpath:static")+"\\\\";
        if (!storeDirectory.exists()) {
            storeDirectory.mkdir();
        }
        for (MultipartFile file : files) {
            Path path = Paths.get(storeDirectory + "/" + UUIDutil.getUUID() + file.getOriginalFilename());
            file.transferTo(path);
            user.setHeadIcon(path.toString().replaceAll("\\\\","/"));

        }
        userServiceImpl.register(user);
    }

    @RequestMapping("/register_action")
    public String register_action(){
        return "visitor/login.html";
    }

//    @GetMapping("/update")
//    public String toUpdate(Model model){
//        return userServiceImpl.toUpdate(model);
//    }

//    @GetMapping("/update_action")
//    public String updateUser(User user,Model model){
//        return userServiceImpl.updateUser(user,model);
//    }


    @GetMapping("/loginOut")
    public String loginOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/login.html";
    }
}
