package com.assignment.diet.response.vo;

public class DietUserResponseVO {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((referralId == null) ? 0 : referralId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DietUserResponseVO other = (DietUserResponseVO) obj;
		if (referralId == null) {
			if (other.referralId != null)
				return false;
		} else if (!referralId.equals(other.referralId))
			return false;
		return true;
	}

	private Long referralId;
	private String role;
	private String fullName;
	private int age;
	private String gender;
	private String mobile;
	private String email;
	private String address;
	private String city;
	private String state;
	private Long batchId;
	private String country;
	private String pinCode;
	private String batchName;
	
	public DietUserResponseVO(Long referralId, String fullName, int age, String gender, String mobile, String email) {
		this.referralId = referralId;
		this.fullName = fullName;
		this.age = age;
		this.gender = gender;
		this.mobile = mobile;
		this.email = email;
	}
	
	
	public DietUserResponseVO(Long referralId, String role, String fullName, int age, String gender, String mobile,
			 String address, String city, String state) {
		this.referralId = referralId;
		this.role = role;
		this.fullName = fullName;
		this.age = age;
		this.gender = gender;
		this.mobile = mobile;
		//this.email = email;
		this.address = address;
		this.city = city;
		this.state = state;
//		this.country = country;
	//	this.pinCode = pinCode;
	}
	
	public DietUserResponseVO(Long referralId, String fullName, int age, String gender, String mobile, String role,
			Long batchId, String batchName, String email) {
		this.referralId = referralId;
		this.role = role;
		this.fullName = fullName;
		this.age = age;
		this.gender = gender;
		this.mobile = mobile;
		this.batchId = batchId;
		this.batchName = batchName;
		this.email = email;
	}
	


	public Long getReferralId() {
		return referralId;
	}

	public void setReferralId(Long referralId) {
		this.referralId = referralId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}



}
