package allen.gong.spring.security.demo.web.filter;

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

import allen.gong.spring.security.demo.web.AuthenticationHelper;
import allen.gong.spring.security.demo.web.user.UserSecurityContext;

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
		if(!login(httpSession)){
			doLogin(httpSession);
		}
		chain.doFilter(request, response);
	}

	private void doLogin(HttpSession httpSession) {
		UserSecurityContext sc = new UserSecurityContext();
		sc.setAuthentication(AuthenticationHelper.createAuthentication());
		httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
	}

	private boolean login(HttpSession httpSession) {
		return httpSession.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY) != null;
	}

	@Override
	public void destroy() {
		
	}

}
