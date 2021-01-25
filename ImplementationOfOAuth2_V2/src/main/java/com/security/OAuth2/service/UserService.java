package com.security.OAuth2.service;

import com.security.OAuth2.model.User;

public interface UserService {

	public User addUser(User user);
	
	public User editUser(User user);
	
	public int deleteUser(User user);
	
	public User searchUser();
	
	public User getUserByUserName(String name);
}
