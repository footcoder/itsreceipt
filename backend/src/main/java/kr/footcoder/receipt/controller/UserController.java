package kr.footcoder.receipt.controller;

import kr.footcoder.receipt.domain.SignupParam;
import kr.footcoder.receipt.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController extends BaseController {

    @GetMapping(value = "/hello")
    public String hello() {

        return "hello world";
    }

    private final UserService userService;

    @PostMapping(value = "/sign-up")
    public ModelMap signupUser(@RequestBody SignupParam signupParam) {

        userService.signupUser(signupParam);

        return success();
    }

    @GetMapping(value = "/sign-in")
    public String signinUser() {

        return "hello world";
    }
}
