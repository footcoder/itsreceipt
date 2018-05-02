package kr.footcoder.receipt.handler;

import kr.footcoder.receipt.domain.User;
import kr.footcoder.receipt.mapper.UserInfoRepository;
import kr.footcoder.receipt.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Slf4j
@Component
@Order(1)
@AllArgsConstructor
public class TokenFilter extends GenericFilterBean {

	private final UserService userServiceImpl;
	private final UserInfoRepository userInfoRepository;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.error("토큰 검증 custom filter");

		HttpServletRequest httpRequest = this.getAsHttpRequest(request);


		String token = httpRequest.getHeader("token");

		if (token != null) {
			log.error("token : {}", token);

			String email = userInfoRepository.getEmailByUser(token);
			//token 값 키로 유저 조회
			User user = (User) userServiceImpl.loadUserByUsername(email);

			if (user != null) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
				SecurityContextHolder.getContext().setAuthentication(authentication);

			}
		} else {
			logger.debug("token is null ========= ");
			logger.debug(httpRequest.getContextPath());
			//todo.에러코드 정의 필요

		}

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
			throw new ClassCastException("Expecting an HTTP request");
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
