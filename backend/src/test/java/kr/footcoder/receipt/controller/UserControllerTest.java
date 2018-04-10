package kr.footcoder.receipt.controller;


import kr.footcoder.receipt.controller.rest.UserControllerRest;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserControllerTest {


    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;
    private UserControllerRest userControllerRest;
    private SignupParam signupParam;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.signupParam = new SignupParam();
        signupParam.setEmail("signinUser2@tset.com");
        signupParam.setPassword("aaaa1111");
        signupParam.setMoneyType("2");

        userControllerRest = new UserControllerRest(mvc, signupParam);
    }


    @Test
    public void 정상적인_회원가입() throws Exception {
        HttpServletResponse response = userControllerRest.signinUser();

        assertThat(response.getStatus(), is(HttpStatus.OK.value()));

    }

    @Test
    public void 이미가입된_회원확인() throws Exception {
        HttpServletResponse response = userControllerRest.signinUser();

        assertThat(response.getStatus(), is(HttpStatus.OK.value()));

    }

    @Test
    public void 통신테스트()throws Exception {

        HttpServletResponse response = userControllerRest.hello();

        assertThat(response.getStatus(), is(HttpStatus.OK.value()));
    }

}
