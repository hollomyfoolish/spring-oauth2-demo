package allen.gong.spring.oauth2.web.resource;

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
@RequestMapping(value = "/public")
public class PublicResourceController {
	
	@ResponseBody
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public HttpEntity<String> info(HttpServletRequest request, HttpServletResponse response){
		return ResponseEntity
			.status(HttpStatus.OK)
			.header("Content-Type", "text/html; charset=utf-8")
			.body("This is public information");
	}
	
	@ResponseBody
	@RequestMapping(value = "/info/detail", method = RequestMethod.GET)
	public HttpEntity<String> detail(HttpServletRequest request, HttpServletResponse response){
		return ResponseEntity
			.status(HttpStatus.OK)
			.header("Content-Type", "text/html; charset=utf-8")
			.body("This is detail information");
	}
	
}
