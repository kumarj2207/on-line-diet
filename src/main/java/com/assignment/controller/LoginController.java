package com.assignment.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assignment.entity.Role;
import com.assignment.entity.User;
import com.assignment.service.LoginService;

//@Controller
//@RequestMapping("/assessment")
//@CrossOrigin("http://localhost:4200")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@RequestMapping("/welcome")
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
		return "welcome";
	}


	//@PostMapping("/login")
	public ResponseEntity<Role> login(@RequestBody User user) {
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		user = loginService.getUser(user);
		return new ResponseEntity<Role>(user.getRole(), HttpStatus.OK);
	}

}
