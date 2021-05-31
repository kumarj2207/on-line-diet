package com.assignment.diet.response.vo;

import com.assignment.diet.vo.Role;

public class ReturnedUser {

	private Long referralId;
	private Role role;
	private String userName;
	private String token;

	public ReturnedUser(Long referralId, String userName, Role role, String token) {
		this.referralId = referralId;
		this.userName = userName;
		this.role = role;
		this.token = token;
	}

	public Long getReferralId() {
		return referralId;
	}

	public void setReferralId(Long referralId) {
		this.referralId = referralId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
