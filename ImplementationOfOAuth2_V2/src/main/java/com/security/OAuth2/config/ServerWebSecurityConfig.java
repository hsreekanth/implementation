package com.security.OAuth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.security.OAuth2.controller.LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class ServerWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public AuthenticationManager getAuthenticationManager() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder userPasswordEncoder() {
		// return new BCryptPasswordEncoder(10);
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(userPasswordEncoder());
	}

	@Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {

		LoginSuccessHandler loginSuccessHandler = new LoginSuccessHandler();
		loginSuccessHandler.setTargetUrlParameter("/succeslogin");
		return loginSuccessHandler;
	}

	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * 
	 * http .authorizeRequests() .antMatchers("/images/**", "/css/**",
	 * "/js/**").permitAll() .and() .authorizeRequests()
	 * .antMatchers("/course/add").hasRole("ADMIN")
	 * .antMatchers("/course/show-all").hasAnyRole("ADMIN", "USER")
	 * .antMatchers("/course/edit").hasAnyRole("USER") .anyRequest().authenticated()
	 * .and() .formLogin() .loginPage("/login").loginProcessingUrl("/oauth/token")
	 * .permitAll() .successHandler(myAuthenticationSuccessHandler())
	 * .defaultSuccessUrl("/");
	 * 
	 * 
	 * http.csrf().disable() .authorizeRequests()
	 * .antMatchers("/register").permitAll() .antMatchers("/login2").permitAll()
	 * .anyRequest().authenticated() .and() .formLogin().loginPage("/login")
	 * .loginProcessingUrl("/oauth/token").permitAll()
	 * .successHandler(myAuthenticationSuccessHandler()).successForwardUrl(
	 * "/succeslogin") //.defaultSuccessUrl("")
	 * 
	 * .and()
	 * 
	 * .logout().invalidateHttpSession(true) .clearAuthentication(true)
	 * .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	 * .logoutSuccessUrl("/login").permitAll(); }
	 */

}
