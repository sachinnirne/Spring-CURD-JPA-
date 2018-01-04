package com.springcurdjpa.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcurdjpa.domain.User;
import com.springcurdjpa.repositories.UserRepository;
import com.springcurdjpa.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	@Override
	public List<User> findAllUsers() {
		List<User>users=null;
		users= this.userRepository.findAll();
		logger.info("users-->"+users);
		return users;
	}

	@Override
	public User findByEmailId(String emailId) {
		if(null!=emailId) {
			logger.info("find by using emailId--> "+emailId);
		return this.userRepository.findByEmailId(emailId);
		}
		return null;
	}

	@Override
	public User createUser(User userBean) {
		User saveUser=null;
		if(null!=userBean) {
			saveUser=this.userRepository.save(userBean);
		}
		return saveUser;
	}

	@Override
	public User updateUser(User userBean) {
		if(null!=userBean) {
			return this.userRepository.save(userBean);
		}
		return null;
	}

	@Override
	public User deleteUserByUserId(Long userId) {
		if(null!=userId) {
			this.userRepository.delete(userId);
		}
		return null;
	}

	@Override
	public User findUserByUserId(Long userId) {
		if (null != userId) {
			logger.info("find by using userId--> "+userId);
			return this.userRepository.findOne(userId);
		}
		return null;
	}

	@Override
	public Boolean isUserExist(String emailId) {
		if (null != this.userRepository.findByEmailId(emailId)) {
			return true;
		}
		return false;
	}

	
}
