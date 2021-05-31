package com.assignment.diet.controller;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assignment.diet.response.vo.DailyLogResponseVO;
import com.assignment.diet.response.vo.DietUserResponseVO;
import com.assignment.diet.response.vo.MeasurementResponseVO;
import com.assignment.diet.response.vo.RegistrationResponseVO;
import com.assignment.diet.service.AdminService;
import com.assignment.diet.service.MeasurementService;
import com.assignment.diet.vo.Approval;
import com.assignment.diet.vo.MessageVO;
import com.assignment.diet.vo.MotivatorVO;

@Controller
@RequestMapping("/diet")
@CrossOrigin("http://localhost:4200")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private MeasurementService measurementService;

	@GetMapping("/newregistrations")
	public ResponseEntity<RegistrationResponseVO> getNewRegistrations() {
		return new ResponseEntity<RegistrationResponseVO>(adminService.getNewRegistrations(), HttpStatus.OK);
	}

	@PostMapping("/approval")
	public ResponseEntity<Void> approve(@RequestBody Approval approval) {
		adminService.approve(approval);  
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/postmessage")
	public ResponseEntity<Void> postMessage(@RequestBody MessageVO messageVO) {
		adminService.postMessage(messageVO);  
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/viewmeasurement/{id}/{month}")
	public ResponseEntity<List<MeasurementResponseVO>> viewMeasurement(@PathVariable("id") Long referralId, @PathVariable("month") Integer month) {
		List<MeasurementResponseVO> list = measurementService.getMeasurements(referralId, month);  
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/viewdailylog/{id}/{month}")
	public ResponseEntity<List<DailyLogResponseVO>> viewDailyLog(@PathVariable("id") Long referralId, @PathVariable("month") Integer month) {

		List<DailyLogResponseVO> list = measurementService.getDailyLog(referralId, month);  
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/dietusers")
	public ResponseEntity<List<DietUserResponseVO>> getAllDietUsers() {
		List<DietUserResponseVO> list = adminService.getAllDietUsers();  
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/dietuser/{id}")
	public ResponseEntity<DietUserResponseVO> getDietUser(@PathVariable("id") Long referralId) {
		DietUserResponseVO du = adminService.getDietUser(referralId);  
		return ResponseEntity.ok(du);
	}
	
	@DeleteMapping("/deletedietuser/{id}")
	public ResponseEntity<Void> deleteDietUser(@PathVariable("id") Long referralId) {
		adminService.deleteDietUser(referralId);  
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/updatedietuser/{id}")
	public ResponseEntity<Void> updateDietUser(@RequestBody MotivatorVO body, 
			@PathVariable("id") Long referralId) throws JSONException {
		adminService.updateDietUser(referralId, body) ;  
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/addmotivator")
	public ResponseEntity<Void> addMotivator(@RequestBody MotivatorVO motivatorVO) {
		adminService.addMotivator(motivatorVO);  
		return ResponseEntity.noContent().build();
	}

}
