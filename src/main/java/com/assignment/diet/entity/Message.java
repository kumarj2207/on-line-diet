package com.assignment.diet.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Message {
	
	@Id
	@SequenceGenerator(name = "msg_seq", sequenceName = "msg_squence", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "msg_seq")
	private Long messageId;
	
	private String message;
	private Boolean read = Boolean.FALSE;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date postDate;
	
	@OneToOne
	private DietUser postedBy;
	
	@ManyToMany
	@JoinTable(name = "challenger_message", inverseJoinColumns = @JoinColumn(name = "challenger_id"), joinColumns = @JoinColumn(name = "message_id"))
	private List<Challenger> challengers = new ArrayList<>();

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public DietUser getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(DietUser postedBy) {
		this.postedBy = postedBy;
	}

	public List<Challenger> getChallengers() {
		return challengers;
	}

	public void setChallengers(List<Challenger> challengers) {
		this.challengers = challengers;
	}

	public Boolean getRead() {
		return read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}

}
