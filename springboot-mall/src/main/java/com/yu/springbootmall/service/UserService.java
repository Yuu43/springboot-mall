package com.yu.springbootmall.service;

import com.yu.springbootmall.dto.UserRegisterRequest;
import com.yu.springbootmall.model.User;

public interface UserService {

    User getUserById(Integer userId);

    Integer register(UserRegisterRequest userRegisterRequest);

}
