package com.backend.web.service.impl;

import com.backend.web.domain.SignupParam;
import com.backend.web.mapper.UserMapper;
import com.backend.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void signinUser(SignupParam signupParam) {
        userMapper.signinUser(signupParam);
    }
}
