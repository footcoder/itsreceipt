package kr.footcoder.receipt.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.footcoder.receipt.domain.AuthenticationRequest;
import kr.footcoder.receipt.domain.SignupParam;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


public class UserControllerRest {

    private final MockMvc mvc;
    private final SignupParam signupParam;
    private final AuthenticationRequest authenticationRequest;

    public UserControllerRest(final MockMvc mvc, SignupParam signupParam, AuthenticationRequest authenticationRequest) {
        this.mvc = mvc;
        this.signupParam = signupParam;
        this.authenticationRequest = authenticationRequest;
    }

    public MockHttpServletResponse signupUser() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        return this.mvc.perform(
                post("/user/sign-up")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(signupParam))
        ).andReturn().getResponse();
    }


    public MockHttpServletResponse hello() throws Exception {

        return this.mvc.perform(
                get("/user/hello")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)

        ).andReturn().getResponse();
    }

    public MockHttpServletResponse signinUser() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        return this.mvc.perform(
                post("/user/sign-in")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(authenticationRequest))
        ).andReturn().getResponse();
    }

}
