package com.sixgiants.cpp.service.impl;

import com.sixgiants.cpp.dao.UserDao;
import com.sixgiants.cpp.entity.User;
import com.sixgiants.cpp.util.DateFormatUtil;
import com.sixgiants.cpp.util.MD5Util;
import com.sixgiants.cpp.util.UUIDutil;
import org.springframework.beans.factory.annotation.Autowired;
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

    public String login(String message[],HttpServletRequest request,Model model){
        String passwordMD5 = MD5Util.md5(message[1]);
        User user = userDao.findByNameAndPsw(message[0], passwordMD5);
        if (user!=null){
            request.getSession().setAttribute("user",user);
            model.addAttribute("user",user);
            return "success";
        }else {
            return "not";
        }
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
        if (phone.length()!=11){
            return 0;
        }else {
            return 1;
        }
    }


    public String register(User user){
        user.setPassword(MD5Util.md5(user.getPassword()));
        user.setCreateTime(new Date());
        userDao.saveUser(user);
        return "redirect:/login.html";
    }

    public String toUpdate(HttpServletRequest request, Model model){
        HttpSession ids = request.getSession();
        User getIds = (User)ids.getAttribute("user");

        String id = getIds.getId();
        User user = userDao.findById(id);
        user.setPassword("");

        model.addAttribute("user",user);
        return "update.html";
    }

    public String updateUser(User user,Model model,HttpServletRequest request){
        HttpSession createTime = request.getSession();
        User date = (User) createTime.getAttribute("user");
        user.setCreateTime(date.getCreateTime());
        user.setPassword(MD5Util.md5(user.getPassword()));
        userDao.updateUser(user);
        model.addAttribute("user",user);

        return "success.html";
    }
}
