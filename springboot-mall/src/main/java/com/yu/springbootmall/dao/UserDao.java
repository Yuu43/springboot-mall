package com.yu.springbootmall.dao;

import com.yu.springbootmall.dto.UserRegisterRequest;
import com.yu.springbootmall.model.User;

public interface UserDao {

    User getUserById(Integer userId);

    User getUserByEmail(String email);

    Integer createUser(UserRegisterRequest userRegisterRequest);
}
