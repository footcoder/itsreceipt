package kr.footcoder.receipt.service;

import kr.footcoder.receipt.domain.SignupParam;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    boolean signupUser(SignupParam signupParam);

}
