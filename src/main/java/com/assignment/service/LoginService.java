package com.assignment.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.assignment.diet.dao.LoginDao;
import com.assignment.entity.User;

//@Service
public class LoginService {

	@Autowired
	private LoginDao loginDao;
	
	public User getUser(User user) {
		return null;//loginDao.findByEmail(user.getEmail());
	}

}
