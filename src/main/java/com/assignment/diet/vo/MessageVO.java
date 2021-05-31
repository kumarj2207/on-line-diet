package com.assignment.diet.vo;

import java.util.ArrayList;
import java.util.List;

public class MessageVO {

	private String message;
	private List<Long> referralIds = new ArrayList<>();
	private Long postedBy;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Long> getReferralIds() {
		return referralIds;
	}

	public void setReferralIds(List<Long> referralIds) {
		this.referralIds = referralIds;
	}

	public Long getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(Long postedBy) {
		this.postedBy = postedBy;
	}

}
