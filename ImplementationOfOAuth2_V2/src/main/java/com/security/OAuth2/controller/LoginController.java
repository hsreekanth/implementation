package com.security.OAuth2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

	
	@RequestMapping(value="/login")
	public ModelAndView login() {
		
		return new ModelAndView("login",HttpStatus.OK);
	}
}
