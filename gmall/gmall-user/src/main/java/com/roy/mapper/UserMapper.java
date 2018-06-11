package com.roy.mapper;

import com.roy.model.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User findUserByUserid(String userId);

    boolean insertUser (User user);

    User findUserByName(String userName);
}
