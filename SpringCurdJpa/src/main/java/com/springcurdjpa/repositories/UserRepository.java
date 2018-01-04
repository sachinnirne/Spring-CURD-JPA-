package com.springcurdjpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springcurdjpa.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByEmailId(String emailId);
}
