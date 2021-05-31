package com.assignment.diet.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.diet.entity.Challenger;
import com.assignment.diet.response.vo.DietUserResponseVO;
import com.assignment.diet.response.vo.MeasurementResponseVO;
import com.assignment.diet.response.vo.ViewMessagesResponseVO;
import com.assignment.diet.service.ChallengerService;
import com.assignment.diet.vo.DailyLogVO;
import com.assignment.diet.vo.MeasurementVO;

@Controller
@RequestMapping("/diet")
@CrossOrigin("http://localhost:4200")
public class ChallengerController {

	@Autowired
	private ChallengerService challengerService;
	
	@GetMapping("/challenger/{refId}")
	public ResponseEntity<Challenger> getChallenger(@PathVariable("refId") Long challengerId) {
		Challenger challenger = challengerService.getChallenger(challengerId);
		return ResponseEntity.ok(challenger);
	}

	@GetMapping("/viewmessages/{refId}")
	public ResponseEntity<ViewMessagesResponseVO> viewMessages(@PathVariable("refId") Long challengerId) {
		ViewMessagesResponseVO messages = challengerService.viewMessages(challengerId);
		return ResponseEntity.ok(messages);
	}

	@GetMapping("/viewmeasurement/{refId}")
	public ResponseEntity<List<MeasurementResponseVO>> viewMeasurement(@PathVariable("refId") Long challengerId) {
		List<MeasurementResponseVO> measurements = challengerService.viewMeasurement(challengerId);
		return ResponseEntity.ok(measurements);
	}
	
	@PostMapping("/postmeasurement/{refId}")
	public ResponseEntity<Void> postMeasurement(@PathVariable("refId") Long referrralId, 
			@RequestBody MeasurementVO measurement) {
		challengerService.postMeasurement(referrralId, measurement);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/postdailylog/{refId}")
	public ResponseEntity<Void> postDailyLog(@RequestBody DailyLogVO dailyLogVO,
			@PathVariable("refId") Long referrralId) {
		challengerService.postDailyLog(referrralId, dailyLogVO);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/setmessageasread/{msgId}")
	public ResponseEntity<Void> setMessageAsRead(@PathVariable("msgId") Long messageId) {
		challengerService.setMessageAsRead(messageId);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/upload/{id}")
	public ResponseEntity<String> uploadMeasurement(@RequestParam("file") MultipartFile file,
			@PathVariable("id") Long id) {
		System.out.println(id);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity
				.ok("{\"message\": \"" + file.getOriginalFilename() + " has been uploaded successfully.\"}");
	}
	
	@GetMapping("/challengers/{batchId}")
	public ResponseEntity<List<DietUserResponseVO>> viewChallengers(@PathVariable("batchId") Long batchId) {
		List<DietUserResponseVO> challengers = challengerService.viewChallengers(batchId);
		return ResponseEntity.ok(challengers);
	}

	@PostMapping(path = "/download")
	public ResponseEntity<Resource> download() throws IOException {
		File file = new File("F:\\to_basabraj\\sample_measurement.xlsx");

		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=sample_measurement.xlsx");
		header.add("Cache-Control", "no-cache, no-store, must-revalidate");
		header.add("Pragma", "no-cache");
		header.add("Expires", "0");

		Path path = Paths.get(file.getAbsolutePath());
		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

		return ResponseEntity.ok().headers(header).contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
	}
}
