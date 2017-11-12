package allen.gong.spring.security.demo.web;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.FilterRegistration.Dynamic;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import allen.gong.spring.security.demo.web.filter.FakeLoginFilter;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer{

	public SecurityWebApplicationInitializer() {
        super(SecurityConfig.class);
    }
	
	@Override
	public void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		Filter fakeLoginFilter = new FakeLoginFilter();
		Dynamic registration = servletContext.addFilter("FakeLoginFilter", fakeLoginFilter);
		EnumSet<DispatcherType> dispatcherTypes = getSecurityDispatcherTypes();
		registration.addMappingForUrlPatterns(dispatcherTypes, false, "/*");
	}
	
	@Override
	public void afterSpringSecurityFilterChain(ServletContext servletContext) {
		
	}
	
}
