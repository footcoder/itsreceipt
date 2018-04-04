package com.backend.web.controller;

import com.backend.web.domain.SignupParam;
import com.backend.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RequestMapping("/user")
@RestController
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/sign-in")
    public String signinUser(@RequestBody SignupParam signupParam){

        userService.signinUser(signupParam);

        return "";
    }

    @GetMapping(value = "/hello")
    public String sampleHello(){



        return "hello world";
    }
}
