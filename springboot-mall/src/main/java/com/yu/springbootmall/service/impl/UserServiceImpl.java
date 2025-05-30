package com.yu.springbootmall.service.impl;

import com.yu.springbootmall.dao.UserDao;
import com.yu.springbootmall.dto.UserRegisterRequest;
import com.yu.springbootmall.model.User;
import com.yu.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }
}
