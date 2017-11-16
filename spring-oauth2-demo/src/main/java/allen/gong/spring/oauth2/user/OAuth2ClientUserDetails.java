package allen.gong.spring.oauth2.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import allen.gong.spring.oauth2.web.AuthenticationHelper;

public class OAuth2ClientUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
	private String userName;
	private Collection<? extends GrantedAuthority> authorities;

	public OAuth2ClientUserDetails(String userName){
		this.userName = userName;
		this.authorities = AuthenticationHelper.createAuthentication(userName).getAuthorities();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
