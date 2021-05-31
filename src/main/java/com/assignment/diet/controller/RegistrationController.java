package com.assignment.diet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assignment.diet.service.RegistrationService;
import com.assignment.diet.vo.RegistrationVO;

@Controller
@RequestMapping("/diet")
@CrossOrigin("http://localhost:4200")
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;

	@PostMapping("/registration")
	public ResponseEntity<String> registration(@RequestBody RegistrationVO challanger) {
		registrationService.registerChallanger(challanger);
		return new ResponseEntity<>("{\"message\": \"Thanks for registration.\"}", HttpStatus.CREATED);
	}

}
