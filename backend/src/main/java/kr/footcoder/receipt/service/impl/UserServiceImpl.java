package kr.footcoder.receipt.service.impl;

import kr.footcoder.receipt.domain.SignupParam;
import kr.footcoder.receipt.domain.User;
import kr.footcoder.receipt.mapper.UserMapper;
import kr.footcoder.receipt.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;


    public UserDetails loadUserByUsername(String username){

        User user = userMapper.findUserByEmail(username);
        user.setAuthorities(getAuthorities(user.getRole()));
        return user;
    }

    private Collection<GrantedAuthority> getAuthorities(String role) {

        List<GrantedAuthority> aurhorities = new ArrayList<>();
        aurhorities.add(new SimpleGrantedAuthority(role));
        return aurhorities;
    }


    public boolean signupUser(SignupParam signupParam) {

        // 패스워드 암호화
        signupParam.setPassword(new BCryptPasswordEncoder().encode(signupParam.getPassword()));

        int result = userMapper.getExistUser(signupParam.getEmail());

        if (result == 0) {
            userMapper.signupUser(signupParam);
            return true;
        }

        return false;
    }


    /*public PasswordEncoder passwordEncoder() {
        return this.passwordEncoder;
    }*/

}
