package kr.footcoder.receipt.service;

import kr.footcoder.receipt.domain.SignupParam;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService extends UserDetailsService {

    boolean signupUser(SignupParam signupParam);

    //PasswordEncoder passwordEncoder();
}
