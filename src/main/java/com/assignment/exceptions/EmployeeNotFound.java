package com.assignment.exceptions;

@SuppressWarnings("serial")
public class EmployeeNotFound extends Exception {
	
	public EmployeeNotFound() {
		super();
	}

	public EmployeeNotFound(String message) {
		super(message);
	}
}
