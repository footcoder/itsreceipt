package kr.footcoder.receipt.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Configuration
@Order(1)
public class TokenFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.error("토큰 검증 custom filter");
        //1.token 유효 기간 검증

        // 만료된 token일경우 에러 리턴

        //2. 정상적인 token일경우 유저 조회후 spring security UsernamePasswordAuthenticationFilter 세션



        chain.doFilter(request, response);
    }

}
