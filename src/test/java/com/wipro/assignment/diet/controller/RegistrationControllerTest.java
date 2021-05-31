package com.wipro.assignment.diet.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.assignment.DemoApplication;
import com.assignment.diet.controller.RegistrationController;
import com.assignment.diet.entity.Registration;
import com.assignment.diet.service.RegistrationService;
import com.assignment.diet.utility.Helper;
import com.assignment.diet.vo.RegistrationVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = { DemoApplication.class, RegistrationController.class })
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles({ "test.junit" })
public class RegistrationControllerTest {

	private String registrationString;

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private RegistrationService registrationService;
	
	//@Captor
	//ArgumentCaptor<List<AssessmentAnswer>> answerArgumentCaptor;
	//mvn verify-DskipTests=true -DwithHistory org.pitest:pitest-maven:mutationCoverage

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		RegistrationVO mockRegistrationVO = new RegistrationVO();
		mockRegistrationVO.setFullName("rajeev");
		mockRegistrationVO.setEmail("rajeev@gmail.com");
		mockRegistrationVO.setGender("M");
		mockRegistrationVO.setHeight(180.0F);
		mockRegistrationVO.setWeight(110.0F);
		mockRegistrationVO.setCountry("IN");
		
		Registration registration = Helper.getEntityFromVO(mockRegistrationVO);

		try {
			registrationString = new ObjectMapper().writeValueAsString(mockRegistrationVO);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		when(registrationService.registerChallanger(Mockito.any(RegistrationVO.class))).thenReturn(registration);
	}

	@Test
	public void registrationTest() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/diet/registration")
				.accept(MediaType.APPLICATION_JSON)
				.content(registrationString).contentType(MediaType.APPLICATION_JSON);

		ResultActions action = mockMvc.perform(requestBuilder);
		action.andExpect(status().isCreated());
	}
}
