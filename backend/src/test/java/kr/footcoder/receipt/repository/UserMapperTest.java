package kr.footcoder.receipt.repository;

import kr.footcoder.receipt.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void 회원조회테스트(){
        User user = userMapper.readUser("test1@test.com");
        if(user == null){
            log.error("user is null");
        }

        assertThat(user.getUsername(), is("test1@test.com"));
        assertThat(user.getPassword(), is("1234"));
        assertThat(user.getRole(), is("ROLE_USER"));
    }

}
