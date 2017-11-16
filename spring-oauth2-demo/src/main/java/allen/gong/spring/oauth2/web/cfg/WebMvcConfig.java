package allen.gong.spring.oauth2.web.cfg;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import allen.gong.spring.oauth2.web.resource.AdminController;
import allen.gong.spring.oauth2.web.resource.MyResourceController;
import allen.gong.spring.oauth2.web.resource.PublicResourceController;
import allen.gong.spring.oauth2.web.resource.UserController;

@Configurable
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter implements InitializingBean{

	@Autowired
	private DataSource dataSource;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
//	@Bean
//	public ContentNegotiatingViewResolver contentViewResolver() throws Exception {
//		ContentNegotiationManagerFactoryBean contentNegotiationManager = new ContentNegotiationManagerFactoryBean();
//		contentNegotiationManager.addMediaType("json", MediaType.APPLICATION_JSON);
//
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setPrefix("/WEB-INF/jsp/");
//		viewResolver.setSuffix(".jsp");
//
//		MappingJackson2JsonView defaultView = new MappingJackson2JsonView();
//		defaultView.setExtractValueFromSingleKeyModel(true);
//
//		ContentNegotiatingViewResolver contentViewResolver = new ContentNegotiatingViewResolver();
//		contentViewResolver.setContentNegotiationManager(contentNegotiationManager.getObject());
//		contentViewResolver.setViewResolvers(Arrays.<ViewResolver> asList(viewResolver));
//		contentViewResolver.setDefaultViews(Arrays.<View> asList(defaultView));
//		return contentViewResolver;
//	}

	@Bean
	public MyResourceController myResourceController() {
		return new MyResourceController();
	}
	
	// N.B. the @Qualifier here should not be necessary (gh-298) but lots of users report needing it.
	@Bean
	public AdminController adminController() {
		AdminController adminController = new AdminController();
		return adminController;
	}

	@Bean
	public PublicResourceController publicResourceController(){
		return new PublicResourceController();
	}
	
	@Bean
	public UserController userController(){
		return new UserController();
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(this.dataSource);
	}
}
