package com.wipro.assignment.diet.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
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
import com.assignment.diet.controller.ChallengerController;
import com.assignment.diet.dao.ChallengerDao;
import com.assignment.diet.dao.DailyLogRepository;
import com.assignment.diet.dao.MeasurementDao;
import com.assignment.diet.dao.MessageDao;
import com.assignment.diet.entity.Challenger;
import com.assignment.diet.entity.Message;
import com.assignment.diet.response.vo.ViewMessagesResponseVO;
import com.assignment.diet.vo.DailyLogVO;
import com.assignment.diet.vo.MeasurementVO;
import com.assignment.diet.vo.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = { DemoApplication.class, ChallengerController.class })
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles({ "test.junit" })
public class ChallengerControllerTest {

	private String token;
	private UserDto user;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private ChallengerDao challengerDao;

	@MockBean
	private MessageDao messageDao;
	
	@MockBean
	private MeasurementDao measurementDao;

	@MockBean
	private DailyLogRepository dailyLogRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		when(challengerDao.findById(Mockito.anyLong())).thenReturn(Optional.of(new Challenger()));
		when(messageDao.findById(Mockito.anyLong())).thenReturn(Optional.of(new Message()));
	}

	@Test
	public void viewMessagesTest() throws Exception {
		String token = getJWTToken("admin", "admin@gmail.com");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/diet/viewmessages/{refId}", 2L)
				.accept(MediaType.APPLICATION_JSON).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON);

		ResultActions action = mockMvc.perform(requestBuilder);
		action.andExpect(status().isOk());

		MvcResult result = action.andReturn();
		ViewMessagesResponseVO vo = mapper.readValue(result.getResponse().getContentAsString(),
				ViewMessagesResponseVO.class);

		assertAll("Assert return body contains correct fields.", () -> {
			assertThat(vo).isNotNull();
			assertThat(vo.getPostedMessages().size()).isEqualTo(0);
		});

		MeasurementVO measurementVO = new MeasurementVO();
		measurementVO.setReferralId(2L);
		measurementVO.setBiceps(1.5F);
		measurementVO.setChest(1.5F);
		measurementVO.setForearms(1.5F);
		measurementVO.setHeight(180.5F);
		measurementVO.setLeg(1.5F);
		measurementVO.setShoulders(1.5F);
		measurementVO.setThighs(1.5F);
		measurementVO.setWaist(1.5F);
		measurementVO.setWeight(110.0F);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/diet/postmeasurement/2").accept(MediaType.APPLICATION_JSON)
				.header("Authorization", token).content(mapper.writeValueAsBytes(measurementVO))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
		
		DailyLogVO dailyLogVO = new DailyLogVO();
		dailyLogVO.setBreakfast("TEA");
		dailyLogVO.setFruitsAandVegetables("FRUITS");
		dailyLogVO.setWorkoutsDone("YES");
		dailyLogVO.setLunch("LUNCH");
		dailyLogVO.setDinner("Dinner");
		dailyLogVO.setSnack("Snack");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/diet/postdailylog/{refId}", 2L).accept(MediaType.APPLICATION_JSON)
				.header("Authorization", token).content(mapper.writeValueAsBytes(dailyLogVO))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
		
		mockMvc.perform(MockMvcRequestBuilders.post("/diet/setmessageasread/{msgId}", 2L).accept(MediaType.APPLICATION_JSON)
				.header("Authorization", token).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
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
