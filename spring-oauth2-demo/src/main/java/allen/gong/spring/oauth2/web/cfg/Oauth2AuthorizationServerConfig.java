package allen.gong.spring.oauth2.web.cfg;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.InvalidScopeException;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.OAuth2RequestValidator;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.approval.ApprovalStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import allen.gong.spring.oauth2.common.OAuthUtils;
import allen.gong.spring.oauth2.user.DummyUserDetailsService;

@Configurable
@EnableAuthorizationServer
public class Oauth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}
	
	@Bean
	protected AuthorizationCodeServices authorizationCodeServices() {
		JdbcAuthorizationCodeServices codeService = new JdbcAuthorizationCodeServices(dataSource);
		return codeService;
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//		security.passwordEncoder(passwordEncoder);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient(OAuthUtils.B1_CLIEN_ID)
			.authorizedGrantTypes(OAuthUtils.OAUTH_GRANTYPE_CODE, "refresh_token")
			.authorities(OAuthUtils.B1_OAUTH_ROLE)
			.secret(OAuthUtils.B1_CLIENT_SECRET)
			.accessTokenValiditySeconds(24 * 3600)
			.refreshTokenValiditySeconds(30 * 24 * 3600);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		ApprovalStoreUserApprovalHandler userApprovalHandler = new ApprovalStoreUserApprovalHandler();
//		OAuth2AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
		endpoints
			.userDetailsService(new DummyUserDetailsService())
			.reuseRefreshTokens(false)
			.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
			.authorizationCodeServices(authorizationCodeServices())
//			.authenticationManager(authenticationManager)
//			.userApprovalHandler(userApprovalHandler)
			.tokenStore(tokenStore())
			.approvalStoreDisabled()
			.requestValidator(new OAuth2RequestValidator(){
				@Override
				public void validateScope(AuthorizationRequest authorizationRequest, ClientDetails client) throws InvalidScopeException {
				}

				@Override
				public void validateScope(TokenRequest tokenRequest, ClientDetails client) throws InvalidScopeException {
				}});
	}
	
}
