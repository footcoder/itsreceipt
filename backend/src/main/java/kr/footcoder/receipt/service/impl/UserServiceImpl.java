package kr.footcoder.receipt.service.impl;

import kr.footcoder.receipt.domain.SignupParam;
import kr.footcoder.receipt.mapper.UserMapper;
import kr.footcoder.receipt.service.UserService;
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
    public boolean signupUser(SignupParam signupParam) {

        int result = userMapper.getExistUser(signupParam.getEmail());

        if(result == 0){
            userMapper.signupUser(signupParam);
            return true;
        }

        return false;

    }
}
