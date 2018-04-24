package kr.footcoder.receipt.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import kr.footcoder.receipt.controller.rest.UserControllerRest;
import kr.footcoder.receipt.domain.AuthenticationRequest;
import kr.footcoder.receipt.domain.SignupParam;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletResponse;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserControllerTest {


    @Autowired
    private WebApplicationContext context;
    private UserControllerRest userControllerRest;

    @Before
    public void setUp() {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        SignupParam signupParam = new SignupParam();
        signupParam.setEmail("signinUser@tset.com");
        signupParam.setPassword("1111");
        signupParam.setMoneyType("2");

        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail("test1@test.com");
        authenticationRequest.setPassword("123123123");

        userControllerRest = new UserControllerRest(mvc, signupParam, authenticationRequest);
    }


    @Test
    public void 정상적인_회원가입() throws Exception {
        HttpServletResponse response = userControllerRest.signupUser();

        assertThat(response.getStatus(), is(HttpStatus.OK.value()));

    }

    @Test
    public void 이미가입된_회원확인() throws Exception {
        HttpServletResponse response = userControllerRest.signupUser();

        assertThat(response.getStatus(), is(HttpStatus.OK.value()));

    }

    @Test
    public void 통신테스트() throws Exception {

        HttpServletResponse response = userControllerRest.hello();

        assertThat(response.getStatus(), is(HttpStatus.OK.value()));
    }

    @Test
    public void 인증_로그인_테스트() throws Exception{
        HttpServletResponse response = userControllerRest.signinUser();

        ObjectMapper objectMapper = new ObjectMapper();


        //String resultResponse = objectMapper.writeValueAsString(response);

        log.error("resultResponse : {}", objectMapper.writeValueAsString(response.getContentType()));

        assertThat(response.getStatus(), is(HttpStatus.OK.value()));
    }

}
