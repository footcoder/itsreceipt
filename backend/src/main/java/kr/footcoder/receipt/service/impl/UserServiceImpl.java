package kr.footcoder.receipt.service.impl;

import kr.footcoder.receipt.domain.SignupParam;
import kr.footcoder.receipt.domain.User;
import kr.footcoder.receipt.repository.UserMapper;
import kr.footcoder.receipt.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public User readUser(String email) {
        return userMapper.readUser(email);
    }

    public boolean signupUser(SignupParam signupParam) {

        int result = userMapper.isExistUser(signupParam.getEmail());

        if (result == 0) {
            String rawPassword = signupParam.getPassword();
            String encodedPassword = new BCryptPasswordEncoder().encode(rawPassword);
            signupParam.setPassword(encodedPassword);

            userMapper.signupUser(signupParam);
            return true;
        }

        return false;
    }


    public void deleteUser(String email) {

        int result = userMapper.isExistUser(email);

        if(result == 0){
            throw new AssertionError("탈퇴 시킬 유저가 존재하지 않습니다. email : " + email);
        }

        if(result > 1){
            throw new AssertionError("중복된 유저가 존재합니다. email : " + email);
        }

        int resultCnt = userMapper.deleteUser(email);

        if(resultCnt != 1) throw new IllegalStateException("탈퇴처리가 정상적으로 이루어지지 않았습니다");
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userMapper.readUser(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with user email");
        }

        user.setAuthorities(getAuthorities(user.getRole()));

        return user;
    }

    private Collection<GrantedAuthority> getAuthorities(String role) {

        List<GrantedAuthority> aurhorities = new ArrayList<>();
        aurhorities.add(new SimpleGrantedAuthority(role));
        return aurhorities;
    }


}
