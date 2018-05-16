package kr.footcoder.receipt.util;

import kr.footcoder.receipt.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class SessionUserUtil {

	private SessionUserUtil(){}

	public static User getUser() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		log.debug("authentication.getName() :{}", authentication.getName());
		log.debug("authentication.getPrincipal(): {}", authentication.getPrincipal());

		if( authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getName()) ){
			return (User) authentication.getPrincipal();
		}

		return null;
	}

}
