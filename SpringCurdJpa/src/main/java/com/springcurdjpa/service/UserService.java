package com.springcurdjpa.service;

import java.util.List;

import com.springcurdjpa.domain.User;

public interface UserService {
	
	public List<User> findAllUsers();
	
	public User findUserByUserId(Long userId);
	
	public User findByEmailId(String emailId);
	
	public Boolean isUserExist(String emailId);
	
	public User createUser(User userBean );
	
	public User updateUser(User userBean);
	
	public User deleteUserByUserId(Long userId);
}
