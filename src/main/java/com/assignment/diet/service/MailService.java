package com.assignment.diet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMessage(String mailTo, String message) {
		Thread thread = new DietThread(mailTo, message);
		thread.start();
	}
	
	public class DietThread extends Thread {

		private String mailTo;
		private String message;

		public DietThread(String mailTo, String message) {
			this.mailTo = mailTo;
			this.message = message;
		}

		@Override
		public void run() {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setTo(mailTo);
			simpleMailMessage.setFrom("mydietadmin@mydiet.com");
			simpleMailMessage.setSubject("Registration result.");
			simpleMailMessage.setText(message);
			javaMailSender.send(simpleMailMessage);
		}

	}
}
