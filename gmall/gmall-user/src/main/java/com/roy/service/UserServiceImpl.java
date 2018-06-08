package com.roy.service;

import com.roy.facade.user.UserService;
import com.roy.model.user.User;
import com.roy.req.user.LoginReq;
import org.springframework.stereotype.Service;

@Service
@com.alibaba.dubbo.config.annotation.Service
public class UserServiceImpl implements UserService {

    public User login(LoginReq loginReq) {
        return null;
    }
}
