package net.abadguy.controller;

import lombok.extern.slf4j.Slf4j;
import net.abadguy.entity.User;
import net.abadguy.service.Userservice;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    Userservice userservice;


    @RequestMapping("/login")
    public String login(String username,String password){
        log.info("-----------进入----------------");
        //获取主体对象
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username,password));
            log.info("登录成功");
            return "redirect:/index";
        }catch (UnknownAccountException e){
            e.printStackTrace();
            log.error("没有当前用户名");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            log.error("密码错误");
        }
        log.error("登录失败");
        return "redirect:/login";
    }



    @RequestMapping("/register")
    public String regist(User user){
        log.info("--------注册--------");
        try {
            userservice.register(user);
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/register";
        }
        return "redirect:/login";
    }


    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
    }
}
