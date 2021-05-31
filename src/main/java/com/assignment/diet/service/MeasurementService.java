package com.assignment.diet.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.diet.dao.ChallengerDao;
import com.assignment.diet.dao.DailyLogRepository;
import com.assignment.diet.dao.MeasurementDao;
import com.assignment.diet.entity.Challenger;
import com.assignment.diet.entity.DailyLog;
import com.assignment.diet.entity.Measurement;
import com.assignment.diet.response.vo.DailyLogResponseVO;
import com.assignment.diet.response.vo.MeasurementResponseVO;
import com.assignment.diet.utility.Helper;

@Service
public class MeasurementService {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private MeasurementDao measurementDao;

	@Autowired
	private ChallengerDao challengerDao;

	@Autowired
	private DailyLogRepository dailyLogRepository;

	public List<MeasurementResponseVO> getMeasurements(Long referralId, int month) {
		String YYYYMM = Year.now().toString() + "-" + month + "-";
		String start = YYYYMM + "01", end = Helper.getEndDate(YYYYMM, month);
		Date startDate = null, endDate = null;
		try {
			startDate = sdf.parse(start);
			endDate = sdf.parse(end);

			Challenger challenger = challengerDao.findById(referralId).get();
			List<Measurement> measurements = measurementDao.findByChallengerAndMeasurementDateBetween(challenger,
					startDate, endDate);
			List<MeasurementResponseVO> list = new ArrayList<>();
			for (Measurement measurement : measurements) {
				list.add(Helper.getMeasurementResponseVOFromMeasurement(measurement));
			}
			return list;
		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	public List<DailyLogResponseVO> getDailyLog(Long referralId, Integer month) {
		String YYYYMM = Year.now().toString() + "-" + month + "-";
		String start = YYYYMM + "01", end = Helper.getEndDate(YYYYMM, month);
		Date startDate = null, endDate = null;
		try {
			startDate = sdf.parse(start);
			endDate = sdf.parse(end);

			Challenger challenger = challengerDao.findById(referralId).get();
			List<DailyLog> dailyLogs = dailyLogRepository.findByChallengerAndLogDateBetween(challenger, startDate, endDate);
			List<DailyLogResponseVO> list = new ArrayList<>();
			for (DailyLog dailyLog : dailyLogs) {
				list.add(Helper.getDailyLogResponseVOFromDailyLog(dailyLog));
			}
			return list;
		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
