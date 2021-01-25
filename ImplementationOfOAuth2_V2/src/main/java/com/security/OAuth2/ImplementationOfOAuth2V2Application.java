package com.security.OAuth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
public class ImplementationOfOAuth2V2Application {

	public static void main(String[] args) {
		SpringApplication.run(ImplementationOfOAuth2V2Application.class, args);
	}

}
