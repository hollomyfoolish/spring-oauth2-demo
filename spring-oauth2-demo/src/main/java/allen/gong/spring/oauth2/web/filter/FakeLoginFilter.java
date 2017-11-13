package allen.gong.spring.oauth2.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import allen.gong.spring.oauth2.web.AuthenticationHelper;
import allen.gong.spring.oauth2.web.user.UserSecurityContext;

public class FakeLoginFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(FakeLoginFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		logger.info("in fake login filter");
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpSession httpSession = httpRequest.getSession(true);
		String user = httpRequest.getParameter("user");
		if(user != null){
			doLogin(httpSession, user);
		}
		chain.doFilter(request, response);
	}

	private void doLogin(HttpSession httpSession, String user) {
		UserSecurityContext sc = new UserSecurityContext();
		sc.setAuthentication(AuthenticationHelper.createAuthentication(user));
		httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
	}

//	private boolean login(HttpSession httpSession) {
//		return httpSession.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY) != null;
//	}

	@Override
	public void destroy() {
		
	}

}
