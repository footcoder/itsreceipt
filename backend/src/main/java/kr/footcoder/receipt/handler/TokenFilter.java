package kr.footcoder.receipt.handler;

import kr.footcoder.receipt.domain.User;
import kr.footcoder.receipt.enumclass.ErrorCode;
import kr.footcoder.receipt.exceptions.LogicException;
import kr.footcoder.receipt.mapper.UserInfoRepository;
import kr.footcoder.receipt.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import static kr.footcoder.receipt.util.JsonResultUtil.getFailJson;

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

			//1. 레디스에 token 존재여부 확인
			String email = userInfoRepository.getEmailByUser(token);

			if(StringUtils.isEmpty(email)){
				printError(response, ErrorCode.ERR0004);
			} else {
				//token 값 키로 유저 조회
				User user = (User) userServiceImpl.loadUserByUsername(email);

				if (user != null) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
					SecurityContextHolder.getContext().setAuthentication(authentication);

					//레디스 만료시간 갱신
					userInfoRepository.refreshSessionOfUser(token);
					chain.doFilter(request, response);
				} else {
					printError(response, ErrorCode.ERR0003);
				}
			}


		} else {
			logger.debug("token is null ========= ");
			logger.debug(httpRequest.getContextPath());
			chain.doFilter(request, response);
		}

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


	private void printError(ServletResponse response, ErrorCode errorCode) {
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", "F");

			JSONObject error = new JSONObject();
			error.put("error", errorCode.name());
			error.put("errorCode", errorCode.getErrorMessage());

			jsonObject.put("results", error);

			PrintWriter out = response.getWriter();
			out.print(jsonObject.toString());

			logger.info(jsonObject.toString());

			out.flush();
			out.close();
		} catch(JSONException | IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
