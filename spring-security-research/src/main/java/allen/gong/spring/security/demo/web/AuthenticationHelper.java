package allen.gong.spring.security.demo.web;

import org.springframework.security.core.Authentication;

import allen.gong.spring.security.demo.web.user.UserAuthentication;

public class AuthenticationHelper {

	public static Authentication createAuthentication() {
		Authentication userAuthentication = new UserAuthentication();
		return userAuthentication;
	}

}
