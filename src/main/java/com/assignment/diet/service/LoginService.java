package com.assignment.diet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.assignment.diet.dao.LoginDao;
import com.assignment.diet.entity.DietUser;
import com.assignment.diet.vo.UserDto;


@Service
public class LoginService {

	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public DietUser getUser(UserDto user) {
		return loginDao.findByUserName(user.getUserName());
	}

	public void changePassword(Long referralId, String oldPassword, String confirmNewPassword) {
		System.out.println(referralId);
		System.out.println(oldPassword);
		System.out.println(confirmNewPassword);
		System.out.println(passwordEncoder.encode(oldPassword));
		
//		DietUser user = loginDao.findByReferralIdAndPassword(referralId, bcryptEncoder.encode(oldPassword));
		DietUser user = loginDao.findById(referralId).get();
		System.out.println(user.getPassword());
		
		
		
		if (passwordEncoder.matches(oldPassword, user.getPassword())) {
			user.setPassword(passwordEncoder.encode(confirmNewPassword));
			loginDao.save(user);
		} else {
		    throw new RuntimeException("Password does not match"); 
		}
		
		
	}



}
