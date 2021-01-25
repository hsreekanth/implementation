package com.security.OAuth2.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.OAuth2.oauth2response.AuthenticationResponse;
import com.security.OAuth2.service.UserService;

@Controller
@RequestMapping("/sucesslogin")
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	@Autowired
	DefaultTokenServices defaultTokenServices;
	
	@Autowired
	UserService userService;
	
	 @Autowired
	 private MappingJackson2HttpMessageConverter messageConverter;
	 
	 private ObjectMapper mapper = new ObjectMapper();
	
	
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, OAuth2Authentication authentication)
			throws IOException, ServletException {
		
		Assert.notNull(authentication, "Authentication for a successfully logged in user should not be null.");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        
        UserDetails authenticatedUserDetails = (UserDetails)authentication.getPrincipal();
        
        if (authenticatedUserDetails != null) {
            //logger.info("User {} has been successfully authenticated", authenticatedUserDetails);

            //this.addAuthorities(authenticatedUserDetails);
            String token = defaultTokenServices.createAccessToken(authentication).getValue();
            String headerPrefix = defaultTokenServices.createAccessToken(authentication).getTokenType();
            //String refreshToken = tokenAuthenticationService.refreshToken(authenticatedUser,token);
            PrintWriter writer = response.getWriter();
            
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.setTokenPrefix(headerPrefix);
            authenticationResponse.setUserToken(token);
            authenticationResponse.setUserId(userService.getUserByUserName(authenticatedUserDetails.getUsername()).getId());
            clearAuthenticationAttributes(request);
            System.out.println("hello");
            mapper = messageConverter.getObjectMapper();
            mapper.writeValue(writer, authenticationResponse);

            writer.flush();
            writer.close();
        } else {
            throw new AuthenticationCredentialsNotFoundException("Authenticated User is null, which is a complete nonsense");
        }
	}
	

}
