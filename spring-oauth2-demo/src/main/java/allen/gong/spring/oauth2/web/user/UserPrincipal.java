package allen.gong.spring.oauth2.web.user;

import java.io.Serializable;
import java.security.Principal;

public class UserPrincipal implements Principal, Serializable {
	private static final long serialVersionUID = 1L;
	private String userId;

	public UserPrincipal(String userId){
		this.userId = userId;
	}
	
	
	@Override
	public String getName() {
		return userId;
	}

}
