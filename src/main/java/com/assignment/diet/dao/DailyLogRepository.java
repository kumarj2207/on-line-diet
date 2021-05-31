package com.assignment.diet.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.diet.entity.Challenger;
import com.assignment.diet.entity.DailyLog;

public interface DailyLogRepository extends JpaRepository<DailyLog, Long> {

	List<DailyLog> findByChallengerAndLogDateBetween(Challenger challenger, Date startDate, Date endDate);

}
