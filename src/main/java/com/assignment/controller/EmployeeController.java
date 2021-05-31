package com.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.entity.Credit;
import com.assignment.entity.Employee;
import com.assignment.exceptions.EmployeeNotFound;
import com.assignment.service.EmployeeService;
import com.assignment.vo.EmployeeRequest;

@RestController
@RequestMapping("/employee")
@CrossOrigin("http://localhost:4200")
public class EmployeeController {

	@Autowired
	private EmployeeService emloyeeService;

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> list = null;
		list = emloyeeService.getAllEmployees();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") Integer id) {
		Employee e = emloyeeService.getEmployee(id);
		return new ResponseEntity<>(e, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeRequest employee) {
		Employee newEmployee = new Employee();
		newEmployee.setName(employee.getName());
		newEmployee.setLocation(employee.getLocation());
		newEmployee.setEmail(employee.getEmail());
		newEmployee.setMobile(employee.getMobile());
				
		emloyeeService.addEmployee(newEmployee);
		return new ResponseEntity<>(newEmployee, HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, 
			@PathVariable("id") Integer id) {
		Employee e = null;
		try {
			e = emloyeeService.updateEmployee(id, employee);
		} catch (EmployeeNotFound ex) {
			return new ResponseEntity<>(employee, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(e, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") Integer id) {
		emloyeeService.deleteEmployee(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Employee>> searchEmployee(@RequestParam(value = "name", required = false) String name) {
		if (name == null) {
			return getAllEmployees();
		} else {
			List<Employee> e = emloyeeService.searchEmployee(name);
			return new ResponseEntity<>(e, HttpStatus.OK);
		}
	}
	
	@GetMapping("/check")
	public ResponseEntity<Credit> checkEligibility(@RequestParam(value = "pan", required = true) String pan) {
			Credit credit = emloyeeService.checkEligibility(pan);
			if (credit == null) {
				return new ResponseEntity<>(credit, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(credit, HttpStatus.OK);
	}
}