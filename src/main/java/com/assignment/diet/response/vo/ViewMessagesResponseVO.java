package com.assignment.diet.response.vo;

import java.util.List;

public class ViewMessagesResponseVO {

	private List<ViewMessage> postedMessages;
	
	public void setPostedMessages(List<ViewMessage> postedMessages) {
		this.postedMessages = postedMessages;
	}

	public List<ViewMessage> getPostedMessages() {
		return postedMessages;
	}

}
