package com.assignment.diet.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.assignment.diet.response.vo.BatchResponseVO;
import com.assignment.diet.response.vo.DietUserResponseVO;
import com.assignment.diet.service.BatchService;

@Controller
@RequestMapping("/diet")
@CrossOrigin("http://localhost:4200")
public class BatchController {

	@Autowired
	private BatchService batchService;

	@GetMapping("/getallbatch")
	public ResponseEntity<List<BatchResponseVO>> getAllBatchWithParentName() {
		List<BatchResponseVO> brvo = batchService.getAllBatchWithParentName();
		return ResponseEntity.ok(brvo);
	}

	@GetMapping("/getallparentbatch")
	public ResponseEntity<List<BatchResponseVO>> getAllParentBatch() {
		List<BatchResponseVO> brvo = batchService.getAllParentBatch();
		return ResponseEntity.ok(brvo);
	}

	@GetMapping("/getbatch/{id}")
	public ResponseEntity<List<BatchResponseVO>> getBatchByBatchId(@PathVariable("id") Long batchId) {
		List<BatchResponseVO> brvo = batchService.getSingleBatchWithParentName(batchId);
		return ResponseEntity.ok(brvo);
	}

	@GetMapping("/getgroup/{id}")
	public ResponseEntity<List<BatchResponseVO>> getGroupByParentBatchId(@PathVariable("id") Long batchId) {
		List<BatchResponseVO> brvo = batchService.getGroupByParentBatchId(batchId);
		return ResponseEntity.ok(brvo);
	}

	@PostMapping("/addbatch")
	public ResponseEntity<String> addBatch(@RequestParam("name") String name, 
										@RequestParam("parentBatchId") Long parentBatchId){
		try {
			batchService.isBatchExists(name, parentBatchId);
			batchService.addBatch(name, parentBatchId);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{\"message\": \"" + e.getMessage() + "\"}");
		}
		return ResponseEntity.ok("{\"message\": \"Batch created.\"}");//.build();
	}
	
	@GetMapping("/motivators/{batchId}")
	public ResponseEntity<List<DietUserResponseVO>> viewMotivators(@PathVariable("batchId") Long batchId) {
		List<DietUserResponseVO> challengers = batchService.viewMotivators(batchId);
		return ResponseEntity.ok(challengers);
	}
	
	@GetMapping("/batmotiv/{batchId}/{referralId}")
	public ResponseEntity<Void> addMotivatorInBatch(@PathVariable("batchId") Long batchId,
			@PathVariable("referralId") Long referralId) {
		batchService.addMotivatorInBatch(batchId, referralId);  
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/batmotivremove/{batchId}/{referralId}")
	public ResponseEntity<Void> removeMotivatorFromBatch(@PathVariable("batchId") Long batchId,
			@PathVariable("referralId") Long referralId) {
		batchService.removeMotivatorFromBatch(batchId, referralId);  
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/assignbatchtomotivator")
	public ResponseEntity<Void> assignBatchToMotivator(@RequestBody String body) throws Exception {
		JSONObject jsonObject = new JSONObject(body);
		Long referralId = jsonObject.getLong("referralId");
		JSONArray array = jsonObject.getJSONArray("batchIds");
		List<Long> batchIds = new ArrayList<>();
		for(int i =0;i < array.length(); i++) {
			batchIds.add(array.getLong(i));
		}
		batchService.assignBatchToMotivator(referralId, batchIds);
		return ResponseEntity.noContent().build();
	}
	
}
