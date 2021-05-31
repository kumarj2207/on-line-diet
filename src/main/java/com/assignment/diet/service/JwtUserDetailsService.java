package com.assignment.diet.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.assignment.diet.dao.DietUserDao;
import com.assignment.diet.entity.DietUser;
import com.assignment.diet.vo.UserDto;


@Service
//@Profile({"!test.junit"})
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private DietUserDao userDao;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		DietUser user = userDao.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				(Collection<? extends GrantedAuthority>) new ArrayList<GrantedAuthority>());
	}

	public DietUser save(UserDto user) {
		DietUser newUser = new DietUser();
		newUser.setUserName(user.getUserName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userDao.save(newUser);
	}
}