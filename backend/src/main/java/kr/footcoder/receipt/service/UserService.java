package kr.footcoder.receipt.service;

import kr.footcoder.receipt.domain.SignupParam;

public interface UserService {
    boolean signupUser(SignupParam signupParam);
}
