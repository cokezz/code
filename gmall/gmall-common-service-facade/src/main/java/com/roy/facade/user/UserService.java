package com.roy.facade.user;

import com.roy.model.user.User;
import com.roy.req.user.LoginReq;


public interface UserService {

    public User login(LoginReq loginReq);

    public User findUser(String userId);

    User findUserByUsername(String username);

}
