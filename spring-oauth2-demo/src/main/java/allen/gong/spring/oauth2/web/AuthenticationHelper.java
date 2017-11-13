package allen.gong.spring.oauth2.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import allen.gong.spring.oauth2.web.user.UserAuthentication;
import allen.gong.spring.oauth2.web.user.UserGrantedAuthority;
import allen.gong.spring.oauth2.web.user.UserPrincipal;

public class AuthenticationHelper {

	public static Authentication createAuthentication(String name) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if("admin".equals(name)){
			authorities.add(new UserGrantedAuthority("ROLE_ADMIN"));
		}else{
			authorities.add(new UserGrantedAuthority("ROLE_USER"));
		}
		UserPrincipal principal = new UserPrincipal(name);
		UserAuthentication userAuthentication = new UserAuthentication(authorities, principal);
		userAuthentication.setAuthenticated(true);
		return userAuthentication;
	}

}
