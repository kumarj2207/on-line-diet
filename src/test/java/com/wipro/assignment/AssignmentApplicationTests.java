package com.wipro.assignment;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.DemoApplication;
import com.assignment.diet.controller.RegistrationController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
@AutoConfigureMockMvc
public class AssignmentApplicationTests {
	
	@Autowired
	private RegistrationController registrationController;

	@Test
	public void contextLoads() {
		assertThat(registrationController).isNotNull();
	}

}
