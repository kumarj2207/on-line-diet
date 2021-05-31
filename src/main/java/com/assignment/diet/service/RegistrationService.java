package com.assignment.diet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.diet.dao.RegistrationDao;
import com.assignment.diet.entity.Registration;
import com.assignment.diet.utility.Helper;
import com.assignment.diet.vo.BMI;
import com.assignment.diet.vo.RegistrationVO;

@Service
public class RegistrationService {

	@Autowired
	private RegistrationDao dao;
	
	public Registration registerChallanger(RegistrationVO registrationVO) {
		float height = registrationVO.getHeight();
		float weight = registrationVO.getWeight();
		
		
		float heightInMeters = height / 100;
		System.out.println(heightInMeters);
		float heightInMetersSquare = heightInMeters * heightInMeters;
		System.out.println(heightInMetersSquare);
		
		
		double bmi = weight / heightInMetersSquare;
		
		Registration registration = Helper.getEntityFromVO(registrationVO);
		if (bmi < 18.5)
			registration.setBmi(BMI.THINNESS);
		else if (bmi < 25)
			registration.setBmi(BMI.NORMAL);
		else if (bmi < 30)
			registration.setBmi(BMI.OVERWEIGHT);
		else
			registration.setBmi(BMI.OBESE);

		dao.save(registration);
		return registration;
	}

}
