package com.assignment.diet.vo;

import com.assignment.diet.vo.DietaryCustom;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationVO {
	private String fullName;
	private int age;
	private String gender;
	private String mobile;
	private String email;
	private String address;
	private String city;
	private String state;
	private String country;
	private String pinCode;
	private float height;
	private float weight;
	private String reason;
	private boolean anyExistingMedicalConditions;
	private boolean anyExistingDietaryRestrictions;
	private DietaryCustom dietaryCustom;
	private boolean pregnantStatus;
	private Long referralId;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public boolean isAnyExistingMedicalConditions() {
		return anyExistingMedicalConditions;
	}

	public void setAnyExistingMedicalConditions(boolean anyExistingMedicalConditions) {
		this.anyExistingMedicalConditions = anyExistingMedicalConditions;
	}

	public boolean isAnyExistingDietaryRestrictions() {
		return anyExistingDietaryRestrictions;
	}

	public void setAnyExistingDietaryRestrictions(boolean anyExistingDietaryRestrictions) {
		this.anyExistingDietaryRestrictions = anyExistingDietaryRestrictions;
	}

	public DietaryCustom getDietaryCustom() {
		return dietaryCustom;
	}

	public void setDietaryCustom(DietaryCustom dietaryCustom) {
		this.dietaryCustom = dietaryCustom;
	}

	public boolean isPregnantStatus() {
		return pregnantStatus;
	}

	public void setPregnantStatus(boolean pregnantStatus) {
		this.pregnantStatus = pregnantStatus;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Long getReferralId() {
		return referralId;
	}

	public void setReferralId(Long referralId) {
		this.referralId = referralId;
	}

}
