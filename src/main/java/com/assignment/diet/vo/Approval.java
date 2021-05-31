package com.assignment.diet.vo;

public class Approval {

	private String email;
	private boolean approved;
	private String rejectionReason;
	private Long batch;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	public Long getBatch() {
		return batch;
	}

	public void setBatch(Long batch) {
		this.batch = batch;
	}
}
