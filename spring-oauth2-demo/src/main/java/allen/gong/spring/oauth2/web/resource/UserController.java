package allen.gong.spring.oauth2.web.resource;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import allen.gong.spring.oauth2.common.NlpUser;

@RequestMapping("/me")
@Controller
public class UserController {
	
	@ResponseBody
	@RequestMapping("")
	public NlpUser getPhotoServiceUser(Principal principal){
		return new NlpUser(principal.getName());
	}
}
