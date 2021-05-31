package com.assignment.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.dao.CreditDao;
import com.assignment.dao.EmployeeDao;
import com.assignment.entity.Credit;
import com.assignment.entity.Employee;
import com.assignment.exceptions.EmployeeNotFound;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private CreditDao creditDao;
	
	@Transactional
	public List<Employee> getAllEmployees() {
		return employeeDao.findAll();
	}

	@Transactional
	public Employee getEmployee(Integer id) {
		return employeeDao.findById(id).get();
	}
	
	@Transactional
	public Employee addEmployee(Employee employee) {
		return employeeDao.save(employee);
	}

	@Transactional
	public void deleteEmployee(Integer id) {
		employeeDao.deleteById(id);
	}

	@Transactional
	public Employee updateEmployee(Integer id, Employee updatedEmployee) throws EmployeeNotFound {
		Optional<Employee> optional = employeeDao.findById(id);
		Employee existingEmployee = null;
		if (optional.isPresent()) {
			existingEmployee = optional.get();
			existingEmployee.setName(updatedEmployee.getName());
			existingEmployee.setLocation(updatedEmployee.getLocation());
			existingEmployee.setEmail(updatedEmployee.getEmail());
			existingEmployee.setMobile(updatedEmployee.getMobile());
			employeeDao.save(existingEmployee);
		} else {
			throw new EmployeeNotFound(id + " not found.");
		}
		
		return existingEmployee;
	}

	public List<Employee> searchEmployee(String name) {
		System.out.println(name);
		return employeeDao.findByName(name.toUpperCase());
	}

	@Transactional
	public Credit checkEligibility(String pan) {
		return creditDao.findByPan(pan);
	}

} 