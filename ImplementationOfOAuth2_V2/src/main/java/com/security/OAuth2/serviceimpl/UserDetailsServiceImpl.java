package com.security.OAuth2.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.OAuth2.model.CustomUserDetails;
import com.security.OAuth2.model.User;
import com.security.OAuth2.repo.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		Optional<User> optionalUser = userRepository.findByUsername(username);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			
			  user.setAccountNonExpired(true);
			  user.setAccountNonLocked(true);
			  user.setCredentialsNonExpired(true);
			 
			optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username or password wrong"));
			CustomUserDetails customUserDetails = new CustomUserDetails(user);
			/*customUserDetails.setUser(user.getUserName());
			customUserDetails.setPassword(user.getPassword());*/
			
			new AccountStatusUserDetailsChecker().check(customUserDetails);
			return customUserDetails;
		}
		throw new UsernameNotFoundException(username);
	}
	
	

}
