package kr.footcoder.receipt.controller.service;

import kr.footcoder.receipt.domain.SignupParam;
import kr.footcoder.receipt.domain.User;
import kr.footcoder.receipt.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collection;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserServiceTest {


    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private User testUser;
    private SignupParam signupParam;



    @Before
    public void setup() {
        testUser = new User();
        testUser.setEmail("test1@test.com");
        testUser.setPassword("123123123");
        testUser.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_USER"));

        signupParam = new SignupParam();
        signupParam.setEmail("test1@test.com");
        signupParam.setPassword("123123123");
        signupParam.setMoneyType("1");


    }

    @Test
    public void createUserTest() {
        //userService.deleteUser(testUser.getUsername());
        userService.signupUser(signupParam);



        // 유저 검증
        User readUser = userService.readUser(testUser.getUsername());
        assertThat(readUser.getUsername(), is(testUser.getUsername()));

        // 비밀번호 검증
        PasswordEncoder passwordEncoder = bCryptPasswordEncoder;
        assertThat(passwordEncoder.matches("123123123", readUser.getPassword()), is(true));

        //권한 검증
        //assertThat(testUser.getAuthorities(), is(new SimpleGrantedAuthority(readUser.getRole())));


        /*
        //권한 검증
        Collection<? extends GrantedAuthority> authoritiesByUser = testUser.getAuthorities();

        Iterator<? extends GrantedAuthority> iterator = authoritiesByUser.iterator();

        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) readUser.getAuthorities();

        while (iterator.hasNext()) {
            GrantedAuthority authority = iterator.next();

            assertThat(authorities, hasItem(new SimpleGrantedAuthority(authority.getAuthority())));

        }
        */
    }



}
