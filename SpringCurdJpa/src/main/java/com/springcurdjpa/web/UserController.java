package com.springcurdjpa.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springcurdjpa.domain.User;
import com.springcurdjpa.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * fetch all users
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		System.out.println("fetch all users--->");
		List<User> users = this.userService.findAllUsers();
		if (null == users || users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("id") Long userId) {
		System.out.println("fetch single user--->");
		User user = this.userService.findUserByUserId(userId);
		if (null == user) {
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	/**
	 * create new user
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody User user) {
		System.out.println("Creating User " + user.getFirstName());
		if (userService.isUserExist(user.getEmailId())) {
			System.out.println("A User with name " + user.getFirstName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		this.userService.createUser(user);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	/**
	 * update existing user
	 * @param user
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@RequestBody User user) {

		User currentUser = this.userService.findUserByUserId(user.getUserId());

		if (currentUser == null) {
			System.out.println("User with id " + user.getUserId() + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		currentUser = this.userService.updateUser(user);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	/**
	 * Delete user
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") Long userId) {
		System.out.println("Fetching & Deleting User with id " + userId);

		User user = this.userService.findUserByUserId(userId);
		if (user == null) {
			System.out.println("Unable to delete. User with id " + userId + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		this.userService.deleteUserByUserId(userId);
		return new ResponseEntity<User>(HttpStatus.OK);
	}

}
