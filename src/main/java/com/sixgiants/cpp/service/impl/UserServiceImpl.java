package com.sixgiants.cpp.service.impl;

import com.sixgiants.cpp.dao.UserDao;
import com.sixgiants.cpp.entity.User;
import com.sixgiants.cpp.util.DateFormatUtil;
import com.sixgiants.cpp.util.MD5Util;
import com.sixgiants.cpp.util.UUIDutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class UserServiceImpl {
    @Autowired
    private UserDao userDao;

    public String login(String name, String password,Model model,HttpServletRequest request) {
        String passwordMD5 = MD5Util.md5(password);
        User user = userDao.findByNameAndPsw(name, passwordMD5);
        if (user!= null) {
            System.out.println("登录成功！");
            request.getSession().setAttribute("user",user);
            model.addAttribute("user",user);
            return "success.html";
        }else {
            if (userDao.findByName(name)!=null){
                System.out.println("登录失败！");
                return "redirect:/login.html";
            }else {
                return "sign.html";
            }
        }
    }

    public String register(User user,HttpServletRequest request){
        String rePassword = request.getParameter("rePassword");
        String username = user.getName();
        if (userDao.findByName(username)==null){
            if (!rePassword.equals(user.getPassword())){
                System.out.println("密码不一致");
                return "redirect:/register.html";
            }
            if (!user.getEmail().matches("\\b^['_a-z0-9-\\+]+(\\.['_a-z0-9-\\+]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*\\.([a-z]{2}|aero|arpa|asia|biz|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|nato|net|org|pro|tel|travel|xxx)$\\b")){
                System.out.println("邮箱格式错误");
                return "redirect:/register.html";
            }

            user.setPassword(MD5Util.md5(user.getPassword()));
            user.setCreateTime(new Date());
            userDao.saveUser(user);
            System.out.println("注册成功");
            return "redirect:/login.html";
        }else {
            System.out.println("该用户已经存在");
            return "redirect:/register.html";
        }
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
        System.out.println("修改成功");

        model.addAttribute("user",user);

        return "success.html";
    }
}
