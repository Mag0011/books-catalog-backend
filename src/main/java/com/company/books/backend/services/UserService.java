package com.company.books.backend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.books.backend.dao.IUserDao;
import com.company.books.backend.model.User;

@Service
public class UserService implements UserDetailsService{

	Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private IUserDao userDao;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUserName(username);
		if(user == null) {
			log.error("User, cannot be found");
			throw new UsernameNotFoundException("User, cannot be found");
		}
		List<GrantedAuthority> authorities = user.getRoles().
				stream().
				map(role -> new SimpleGrantedAuthority(role.getRole())).
				peek(authority -> log.info(authority.getAuthority())).
				collect(Collectors.toList());
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
	}

	
	
}
