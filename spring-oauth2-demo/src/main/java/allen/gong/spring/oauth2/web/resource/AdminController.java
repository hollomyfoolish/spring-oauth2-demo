package allen.gong.spring.oauth2.web.resource;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@ResponseBody
	@RequestMapping(value = "/echo", method = RequestMethod.GET)
	public HttpEntity<String> echo(HttpServletRequest request, HttpServletResponse response, Principal principal){
		return ResponseEntity
			.status(HttpStatus.OK)
			.header("Content-Type", "text/html; charset=utf-8")
			.body("hello " + principal.getName());
	}
}
