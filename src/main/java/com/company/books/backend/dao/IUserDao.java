package com.company.books.backend.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.company.books.backend.model.User;

public interface IUserDao extends CrudRepository<User, Long>{

	public User findByUserName(String userName);
	@Query("SELECT u from User u where u.userName = ?1")
	public User findByUserId();
	
}
