package allen.gong.spring.oauth2.web.cfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@ComponentScan(basePackageClasses = Oauth2ServerConfig.class)
public class Oauth2ServerConfig {

	@Bean
	public TokenStore tokenStore(){
		return new InMemoryTokenStore();
	}
	
}
