package allen.gong.spring.oauth2.web.user;

import java.security.Principal;
import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UserAuthentication extends AbstractAuthenticationToken {
	private static final long serialVersionUID = 1L;
	private Principal principal;

	public UserAuthentication(Collection<? extends GrantedAuthority> authorities, Principal principal) {
		super(authorities);
		this.principal = principal;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}
}
