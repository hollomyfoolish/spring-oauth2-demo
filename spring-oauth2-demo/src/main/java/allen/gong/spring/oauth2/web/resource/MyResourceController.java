package allen.gong.spring.oauth2.web.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/myrs")
public class MyResourceController {
	
	@ResponseBody
	@RequestMapping(value = "/authorize", method = RequestMethod.GET)
	public HttpEntity<String> authorize(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession(true);
		List<String> list = (List<String>) session.getAttribute("list");
		if(list == null){
			list = new ArrayList<String>();
			session.setAttribute("list", list);
		}
		list.add(String.valueOf(System.currentTimeMillis()));
		session.setAttribute("list", list);
		return ResponseEntity
			.status(HttpStatus.OK)
			.header("Content-Type", "text/html; charset=utf-8")
			.body("your id: " + request.getSession(true).getId());
	}
	
	@ResponseBody
	@RequestMapping(value = "/sessions", method = RequestMethod.GET)
	public HttpEntity<String> sessions(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession(false);
		return ResponseEntity
			.status(HttpStatus.OK)
			.header("Content-Type", "text/html; charset=utf-8")
			.body("your session: " + (session == null?"null" : session.getAttribute("list").toString()));
	}
	
	@ResponseBody
	@RequestMapping(value = "/invalidate", method = RequestMethod.GET)
	public HttpEntity<String> invalidate(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession(false);
		if(session != null){
			session.invalidate();
		}
		return ResponseEntity
			.status(HttpStatus.OK)
			.header("Content-Type", "text/html; charset=utf-8")
			.body("OK");
	}
}
