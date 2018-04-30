package kr.footcoder.receipt.handler;


import kr.footcoder.receipt.domain.User;
import kr.footcoder.receipt.mapper.UserInfoRepository;
import kr.footcoder.receipt.util.SessionUserUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Slf4j
@Component
@AllArgsConstructor
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private final UserInfoRepository userInfoRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
	                                    HttpServletResponse response, Authentication authentication) throws IOException {

		log.error("로그인 성공 onAuthenticationSuccess");

		User user = SessionUserUtil.getUser();

		// 세션 redis 저장
		userInfoRepository.initUserInfo(user.getSeq(), request.getSession().getId());

		// 리턴 성공 정의
		response.setStatus(HttpServletResponse.SC_OK);

		// 응답
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		PrintWriter writer = response.getWriter();
		writer.print("{\"status\":\"T\"}");
		writer.flush();
		writer.close();


	}

}
