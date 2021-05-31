package com.assignment.diet.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.diet.entity.Challenger;
import com.assignment.diet.entity.Measurement;


@Repository
public interface MeasurementDao extends JpaRepository<Measurement, Long> {

	List<Measurement> findByChallengerAndMeasurementDateBetween(Challenger challenger, Date start, Date end);

	List<Measurement> findByChallengerOrderByMeasurementDateDesc(Challenger challenger);
	

}
