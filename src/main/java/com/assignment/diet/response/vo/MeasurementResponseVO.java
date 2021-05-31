package com.assignment.diet.response.vo;

import java.util.Date;

import com.assignment.diet.vo.BMI;

public class MeasurementResponseVO {
	private Long measurementId;

	private float height, weight, chest, waist, shoulders, biceps, forearms, legs, thighs;

	private Date measurementDate;

	private BMI bmi;

	public Long getMeasurementId() {
		return measurementId;
	}

	public void setMeasurementId(Long measurementId) {
		this.measurementId = measurementId;
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

	public BMI getBmi() {
		return bmi;
	}

	public void setBmi(BMI bmi) {
		this.bmi = bmi;
	}

	public float getChest() {
		return chest;
	}

	public void setChest(float chest) {
		this.chest = chest;
	}

	public float getWaist() {
		return waist;
	}

	public void setWaist(float waist) {
		this.waist = waist;
	}

	public float getShoulders() {
		return shoulders;
	}

	public void setShoulders(float shoulders) {
		this.shoulders = shoulders;
	}

	public float getBiceps() {
		return biceps;
	}

	public void setBiceps(float biceps) {
		this.biceps = biceps;
	}

	public float getForearms() {
		return forearms;
	}

	public void setForearms(float forearms) {
		this.forearms = forearms;
	}

	public float getLegs() {
		return legs;
	}

	public void setLegs(float legs) {
		this.legs = legs;
	}

	public float getThighs() {
		return thighs;
	}

	public void setThighs(float thighs) {
		this.thighs = thighs;
	}

	public Date getMeasurementDate() {
		return measurementDate;
	}

	public void setMeasurementDate(Date measurementDate) {
		this.measurementDate = measurementDate;
	}

}
