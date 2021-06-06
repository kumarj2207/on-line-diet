package com.assignment.diet.controller;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assignment.diet.entity.DietUser;
import com.assignment.diet.response.vo.ReturnedUser;
import com.assignment.diet.service.LoginService;
import com.assignment.diet.vo.JwtRequest;
import com.assignment.diet.vo.UserDto;

@Controller
@RequestMapping("/diet")
@CrossOrigin("http://online-diet-app.herokuapp.com")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private JwtAuthenticationController jwtAuthenticationController;

	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@RequestMapping("/welcome")
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
		return "welcome";
	}

	@PostMapping("/login")
	public ResponseEntity<ReturnedUser> login(@RequestBody UserDto user) throws Exception {
		DietUser duser = loginService.getUser(user);
		if (duser != null) {
			JwtRequest authenticationRequest = new JwtRequest();
			authenticationRequest.setUsername(user.getUserName());
			authenticationRequest.setPassword(user.getPassword());
			String token = null;
			if (jwtAuthenticationController != null) {
				token = jwtAuthenticationController.createAuthenticationToken(authenticationRequest).getBody()
						.getToken();
			}
			return ResponseEntity
					.ok(new ReturnedUser(duser.getReferralId(), duser.getFullName(), duser.getRole(), token));
		}
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/changepassword/{referralId}")
	public ResponseEntity<String> changePassword(@RequestBody String body, 
			@PathVariable("referralId") Long referralId) throws Exception {
		JSONObject jsonObject = new JSONObject(body);
		String oldPassword = jsonObject.getString("oldPassword");
		//String newPassword = jsonObject.getString("newPassword");
		String confirmNewPassword = jsonObject.getString("confirmNewPassword");
		
		loginService.changePassword(referralId, oldPassword, confirmNewPassword);
		
		return ResponseEntity.ok("{\"message\": \"Password changed.\"}");
	}

}
