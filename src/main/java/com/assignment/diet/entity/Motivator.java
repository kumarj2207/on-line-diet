package com.assignment.diet.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class Motivator {
	@Id
	private Long referralId;

	@ManyToMany
	@JoinTable(
			name = "batch_motivator", 
			joinColumns = @JoinColumn(name = "motivator_id"), 
			inverseJoinColumns = @JoinColumn(name = "batch_id")
	)
	private Set<Batch> batches = new HashSet<>();

	public Set<Batch> getBatches() {
		return batches;
	}

	public void setBatches(Set<Batch> batches) {
		this.batches = batches;
	}

	public Long getReferralId() {
		return referralId;
	}

	public void setReferralId(Long referralId) {
		this.referralId = referralId;
	}

}
