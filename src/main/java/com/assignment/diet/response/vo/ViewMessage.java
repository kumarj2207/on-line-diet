package com.assignment.diet.response.vo;

import java.util.Date;

public class ViewMessage {

	private String message;
	private Date postDate;
	private String fullName;
	private String role;
	private boolean read;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public boolean getRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

}
