package kr.footcoder.receipt.controller;

import kr.footcoder.receipt.domain.AuthenticationRequest;
import kr.footcoder.receipt.domain.AuthenticationToken;
import kr.footcoder.receipt.domain.SignupParam;
import kr.footcoder.receipt.domain.User;
import kr.footcoder.receipt.enumclass.ErrorCode;
import kr.footcoder.receipt.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@AllArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController extends BaseController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @GetMapping(value = "/hello")
    public String hello() {

        return "hello world";
    }

    /**
     * 회원 가입
     */
    @PostMapping(value = "/sign-up")
    public ModelMap signupUser(@RequestBody SignupParam signupParam) {

        if (!userService.signupUser(signupParam)) {
            return error(ErrorCode.ERR0002);
        }

        return success();
    }

    @PostMapping(value = "/sign-in")
    public AuthenticationToken login(
            @RequestBody AuthenticationRequest authenticationRequest,
            HttpSession session
    ) {
        String email = authenticationRequest.getEmail();
        String password = authenticationRequest.getPassword();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        User user = userService.readUser(email);

        return new AuthenticationToken(user.getEmail(), user.getAuthorities(), session.getId());

    }
}
