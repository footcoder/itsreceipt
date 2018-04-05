package com.backend.web.controller.rest;

import com.backend.web.domain.SignupParam;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


public class UserControllerRest {

    private final MockMvc mvc;
    private final SignupParam signupParam;

    public UserControllerRest(final MockMvc mvc, SignupParam signupParam) {
        this.mvc = mvc;
        this.signupParam = signupParam;
    }

    public MockHttpServletResponse signinUser() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        return this.mvc.perform(
                post("/user/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signupParam))
        ).andReturn().getResponse();
    }


    public MockHttpServletResponse hello() throws Exception {

        return this.mvc.perform(
                get("/user/hello")
                        .contentType(MediaType.APPLICATION_JSON)

        ).andReturn().getResponse();
    }
}
