package com.sixgiants.cpp.service.impl;

import com.sixgiants.cpp.dao.UserDao;
import com.sixgiants.cpp.entity.User;
import com.sixgiants.cpp.util.DateFormatUtil;
import com.sixgiants.cpp.util.MD5Util;
import com.sixgiants.cpp.util.UUIDutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl {
    @Autowired
    private UserDao userDao;

    public int confirmName(String username){
        User user = userDao.findByName(username);
        if(user==null){
            return 1;
        }
        else{
            return 0;
        }
    }

    public void login(Model model){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User user = (User)authentication.getPrincipal();
        model.addAttribute("user",user);
    }

    public int confirmPassword(String message[]){
        String password = message[0];
        String rePassword = message[1];
        if (password.equals(rePassword)){
            return 1;
        }else {
            return 0;
        }
    }

    public int confirmEmail(String email){
        if (!email.matches("\\b^['_a-z0-9-\\+]+(\\.['_a-z0-9-\\+]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*\\.([a-z]{2}|aero|arpa|asia|biz|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|nato|net|org|pro|tel|travel|xxx)$\\b")){
            return 0;
        }else {
            return 1;
        }
    }

    public int confirmPhone(String phone){
        if (!phone.matches("^[1][3,4,5,7,8][0-9]{9}$")){
            return 0;
        }else {
            return 1;
        }
    }


    public void register(User user){
        userDao.saveUser(user);
    }

//    public String toUpdate(Model model){
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        Authentication authentication = securityContext.getAuthentication();
//        User user = (User)authentication.getPrincipal();
//
//        model.addAttribute("user",user);
//        return "some/update.html";
//    }

    public User updateUser(User user){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User NewUser = (User)authentication.getPrincipal();
//        NewUser.setName(user.getName());
        NewUser.setEmail(user.getEmail());
        NewUser.setPhone(user.getPhone());
        NewUser.setSex(user.getSex());
        userDao.updateUser(NewUser);
//        model.addAttribute("user",NewUser);

        return NewUser;
    }

    public int OldPassword(String password){
        String MDpassword = MD5Util.md5(password);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User user = (User)authentication.getPrincipal();
        if (!user.getPassword().equals(MDpassword)){
            return 0;
        }else {
            return 1;
        }
    }

    public User NewPassword(User user){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User NewUser = (User)authentication.getPrincipal();
        NewUser.setPassword(MD5Util.md5(user.getPassword()));
        userDao.updatePassword(NewUser);
        return NewUser;
    }

    public User findUserById(String employerId){
        return userDao.findById(employerId);
    }
}
