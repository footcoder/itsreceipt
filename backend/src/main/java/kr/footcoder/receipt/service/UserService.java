package kr.footcoder.receipt.service;

import kr.footcoder.receipt.domain.SignupParam;
import kr.footcoder.receipt.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService{

    User readUser(String email);

    boolean signupUser(SignupParam signupParam);

    void deleteUser(String email);


}
