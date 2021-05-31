package com.assignment.diet.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.assignment.diet.dao.BatchDao;
import com.assignment.diet.dao.ChallengerDao;
import com.assignment.diet.dao.DietUserDao;
import com.assignment.diet.dao.MessageDao;
import com.assignment.diet.dao.MotivatorRepository;
import com.assignment.diet.dao.RegistrationDao;
import com.assignment.diet.entity.Challenger;
import com.assignment.diet.entity.DietUser;
import com.assignment.diet.entity.Measurement;
import com.assignment.diet.entity.Message;
import com.assignment.diet.entity.Motivator;
import com.assignment.diet.entity.Registration;
import com.assignment.diet.response.vo.DietUserResponseVO;
import com.assignment.diet.response.vo.RegistrationResponseVO;
import com.assignment.diet.utility.Helper;
import com.assignment.diet.vo.Approval;
import com.assignment.diet.vo.MessageVO;
import com.assignment.diet.vo.MotivatorVO;
import com.assignment.diet.vo.Role;
import com.assignment.diet.vo.Status;

@Service
public class AdminService {

	@Autowired
	private RegistrationDao registrationDao;

	@Autowired
	private DietUserDao userDao;

	@Autowired
	private ChallengerDao challengerDao;

	@Autowired
	private BatchDao batchDao;

	@Autowired
	private MailService mailService;

	@Autowired
	private MessageDao messageDao;
	
	@Autowired
	private MotivatorRepository motivatorRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	public RegistrationResponseVO getNewRegistrations() {
		RegistrationResponseVO response = new RegistrationResponseVO();
		response.setRegistrations(registrationDao.getNewRegistrations());
		response.setBatches(batchDao.findAll());
		return response;
	}

	public void approve(Approval x) {
	//	List<Approval> l = approvalVO.getList();
//		for (Approval x : l) {
//		l.forEach(x -> {
			if (x.isApproved()) {
				Registration registration = registrationDao.findById(x.getEmail()).get();
				registration.setStatus(Status.APPROVED);
				registrationDao.save(registration);

				DietUser user = Helper.getDietUserFromRegistration(registration);
				user.setPassword(bcryptEncoder.encode("password123"));
				user.setRole(Role.CHALLENGER);
				user = userDao.save(user);

				Measurement measurement = Helper.getMeasurementFromRegistration(registration);
				Challenger challenger = new Challenger();
				challenger.setReferralId(user.getReferralId());
				challenger.setBatch(batchDao.findById(x.getBatch()).get());
				challenger.getMeasurement().add(measurement);
				
				measurement.setChallenger(challenger);
				challengerDao.save(challenger);

				mailService.sendMessage(x.getEmail(), prepareWelcomeMessage(user));
			} else {
				Registration r = registrationDao.findById(x.getEmail()).get();
				r.setStatus(Status.REJECTED);
				registrationDao.save(r);

				mailService.sendMessage(x.getEmail(), prepareRejectMessage(x.getRejectionReason()));
			}
		//};
	}

	public void postMessage(MessageVO messageVO) {
		Message message = new Message();
		message.setMessage(messageVO.getMessage());
		message.setPostedBy(userDao.findById(messageVO.getPostedBy()).get());
		message.setPostDate(new Date());
		for (Long id : messageVO.getReferralIds()) {
			message.getChallengers().add(challengerDao.findById(id).get());
		}
		
		messageDao.save(message);
	}

	private String prepareWelcomeMessage(DietUser user) {
		StringBuilder mailBody = new StringBuilder("Welcome in dite plan.\nYour User Id is: ");
		mailBody.append(user.getUserName()).append("\n").append("Your temporary Password is: password123")
				.append("\n").append("Your Referral Code is: ").append(user.getReferralId());
		return mailBody.toString();
	}

	private String prepareRejectMessage(String rejectionReason) {
		StringBuilder mailBody = new StringBuilder("Sorry !!\nYou can not join diet plan.\n");
		mailBody.append(rejectionReason);
		return mailBody.toString();
	}

	public List<DietUserResponseVO> getAllDietUsers() {
		List<String> users = userDao.getAllDietUserWithBatch();
		List<DietUserResponseVO> t = Helper.getDietUserResponseVO(users);
		List<DietUserResponseVO> challengers = t.stream()
				.filter(x -> x.getRole().equalsIgnoreCase(Role.CHALLENGER.name()))
				.collect(Collectors.toList());
		List<DietUserResponseVO> motivators = t.stream()
						.filter(x -> x.getRole().equalsIgnoreCase(Role.MOTIVATOR.name()))
						.collect(Collectors.toList());
		Map<DietUserResponseVO, String> map = new HashMap<>();
		for (DietUserResponseVO dietUserResponseVO : motivators) {
			String batch = map.get(dietUserResponseVO);
			if (batch == null) {
				map.put(dietUserResponseVO, dietUserResponseVO.getBatchName());	
			} else {
				batch = batch + "," + dietUserResponseVO.getBatchName();
				map.put(dietUserResponseVO, batch);	
			}
		}
		
		motivators = new ArrayList<>();
		map.forEach( (k,v) -> { k.setBatchName(v);} );
		for (DietUserResponseVO dietUserResponseVO : map.keySet()) {
			motivators.add(dietUserResponseVO);
		}
		
		challengers.addAll(motivators);
		return challengers;
	}

	public void deleteDietUser(Long referralId) {
		motivatorRepository.deleteById(referralId);
		userDao.deleteById(referralId);
	}

	public void addMotivator(MotivatorVO motivatorVO) {
		DietUser user = Helper.getDietUserFromMotivatorVO(motivatorVO);
		user.setPassword(bcryptEncoder.encode("password123"));
		user.setRole(Role.MOTIVATOR);
		user = userDao.save(user);
		
		Motivator motivator = new Motivator();
		motivator.setReferralId(user.getReferralId());
		
		motivatorRepository.save(motivator);
	}

	public DietUserResponseVO getDietUser(Long referralId) {
		String user = userDao.getSingleDietUserWithBatch(referralId).orElseThrow(() -> new RuntimeException("Not found"));
		return Helper.getDietUserResponseVO(Arrays.asList(user)).get(0);
	}

	public void updateDietUser(Long referralId, MotivatorVO updatedUser) throws JSONException {
		DietUser user = userDao.findById(referralId).get();
		user.setFullName(updatedUser.getFullName());
		user.setAge(updatedUser.getAge());
		user.setGender(updatedUser.getGender());
		user.setMobile(updatedUser.getMobile());
		user.setAddress(updatedUser.getAddress());
		user.setCity(updatedUser.getCity());
		user.setState(updatedUser.getState());
		user.setCountry(updatedUser.getCountry());
		user.setPinCode(updatedUser.getPinCode());
		
		userDao.save(user);
	}

}
