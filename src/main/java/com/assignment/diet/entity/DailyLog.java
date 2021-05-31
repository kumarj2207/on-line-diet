package com.assignment.diet.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class DailyLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long logId;

	private String breakfast, lunch, snack, dinner, fruitsAandVegetables, workoutsDone;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date logDate = new Date();

	@ManyToOne
	@JoinColumn(name = "challenger")
	private Challenger challenger;

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public String getBreakfast() {
		return breakfast;
	}

	public void setBreakfast(String breakfast) {
		this.breakfast = breakfast;
	}

	public String getFruitsAandVegetables() {
		return fruitsAandVegetables;
	}

	public void setFruitsAandVegetables(String fruitsAandVegetables) {
		this.fruitsAandVegetables = fruitsAandVegetables;
	}

	public String getWorkoutsDone() {
		return workoutsDone;
	}

	public void setWorkoutsDone(String workoutsDone) {
		this.workoutsDone = workoutsDone;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public String getLunch() {
		return lunch;
	}

	public void setLunch(String lunch) {
		this.lunch = lunch;
	}

	public String getSnack() {
		return snack;
	}

	public void setSnack(String snack) {
		this.snack = snack;
	}

	public String getDinner() {
		return dinner;
	}

	public void setDinner(String dinner) {
		this.dinner = dinner;
	}

	public Challenger getChallenger() {
		return challenger;
	}

	public void setChallenger(Challenger challenger) {
		this.challenger = challenger;
	}

}
