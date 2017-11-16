package allen.gong.spring.oauth2.web;

import java.util.EnumSet;
import java.util.concurrent.ScheduledExecutorService;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import allen.gong.spring.oauth2.web.cfg.Oauth2AuthorizationServerConfig;
import allen.gong.spring.oauth2.web.cfg.Oauth2ResourceServerConfig;
import allen.gong.spring.oauth2.web.cfg.SecurityConfiguration;
import allen.gong.spring.oauth2.web.filter.FakeLoginFilter;
import allen.gong.spring.oauth2.web.listener.SessionListener;

public class MyApplicationInitializer implements WebApplicationInitializer {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyApplicationInitializer.class);

	@Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        addServiceFilter(servletContext);
        configSessionListener(servletContext);
        initSpringContext(servletContext);
        configServletContext(servletContext);
        addServletHandlers(servletContext);
    }

	private void addServletHandlers(ServletContext servletContext) {
		addSpringDispatcher(servletContext);
	}

	private void addServiceFilter(final ServletContext servletContext) {
		addSpringSessionRepositoryFilter(servletContext);
		addFakeLoginFilter(servletContext);
		addSpringSecurityFilter(servletContext);
		addCharacterEncodingFilter(servletContext);
	}

	private void addFakeLoginFilter(ServletContext servletContext) {
		javax.servlet.FilterRegistration.Dynamic filter = servletContext.addFilter("FakeLoginFilter", new FakeLoginFilter());
        if (filter != null) {
            filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), false, "/*");
        }
	}

	public void addSpringSecurityFilter(final ServletContext servletContext) {
		DelegatingFilterProxy filter = new DelegatingFilterProxy("springSecurityFilterChain");
//		filter.setContextAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.dispatcher");
		servletContext.addFilter("springSecurityFilterChain", filter).addMappingForUrlPatterns(null, false, "/*");
	}

    private void addSpringSessionRepositoryFilter(ServletContext servletContext) {
    	DelegatingFilterProxy filter = new DelegatingFilterProxy("springSessionRepositoryFilter");
		servletContext.addFilter("springSessionRepositoryFilter", filter).addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), false, "/*");
	}

	private void addCharacterEncodingFilter(final ServletContext servletContext) {
        final CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        final javax.servlet.FilterRegistration.Dynamic filter = servletContext.addFilter("CharacterEncodingFilter",
                characterEncodingFilter);
        if (filter != null) {
            filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), true, "/*");
        }
    }

    protected void configSessionListener(ServletContext servletContext) {
        servletContext.addListener(SessionListener.class);
    }

    protected void initSpringContext(ServletContext servletContext) {
        addSpringContextLoaderListener(servletContext);
    }

    protected void configAppContext(AnnotationConfigWebApplicationContext appContext) {
    	appContext.setDisplayName("nlpauth");
    	appContext.register(GlobalAppContextConfig.class);
    	appContext.register(SecurityConfiguration.class);
    	appContext.register(Oauth2AuthorizationServerConfig.class);
    	appContext.register(Oauth2ResourceServerConfig.class);
    }

    protected void configServletContext(ServletContext servletContext) {
    	configSession(servletContext);
    	setContextValue(servletContext);
    }

    private void configSession(ServletContext servletContext) {
		servletContext.getSessionCookieConfig().setSecure(true);
	}
    
	private void addSpringDispatcher(ServletContext servletContext) {
		Dynamic springDispatcher = servletContext.addServlet("SpringDispatcherServlet", DispatcherServlet.class);
		springDispatcher.addMapping("/");
		springDispatcher.setInitParameter("contextClass", "org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
		springDispatcher.setInitParameter("contextInitializerClasses", "allen.gong.spring.oauth2.web.MySpringWebContext");
		springDispatcher.setLoadOnStartup(1); 
	}

	private void setContextValue(ServletContext servletContext) {
		servletContext.setInitParameter("spring.profiles.default", "dev");
		servletContext.setInitParameter("webAppRootKey", "nlpauth-service");
	}

    private void addSpringContextLoaderListener(final ServletContext servletContext) {
        servletContext.addListener(new ContextLoaderListener() {
            @Override
            protected WebApplicationContext createWebApplicationContext(ServletContext sc) {
                final AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
                registerApplicationContextListeners(appContext);
                configAppContext(appContext);
                appContext.registerShutdownHook();
                return appContext;
            }
            
            @Override
            public void contextDestroyed(ServletContextEvent sce){
            	try{
        			WebApplicationContext webAppContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        			ScheduledExecutorService executorService = webAppContext.getBean(ScheduledExecutorService.class);
        			if(executorService == null){
        				LOGGER.info("there is no ScheduledExecutorService in WebApplicationContext");
        			}
        			executorService.shutdownNow();
//        			webAppContext.getBean(requiredType)
        		}catch(Throwable e){
        			LOGGER.error("fail to get WebApplicationContext", e);
        		}
            	super.contextDestroyed(sce);
            }
        });
    }

    private void registerApplicationContextListeners(final AnnotationConfigWebApplicationContext appContext) {
        appContext.addApplicationListener(new SessionListener());
//        appContext.addApplicationListener(new ScheduledJobStartUpListener());
    }

    protected String getComponentName() {
        return "oauth2demo";
    }

}
