package com.roy.mapper;

import com.roy.model.user.User;
import com.roy.req.user.UserQueryReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> findUsers(UserQueryReq userQueryReq);
}
