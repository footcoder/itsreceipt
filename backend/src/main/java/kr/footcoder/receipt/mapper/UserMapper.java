package kr.footcoder.receipt.mapper;

import kr.footcoder.receipt.domain.SignupParam;
import kr.footcoder.receipt.domain.UserSession;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    UserSession findUserByEmail(String email);

    void signupUser(SignupParam signupParam);

    int getExistUser(String email);


}
