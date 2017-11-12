package allen.gong.spring.security.demo.web.user;

import org.springframework.security.core.GrantedAuthority;

public class UserGrantedAuthority implements GrantedAuthority{
	private static final long serialVersionUID = 1L;
	// ROLE is here
	private String authority;

	public UserGrantedAuthority(String authority) {
		this.authority = authority;
	}
	
	@Override
	public String getAuthority() {
		return authority;
	}
}
