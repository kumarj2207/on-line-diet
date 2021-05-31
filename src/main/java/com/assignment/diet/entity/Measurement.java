package com.assignment.diet.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.assignment.diet.vo.BMI;

@Entity
@Table
public class Measurement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long measurementId;

	private float height, weight, chest, waist, shoulders, biceps, forearm, leg, thighs;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date measurementDate = new Date();

	@Enumerated(EnumType.STRING)
	private BMI bmi;

	@ManyToOne
	@JoinColumn(name = "challenger")
	private Challenger challenger;

	public Long getMeasurementId() {
		return measurementId;
	}

	public void setMeasurementId(Long measurementId) {
		this.measurementId = measurementId;
	}

	public Challenger getChallenger() {
		return challenger;
	}

	public void setChallenger(Challenger challenger) {
		this.challenger = challenger;
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

	public float getForearm() {
		return forearm;
	}

	public void setForearm(float forearm) {
		this.forearm = forearm;
	}

	public float getLeg() {
		return leg;
	}

	public void setLeg(float leg) {
		this.leg = leg;
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
