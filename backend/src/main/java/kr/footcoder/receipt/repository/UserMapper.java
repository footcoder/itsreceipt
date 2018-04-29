package kr.footcoder.receipt.repository;

import kr.footcoder.receipt.domain.SignupParam;
import kr.footcoder.receipt.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    User readUser(String email);

    void signupUser(SignupParam signupParam);

    int isExistUser(String email);

    int deleteUser(String email);
}
