package com.assignment.diet.response.vo;

import java.util.List;

import com.assignment.diet.entity.Batch;
import com.assignment.diet.entity.Registration;

public class RegistrationResponseVO {

	private List<Registration> registrations;
	private List<Batch> batches;

	public List<Registration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(List<Registration> registrations) {
		this.registrations = registrations;
	}

	public List<Batch> getBatches() {
		return batches;
	}

	public void setBatches(List<Batch> batches) {
		this.batches = batches;
	}

}
