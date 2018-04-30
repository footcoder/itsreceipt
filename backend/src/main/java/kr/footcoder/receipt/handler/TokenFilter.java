package kr.footcoder.receipt.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Slf4j
@Component
@Order(1)
public class TokenFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.error("토큰 검증 custom filter");
/*

        HttpServletRequest httpRequest = this.getAsHttpRequest(request);
        String token = httpRequest.getHeader("token");

        if(token != null){
            //



            chain.doFilter(request, response);
        } else {
            logger.debug("token is null ========= ");
            logger.debug(httpRequest.getContextPath());
            chain.doFilter(request, response);

        }



        chain.doFilter(request, response);
        */
        //1.token 유효 기간 검증

        // 만료된 token일경우 에러 리턴

        //2. 정상적인 token일경우 유저 조회후 spring security UsernamePasswordAuthenticationFilter 세션
        chain.doFilter(request, response);
    }

    private HttpServletRequest getAsHttpRequest(ServletRequest request) {
        if (!(request instanceof HttpServletRequest)) {
            throw new RuntimeException("Expecting an HTTP request");
        }

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        try {
            httpRequest.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }

        return httpRequest;
    }
}
