package com.game.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;


/* Temporarily disabled to support endpoint design specification
 * Have tested and works perfect
 * */
 
@Configuration
@EnableAuthorizationServer
public class KalahAuthServer extends AuthorizationServerConfigurerAdapter{

	@Value("${oauth.clientId:clientId}")
	private String clientId;
	
	@Value("${oauth.clientSecret:clientSecret}")
	private String clientSecret;
	
	private AuthenticationManager authenticationManager;
	
	@Autowired
	KalahAuthServer(AuthenticationManager authenticationManager){
		this.authenticationManager = authenticationManager;
	}
	
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	
		clients.inMemory().
		withClient(clientId).
		secret("{noop}"+clientSecret).
		authorizedGrantTypes("client_credentials").
		scopes("resource-server-read", "resource-server-write");
	}
}
