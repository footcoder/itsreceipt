package com.backend.web.mapper;

import com.backend.web.domain.SignupParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    void signinUser(SignupParam signupParam);

}
