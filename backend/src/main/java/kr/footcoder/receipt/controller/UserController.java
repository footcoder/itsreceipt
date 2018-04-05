package kr.footcoder.receipt.controller;

import kr.footcoder.receipt.domain.SignupParam;
import kr.footcoder.receipt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/sign-up")
    public String signupUser(@RequestBody SignupParam signupParam){

        userService.signupUser(signupParam);

        return "";
    }

    @GetMapping(value = "/hello")
    public String sampleHello(){



        return "hello world";
    }
}
