package com.assignment.diet.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Batch {

	@Id
	@SequenceGenerator(name = "bat_seq", sequenceName = "batch_squence", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bat_seq")
	private Long batchId;

	@Column(nullable = false)
	private String name;

	@JsonIgnore
	private Long parentBatchId;
	
	@JsonIgnore
	@OneToMany(mappedBy = "batch")
	private List<Challenger> challengers;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "batch_motivator", joinColumns = @JoinColumn(name = "batch_id"), inverseJoinColumns = @JoinColumn(name = "motivator_id"))
	private Set<Motivator> motivators = new HashSet<>();

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentBatchId() {
		return parentBatchId;
	}

	public void setParentBatchId(Long parentBatchId) {
		this.parentBatchId = parentBatchId;
	}

	public List<Challenger> getChallengers() {
		return challengers;
	}

	public void setChallengers(List<Challenger> challengers) {
		this.challengers = challengers;
	}

	public Set<Motivator> getMotivators() {
		return motivators;
	}

	public void setMotivators(Set<Motivator> motivators) {
		this.motivators = motivators;
	}

}
