package allen.gong.spring.security.demo.web.user;

import java.io.Serializable;
import java.security.Principal;

public class UserPrincipal implements Principal, Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "Allen";
	}

}
