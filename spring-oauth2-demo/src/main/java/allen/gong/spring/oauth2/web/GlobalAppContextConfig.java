package allen.gong.spring.oauth2.web;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60)
public class GlobalAppContextConfig {

	@Bean
	public JedisConnectionFactory connectionFactory() {
		JedisConnectionFactory redisFactory = new JedisConnectionFactory();
		redisFactory.setHostName("10.58.5.74");
		redisFactory.setPort(6379);
		redisFactory.setDatabase(0);
		return redisFactory;
	}
	
	@Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
    }
	
	@Bean
	public DataSource dataSource() {
		return new DriverManagerDataSource("jdbc:sap://10.58.5.74:30015?currentschema=NLP_CLOUD_AUTH", "SYSTEM", "manager");
	}
	
//	@Bean
//	public List<HttpSessionListener> listeners(){
//		List<HttpSessionListener> liseners = new ArrayList<HttpSessionListener>();
//		liseners.add(new HttpSessionListener(){
//			@Override
//			public void sessionCreated(HttpSessionEvent se) {
//				System.out.println("***************session created****************" + se.getSession().getId());
//			}
//
//			@Override
//			public void sessionDestroyed(HttpSessionEvent se) {
//				System.out.println("***************session destroyed****************" + se.getSession().getId());
//			}
//		});
//		
//		return liseners;
//	}
	
}
