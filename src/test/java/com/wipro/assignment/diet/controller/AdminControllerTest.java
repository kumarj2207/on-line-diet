package com.wipro.assignment.diet.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.assignment.DemoApplication;
import com.assignment.diet.controller.AdminController;
import com.assignment.diet.dao.BatchDao;
import com.assignment.diet.dao.ChallengerDao;
import com.assignment.diet.dao.DailyLogRepository;
import com.assignment.diet.dao.DietUserDao;
import com.assignment.diet.dao.MeasurementDao;
import com.assignment.diet.dao.MessageDao;
import com.assignment.diet.dao.RegistrationDao;
import com.assignment.diet.entity.Batch;
import com.assignment.diet.entity.Challenger;
import com.assignment.diet.entity.Registration;
import com.assignment.diet.response.vo.RegistrationResponseVO;
import com.assignment.diet.utility.Helper;
import com.assignment.diet.vo.Approval;
import com.assignment.diet.vo.ApprovalVO;
import com.assignment.diet.vo.MessageVO;
import com.assignment.diet.vo.RegistrationVO;
import com.assignment.diet.vo.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = { DemoApplication.class, AdminController.class })
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles({ "test.junit" })
public class AdminControllerTest {

	private String token;
	private RegistrationVO mockRegistrationVO;
	private UserDto user;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private RegistrationDao registrationDao;

	@Mock
	private DietUserDao userDao;

	@MockBean
	private ChallengerDao challengerDao;

	@MockBean
	private BatchDao batchDao;

	@MockBean
	private MessageDao messageDao;
	
	@MockBean
	private MeasurementDao measurementDao;

	@MockBean
	private DailyLogRepository dailyLogRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		Batch batch1 = new Batch();
		batch1.setBatchId(1L);
		batch1.setName("VegOnly");

		Batch batch2 = new Batch();
		batch2.setBatchId(2L);
		batch2.setName("NonVegOnly");

		List<Batch> batches = new ArrayList<>();
		batches.add(batch1);
		batches.add(batch2);

		mockRegistrationVO = new RegistrationVO();
		mockRegistrationVO.setFullName("rajeev");
		mockRegistrationVO.setEmail("rajeev@gmail.com");
		mockRegistrationVO.setGender("M");
		mockRegistrationVO.setHeight(180.0F);
		mockRegistrationVO.setWeight(110.0F);
		mockRegistrationVO.setCountry("IN");

		Registration registration = Helper.getEntityFromVO(mockRegistrationVO);
//		System.out.println("registration = " + registration.getEmail());

		when(batchDao.findAll()).thenReturn(batches);
		when(registrationDao.getNewRegistrations()).thenReturn(Collections.<Registration>emptyList());
		when(batchDao.findById(Mockito.anyLong())).thenReturn(Optional.of(batch1));
		//when(challengerDao.save(Mockito.any(Challenger.class))).thenReturn(new Challenger());
		when(registrationDao.findById(Mockito.anyString())).thenReturn(Optional.of(registration));

	}

	@Test
	public void newregistrationsTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/diet/newregistrations")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

		ResultActions action = mockMvc.perform(requestBuilder);
		action.andExpect(status().isUnauthorized());
	}

	@Test
	public void newregistrationsWithTokenTest() throws Exception {
		String token = getJWTToken("admin", "admin@gmail.com");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/diet/newregistrations")
				.accept(MediaType.APPLICATION_JSON).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON);

		ResultActions action = mockMvc.perform(requestBuilder);
		action.andExpect(status().isOk());

		MvcResult result = action.andReturn();
		RegistrationResponseVO vo = mapper.readValue(result.getResponse().getContentAsString(),
				RegistrationResponseVO.class);

//		assertAll("Assert return body contains correct fields.", () -> {
			assertThat(vo).isNotNull();
			assertThat(vo.getBatches().size()).isEqualTo(2);
			assertThat(vo.getRegistrations().size()).isEqualTo(0);
	//	});

		List<Approval> list = new ArrayList<>();
		Approval approval = new Approval();
		approval.setEmail("rajeev@gmail.com");
		approval.setApproved(true);
		approval.setBatch(1L);

		list.add(approval);

		approval = new Approval();
		approval.setEmail("roger@gmail.com");
		approval.setApproved(false);
		approval.setRejectionReason("XYZ");

		list.add(approval);
		ApprovalVO approvalVO = new ApprovalVO();
		approvalVO.setList(list);
		
		requestBuilder = MockMvcRequestBuilders.post("/diet/approval").accept(MediaType.APPLICATION_JSON)
				.header("Authorization", token).content(mapper.writeValueAsBytes(approvalVO))
				.contentType(MediaType.APPLICATION_JSON);

		action = mockMvc.perform(requestBuilder);
		//action.andExpect(status().isNoContent());
		
		MessageVO messageVO = new MessageVO();
		messageVO.setMessage("Test Message");
		messageVO.setPostedBy(1L);
		messageVO.getReferralIds().add(2L);
		messageVO.getReferralIds().add(3L);
		
		requestBuilder = MockMvcRequestBuilders.post("/diet/postmessage").accept(MediaType.APPLICATION_JSON)
				.header("Authorization", token).content(mapper.writeValueAsBytes(messageVO))
				.contentType(MediaType.APPLICATION_JSON);

		when(challengerDao.findById(Mockito.anyLong())).thenReturn(Optional.of(new Challenger()));
		action = mockMvc.perform(requestBuilder);
		action.andExpect(status().isNoContent());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/diet/viewmeasurement/2/11")
				.accept(MediaType.APPLICATION_JSON).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/diet/viewdailylog/2/11")
				.accept(MediaType.APPLICATION_JSON).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	private String getJWTToken(String userName, String email) throws Exception {

		user = new UserDto();
		user.setUserName(userName);
		user.setPassword("password");
		user.setEmail(email);

		String content = mapper.writeValueAsString(user);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/register").accept(MediaType.APPLICATION_JSON)
				.content(content).contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder);

		content = "{\"userName\":\"" + userName + "\",\"password\":\"password\"}";
		requestBuilder = MockMvcRequestBuilders.post("/authenticate").accept(MediaType.APPLICATION_JSON)
				.content(content).contentType(MediaType.APPLICATION_JSON);
		ResultActions action = mockMvc.perform(requestBuilder);
		MvcResult result = action.andReturn();

		JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
		token = jsonObject.getString("token");
		//System.out.println("Token = " + token);
		return token;
	}
}
