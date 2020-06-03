package net.abadguy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class RouteController {

    @RequestMapping("/login")
    public String login(){
        log.info("跳转到login.html");
        return "login";
    }

    @RequestMapping("/register")
    public String register(){
        return "regist";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

}
