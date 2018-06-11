package com.roy.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.roy.facade.redis.RedisService;
import com.roy.facade.user.UserService;
import com.roy.mapper.UserMapper;
import com.roy.model.user.User;
import com.roy.req.user.LoginReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@com.alibaba.dubbo.config.annotation.Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Reference
    RedisService redisService;

    public User login(LoginReq loginReq) {
        return null;
    }

    @Override
    public User findUser(String userId) {
        if(redisService.get("findUser"+userId) != null){
            return (User)redisService.get("findUser"+userId);
        }
        User user = userMapper.findUserByUserid(userId);
        redisService.set("findUser"+userId,user);
        return user;
    }

    @Override
    public User findUserByUsername(String username) {

        Object obj = redisService.get("findUserByUsername"+username);
        if(obj != null){
            return (User)obj;
        }
        User user = userMapper.findUserByName(username);
        redisService.set("findUserByUsername"+username,user);
        return user;
    }

}
