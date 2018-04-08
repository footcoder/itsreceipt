package kr.footcoder.receipt.mapper;

import kr.footcoder.receipt.domain.SignupParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    void signupUser(SignupParam signupParam);

    int getExistUser(String email);
}
