package com.assignment.diet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.diet.dao.BatchDao;
import com.assignment.diet.dao.DietUserDao;
import com.assignment.diet.dao.MotivatorRepository;
import com.assignment.diet.entity.Batch;
import com.assignment.diet.entity.DietUser;
import com.assignment.diet.entity.Motivator;
import com.assignment.diet.response.vo.BatchResponseVO;
import com.assignment.diet.response.vo.DietUserResponseVO;
import com.assignment.diet.utility.Helper;

@Service
public class BatchService {
	
	@Autowired
	private BatchDao batchDao;
	
	@Autowired
	private DietUserDao dietUserDao;
	
	@Autowired
	private MotivatorRepository motivatorRepository;

	public List<BatchResponseVO> getAllBatchWithParentName() {
		List<BatchResponseVO> parentList = getAllParentBatch();
		List<BatchResponseVO> list = batchDao.getAllBatchWithParentName();
		List<BatchResponseVO> ret = new ArrayList<>();
		for (BatchResponseVO batchResponseVO : parentList) {
			String parentName = batchResponseVO.getName();
			Stream<BatchResponseVO> stream = list.stream();
			//BatchResponse br = new BatchResponse(parentName, 
			 //stream.filter(x -> x.getParentBatchName()!=null&&x.getParentBatchName().equals(parentName))
				//	.collect(Collectors.toList()));
			
			List<BatchResponseVO> temp = stream.filter(x -> x.getParentBatchName()!=null&&x.getParentBatchName().equals(parentName))
					.collect(Collectors.toList());
			ret.add(batchResponseVO);
			ret.addAll(temp);
			
			stream.close();
		}
		return ret;
	}
	
//	public List<BatchResponse> getAllBatchWithParentNamey() {
//		List<BatchResponseVO> parentList = getAllParentBatch();
//		List<BatchResponseVO> list = batchDao.getAllBatchWithParentName();
//		List<BatchResponseVO> ret = new ArrayList<>();
//		
//		for (BatchResponseVO batchResponseVO : parentList) {
//			//ret.add(batchResponseVO);
//			String parentName = batchResponseVO.getName();
//			Stream<BatchResponseVO> stream = list.stream();
//			List<BatchResponse> temp = stream.filter(x -> x.getParentBatchName()!=null&&x.getParentBatchName().equals(parentName))
//					.collect(Collectors.toList()));
//			stream.close();
//			ret.add(br);
//		}
//		return ret;
//	}

	public List<BatchResponseVO> getSingleBatchWithParentName(Long batchId) {
		 List<BatchResponseVO> l = batchDao.getSingleBatchWithParentName(batchId);
		 BatchResponseVO brvo = l.get(0);
		 
		 brvo.setBatches(batchDao.findAll());
		 return l;
	}

	public List<BatchResponseVO> getAllParentBatch() {
		List<Batch> parentBatches = batchDao.findByParentBatchIdIsNull();
		List<BatchResponseVO> resp = new ArrayList<>(); 
		for (Batch batch : parentBatches) {
			BatchResponseVO brvo = new BatchResponseVO(batch.getBatchId(), batch.getName(), null, null);
			resp.add(brvo);
		}
		return resp;
	}

	public List<BatchResponseVO> getGroupByParentBatchId(Long batchId) {
		List<Batch> groups = batchDao.findByParentBatchId(batchId);
		List<BatchResponseVO> resp = new ArrayList<>(); 
		for (Batch batch : groups) {
			BatchResponseVO brvo = new BatchResponseVO(batch.getBatchId(), batch.getName(), batchId, null);
			resp.add(brvo);
		}
		return resp;		
	}

	public void addBatch(String name, Long parentBatchId) {
		Batch batch = new Batch();
		batch.setName(name);
		batch.setParentBatchId(parentBatchId);
		batchDao.save(batch );
	}

	public List<DietUserResponseVO> viewMotivators(Long batchId) {
		Batch batch = batchDao.findById(batchId).get();
		Set<Motivator> motivators = batch.getMotivators();
		List<Long> referralIds = motivators.stream().map(x -> x.getReferralId()).collect(Collectors.toList());
		List<DietUserResponseVO> users = dietUserDao.findByReferralIdIn(referralIds);
		return users;//Helper.getDietUserResponseVO(users);
	}

	public void addMotivatorInBatch(Long batchId, Long referralId) {
		Batch batch = batchDao.findById(batchId).get();
		Motivator motivator = motivatorRepository.findById(referralId).orElseThrow(() -> new RuntimeException("Not found"));
		
//		List<Motivator> motivators = new ArrayList<>();
//		motivators.add(motivator);
		batch.getMotivators().add(motivator);
		batchDao.save(batch);
	}

	public void removeMotivatorFromBatch(Long batchId, Long referralId) {
		Batch batch = batchDao.findById(batchId).get();
		Motivator motivator = motivatorRepository.findById(referralId).orElseThrow(() -> new RuntimeException("Not found"));
		
		batch.getMotivators().remove(motivator);
		batchDao.save(batch);
	}

	public void assignBatchToMotivator(Long referralId, List<Long> batchIds) {
		Motivator motivator = motivatorRepository.findById(referralId).get();
		motivator.getBatches().addAll(batchDao.findAllById(batchIds));
		
		motivatorRepository.save(motivator);
	}

	public void isBatchExists(String name, Long parentBatchId) {
		Optional<Batch> o = batchDao.findByNameAndParentBatchId(name, parentBatchId);
		if(o.isPresent()){
			throw new RuntimeException("Batch already exists in given category.");
		}
	}

}
