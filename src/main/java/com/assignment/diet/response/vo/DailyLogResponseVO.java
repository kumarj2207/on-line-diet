package com.assignment.diet.response.vo;

import java.util.Date;

public class DailyLogResponseVO {

	private Long logId;

	private String breakfast, lunch, snack, dinner, fruitsAandVegetables, workoutsDone;

	private Date logDate;

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

}
