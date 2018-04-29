package kr.footcoder.receipt.controller;

import kr.footcoder.receipt.domain.AuthenticationRequest;
import kr.footcoder.receipt.domain.SignupParam;
import kr.footcoder.receipt.domain.User;
import kr.footcoder.receipt.repository.UserInfoRepository;
import kr.footcoder.receipt.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.google.common.base.Preconditions.checkNotNull;
import static kr.footcoder.receipt.enumclass.ErrorCode.ERR0002;
import static kr.footcoder.receipt.enumclass.ErrorCode.ERR0003;

@Slf4j
@AllArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController extends BaseController {

    private final UserService userService;
    private final UserInfoRepository userInfoRepository;
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
            return error(ERR0002);
        }

        return success();
    }

    /**
     * 로그인
     */
    @PostMapping(value = "/sign-in")
    public ModelMap login(
            @RequestBody AuthenticationRequest authenticationRequest,
            HttpSession session
    ){

        String email    = authenticationRequest.getEmail();
        String password = authenticationRequest.getPassword();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);

        try {
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            return error(ERR0003);
        }

        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                            SecurityContextHolder.getContext());

        User user = userService.readUser(email);
        checkNotNull(user, "user must not be null : " + user.getEmail());

        userInfoRepository.initUserInfo(user.getSeq(), session.getId());

        Map<String, Object> results = new ConcurrentHashMap<>();
        results.put("token", session.getId());
        results.put("email", user.getEmail());

        return success().addAttribute("results", results);

    }

    /**
     * 기존 이메일 검증 API
     */
    @PostMapping(value = "/isExist/email")
    public ModelMap isExistEamil(String email){

        User isExistUser = userService.readUser(email);

        if(isExistUser != null){
            return error(ERR0002);
        }

        return success().addAttribute("results", "사용가능한 이메일입니다.");
    }

}
