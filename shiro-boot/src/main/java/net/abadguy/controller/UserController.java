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


    @RequestMapping("/getImage")
    public void getImage(){

    }

    @RequestMapping("/login")
    public String login(String username,String password){
        //获取主体对象
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username,password));
            return "redirect:/index.jsp";
        }catch (UnknownAccountException e){
            e.printStackTrace();
            log.error("没有当前用户名");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            log.error("密码错误");
        }
        return "redirect:/login.jsp";
    }



    @RequestMapping("/register")
    public String regist(User user){
        try {
            userservice.register(user);
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/regist.jsp";
        }
        return "redirect:/login.jsp";
    }
}
