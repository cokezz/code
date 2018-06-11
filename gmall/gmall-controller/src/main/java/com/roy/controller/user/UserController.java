package com.roy.controller.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.roy.facade.user.UserService;
import com.roy.req.user.LoginReq;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Reference
    private UserService userService;


    @GetMapping("/")
    String home(){
        return "hello World!";
    }

    @GetMapping("/getUserInfo")
    String getUserInfo(){
        userService.findUserByUsername("roy");
        return "hello World!!!!!";
    }
}
