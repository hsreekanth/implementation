package com.security.OAuth2.serviceimpl;

import java.util.Optional;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.security.OAuth2.model.User;
import com.security.OAuth2.repo.UserRepository;
import com.security.OAuth2.service.UserService;



@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
	@Resource
	private UserRepository userRepo;
	
	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User editUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User searchUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUserName(String name) {
		
		logger.info("*************FETCHING USER FROM DB***************");
		Optional<User> optionalUser = userRepo.findByUsername(name);
		if(optionalUser.isPresent()) {
			
			logger.info("*************USER FOUND FROM DB***************");
			return optionalUser.get();
		}
		
		return null;
	}
	
	

}
