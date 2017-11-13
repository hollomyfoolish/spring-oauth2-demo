package allen.gong.spring.oauth2.web;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import allen.gong.spring.oauth2.web.cfg.Oauth2AuthorizationServerConfig;
import allen.gong.spring.oauth2.web.cfg.SecurityConfiguration;
import allen.gong.spring.oauth2.web.cfg.WebMvcConfig;

public class MySpringWebContext<C extends ConfigurableApplicationContext> implements ApplicationContextInitializer<C>{

	@Override
	public void initialize(C applicationContext) {
		if(applicationContext instanceof AnnotationConfigWebApplicationContext){
			AnnotationConfigWebApplicationContext ac = (AnnotationConfigWebApplicationContext)applicationContext;
			ac.register(WebMvcConfig.class);
//			ac.register(SecurityConfiguration.class);
//			ac.register(Oauth2AuthorizationServerConfig.class);
		}
	}
	
}
