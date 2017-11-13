package allen.gong.spring.security.demo.web.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

public class UserSecurityContext implements SecurityContext {

	private static final long serialVersionUID = 1L;
	private Authentication authentication;

	@Override
	public Authentication getAuthentication() {
		return this.authentication;
	}

	@Override
	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

}
