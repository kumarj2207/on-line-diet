package com.assignment.diet.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Challenger {
	@Id
	private Long referralId;

	@ManyToOne
	@JoinColumn(name = "batch")
	private Batch batch;

	@OneToMany(mappedBy = "challenger")
	private List<DailyLog> logs = new ArrayList<>();

	@OneToMany(mappedBy = "challenger", cascade = CascadeType.ALL)
	private List<Measurement> measurement = new ArrayList<>();

	@ManyToMany(mappedBy = "challengers")
	private List<Message> messages = new ArrayList<>();;

	public Long getReferralId() {
		return referralId;
	}

	public void setReferralId(Long referralId) {
		this.referralId = referralId;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public List<DailyLog> getLogs() {
		return logs;
	}

	public void setLogs(List<DailyLog> logs) {
		this.logs = logs;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public List<Measurement> getMeasurement() {
		return measurement;
	}

	public void setMeasurement(List<Measurement> measurement) {
		this.measurement = measurement;
	}

}
