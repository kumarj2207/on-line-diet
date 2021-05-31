package com.assignment.diet.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.diet.dao.BatchDao;
import com.assignment.diet.dao.ChallengerDao;
import com.assignment.diet.dao.DailyLogRepository;
import com.assignment.diet.dao.DietUserDao;
import com.assignment.diet.dao.MeasurementDao;
import com.assignment.diet.dao.MessageDao;
import com.assignment.diet.entity.Batch;
import com.assignment.diet.entity.Challenger;
import com.assignment.diet.entity.DailyLog;
import com.assignment.diet.entity.Measurement;
import com.assignment.diet.entity.Message;
import com.assignment.diet.response.vo.DietUserResponseVO;
import com.assignment.diet.response.vo.MeasurementResponseVO;
import com.assignment.diet.response.vo.ViewMessage;
import com.assignment.diet.response.vo.ViewMessagesResponseVO;
import com.assignment.diet.utility.Helper;
import com.assignment.diet.vo.DailyLogVO;
import com.assignment.diet.vo.MeasurementVO;

@Service
public class ChallengerService {

	@Autowired
	private MeasurementDao measurementDao;

	@Autowired
	private ChallengerDao challengerDao;
	
	@Autowired
	private DailyLogRepository dailyLogRepository;
	
	@Autowired
	private MessageDao messageDao;
	
	@Autowired
	private BatchDao batchDao;

	@Autowired
	private DietUserDao dietUserDao;
	
	private Comparator<ViewMessage> comparator = (o1, o2) -> {
		if (o1.getPostDate().after(o2.getPostDate())) {
			return 1;
		} else if (o1.getPostDate().before(o2.getPostDate())) {
			return -1;
		}
		return 0;
	};

	public ViewMessagesResponseVO viewMessages(Long challengerId) {
		List<Message> messages = challengerDao.findById(challengerId).get().getMessages();
		ViewMessagesResponseVO response = new ViewMessagesResponseVO();
		List<ViewMessage> list = new ArrayList<>();
		for (Message message : messages) {
			ViewMessage temp = new ViewMessage();
			temp.setMessage(message.getMessage());
			temp.setPostDate(message.getPostDate());
			temp.setFullName(message.getPostedBy().getFullName());
			temp.setRole(message.getPostedBy().getRole().name());
			temp.setRead(message.getRead());
			list.add(temp);
		}
		Collections.sort(list, comparator);
		response.setPostedMessages(list);
		return response;
	}
	
	public List<MeasurementResponseVO> viewMeasurement(Long challengerId) {
		Challenger challenger = challengerDao.findById(challengerId).get();
		List<Measurement> measurements = measurementDao.findByChallengerOrderByMeasurementDateDesc(challenger);
		List<MeasurementResponseVO> response = new ArrayList<>();
		for (Measurement measurement : measurements) {
			response.add(Helper.getMeasurementResponseVOFromMeasurement(measurement));
		}
		return response;
	}

	public void postMeasurement(Long referrralId, MeasurementVO measurementVO) {
		Challenger challenger = challengerDao.findById(referrralId).get();
		Measurement measurement = Helper.getMeasurementFromMeasurementVO(measurementVO);
		measurement.setChallenger(challenger);
		measurementDao.save(measurement);
	}

	public void postDailyLog(Long referrralId, DailyLogVO dailyLogVO) {
		DailyLog dailyLog = Helper.getDailyLogFromDailyLogVO(dailyLogVO);
		Challenger challenger = challengerDao.findById(referrralId).get();
		dailyLog.setChallenger(challenger);
		
		dailyLogRepository.save(dailyLog);
	}

	public void setMessageAsRead(Long messageId) {
		messageDao.findById(messageId).get().setRead(Boolean.TRUE);
	}

	public List<DietUserResponseVO> viewChallengers(Long batchId) {
		Batch batch = batchDao.findById(batchId).get();
		List<Challenger> challengers = challengerDao.findByBatch(batch);
		List<Long> referralIds = challengers.stream().map(x -> x.getReferralId()).collect(Collectors.toList());
		return dietUserDao.findByReferralIdIn(referralIds);
	}

	public Challenger getChallenger(Long challengerId) {
		return challengerDao.findById(challengerId).get();
	}

}
