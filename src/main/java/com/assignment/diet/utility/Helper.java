package com.assignment.diet.utility;

import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.assignment.diet.entity.DailyLog;
import com.assignment.diet.entity.DietUser;
import com.assignment.diet.entity.Measurement;
import com.assignment.diet.entity.Registration;
import com.assignment.diet.response.vo.DailyLogResponseVO;
import com.assignment.diet.response.vo.DietUserResponseVO;
import com.assignment.diet.response.vo.MeasurementResponseVO;
import com.assignment.diet.vo.BMI;
import com.assignment.diet.vo.DailyLogVO;
import com.assignment.diet.vo.MeasurementVO;
import com.assignment.diet.vo.MotivatorVO;
import com.assignment.diet.vo.RegistrationVO;

public class Helper {

	public static Registration getEntityFromVO(RegistrationVO registrationVO) {
		Registration entity = new Registration();
		entity.setAddress(registrationVO.getAddress());
		entity.setAge(registrationVO.getAge());
		entity.setAnyExistingDietaryRestrictions(registrationVO.isAnyExistingDietaryRestrictions());
		entity.setAnyExistingMedicalConditions(registrationVO.isAnyExistingMedicalConditions());
		entity.setCity(registrationVO.getCity());
		entity.setCountry(registrationVO.getCountry());
		entity.setDietaryCustom(registrationVO.getDietaryCustom());
		entity.setEmail(registrationVO.getEmail());
		entity.setFullName(registrationVO.getFullName());
		entity.setGender(registrationVO.getGender());
		entity.setHeight(registrationVO.getHeight());
		entity.setMobile(registrationVO.getMobile());
		entity.setPinCode(registrationVO.getPinCode());
		entity.setPregnantStatus(registrationVO.isPregnantStatus());
		entity.setReason(registrationVO.getReason());
		entity.setState(registrationVO.getState());
		entity.setWeight(registrationVO.getWeight());
		entity.setReferralId(registrationVO.getReferralId());

		return entity;
	}

	public static Measurement getMeasurementFromRegistration(Registration registration) {
		Measurement measurement = new Measurement();
		measurement.setHeight(registration.getHeight());
		measurement.setWeight(registration.getWeight());
		measurement.setBmi(registration.getBmi());
		measurement.setMeasurementDate(new Date());
		return measurement;
	}

	public static DietUser getDietUserFromRegistration(Registration registration) {
		DietUser entity = new DietUser();
		entity.setAddress(registration.getAddress());
		entity.setAge(registration.getAge());
		entity.setCity(registration.getCity());
		entity.setCountry(registration.getCountry());
		entity.setEmail(registration.getEmail());
		entity.setFullName(registration.getFullName());
		entity.setGender(registration.getGender());
		entity.setMobile(registration.getMobile());
		entity.setPinCode(registration.getPinCode());
		entity.setState(registration.getState());
		entity.setUserName(getLoginId(registration.getFullName()));

		return entity;
	}

	public static Measurement getMeasurementFromMeasurementVO(MeasurementVO measurementVO) {
		Measurement measurement = new Measurement();
		measurement.setBiceps(measurementVO.getBiceps());
		measurement.setChest(measurementVO.getChest());
		measurement.setForearm(measurementVO.getForearms());
		measurement.setHeight(measurementVO.getHeight());
		measurement.setLeg(measurementVO.getLegs());
		measurement.setMeasurementDate(new Date());
		measurement.setShoulders(measurementVO.getShoulders());
		measurement.setThighs(measurementVO.getThighs());
		measurement.setWaist(measurementVO.getWaist());
		measurement.setWeight(measurementVO.getWeight());

		float heightInMeters = measurementVO.getHeight() / 100;
		float heightInMetersSquare = heightInMeters * heightInMeters;

		double bmi = measurementVO.getWeight() / heightInMetersSquare;
		if (bmi < 18.5)
			measurement.setBmi(BMI.THINNESS);
		else if (bmi < 25)
			measurement.setBmi(BMI.NORMAL);
		else if (bmi < 30)
			measurement.setBmi(BMI.OVERWEIGHT);
		else
			measurement.setBmi(BMI.OBESE);

		return measurement;
	}

	public static MeasurementResponseVO getMeasurementResponseVOFromMeasurement(Measurement measurement) {
		MeasurementResponseVO measurementRespVO = new MeasurementResponseVO();
		measurementRespVO.setBiceps(measurement.getBiceps());
		measurementRespVO.setChest(measurement.getChest());
		measurementRespVO.setForearms(measurement.getForearm());
		measurementRespVO.setHeight(measurement.getHeight());
		measurementRespVO.setLegs(measurement.getLeg());
		measurementRespVO.setMeasurementDate(measurement.getMeasurementDate());
		measurementRespVO.setShoulders(measurement.getShoulders());
		measurementRespVO.setThighs(measurement.getThighs());
		measurementRespVO.setWaist(measurement.getWaist());
		measurementRespVO.setWeight(measurement.getWeight());
		measurementRespVO.setBmi(measurement.getBmi());
		measurementRespVO.setMeasurementId(measurement.getMeasurementId());

		return measurementRespVO;
	}

	public static String getEndDate(String end, int month) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			end = end + "31";
		} else if (month == 2) {
			if (Year.isLeap(month)) {
				end = end + "29";
			} else {
				end = end + "28";
			}
		} else {
			end = end + "30";
		}
		return end;
	}

	private static String getLoginId(String fullName) {
		int randomNumber = (int) (Math.random() * 9_00_00_000) + 1_00_00_000;
		return fullName.substring(0, 2).toLowerCase() + randomNumber + "@mydiet.com";
	}

	public static DailyLog getDailyLogFromDailyLogVO(DailyLogVO dailyLogVO) {
		DailyLog dailyLog = new DailyLog();
		dailyLog.setBreakfast(dailyLogVO.getBreakfast());
		dailyLog.setFruitsAandVegetables(dailyLogVO.getFruitsAandVegetables());
		dailyLog.setWorkoutsDone(dailyLogVO.getWorkoutsDone());
		dailyLog.setLunch(dailyLogVO.getLunch());
		dailyLog.setDinner(dailyLogVO.getDinner());
		dailyLog.setSnack(dailyLogVO.getSnack());

		return dailyLog;
	}

	public static DailyLogResponseVO getDailyLogResponseVOFromDailyLog(DailyLog dailyLog) {
		DailyLogResponseVO dailyLogResponseVO = new DailyLogResponseVO();
		dailyLogResponseVO.setLogId(dailyLog.getLogId());
		dailyLogResponseVO.setLogDate(dailyLog.getLogDate());
		dailyLogResponseVO.setBreakfast(dailyLog.getBreakfast());
		dailyLogResponseVO.setFruitsAandVegetables(dailyLog.getFruitsAandVegetables());
		dailyLogResponseVO.setWorkoutsDone(dailyLog.getWorkoutsDone());
		dailyLogResponseVO.setLunch(dailyLog.getLunch());
		dailyLogResponseVO.setDinner(dailyLog.getDinner());
		dailyLogResponseVO.setSnack(dailyLog.getSnack());
		return dailyLogResponseVO;
	}

	public static DietUser getDietUserFromMotivatorVO(MotivatorVO motivatorVO) {
		DietUser entity = new DietUser();
		entity.setAddress(motivatorVO.getAddress());
		entity.setAge(motivatorVO.getAge());
		entity.setCity(motivatorVO.getCity());
		entity.setCountry(motivatorVO.getCountry());
		entity.setEmail(getLoginId(motivatorVO.getFullName()));
		entity.setFullName(motivatorVO.getFullName());
		entity.setGender(motivatorVO.getGender());
		entity.setMobile(motivatorVO.getMobile());
		entity.setPinCode(motivatorVO.getPinCode());
		entity.setState(motivatorVO.getState());
		entity.setUserName(getLoginId(motivatorVO.getFullName()));

		return entity;
	}
	
	interface In {
		DietUserResponseVO getDietUserResponseVO(Long referralId, String fullName, int age, String gender, String mobile, String role,
				Long batchId, String batchName, String email);
	}

	static Function<String, DietUserResponseVO> fx = du -> {
		String[] arr = du.split(",", 9);
		In i = DietUserResponseVO::new;
		return i.getDietUserResponseVO(Long.parseLong(arr[0]), arr[1], Integer.parseInt(arr[2]),
				arr[3], arr[4], arr[5], Long.parseLong(arr[6]), arr[7], arr[8]);
	};

	public static List<DietUserResponseVO> getDietUserResponseVO(List<String> users) {
		return users.stream().map(fx).collect(Collectors.toList());
	}

}
