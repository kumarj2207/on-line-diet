package com.assignment.expense.entity;

import java.util.Date;

public class Expense {
	private String partyName;
	private Double amount;
	private String description;
	private Date date;
	private Mode mode;
    private short category;
    
    public Expense(String partyName, Double amount, String description, Date date, Mode mode, short category) {
		this.partyName = partyName;
		this.amount = amount;
		this.description = description;
		this.date = date;
		this.mode = mode;
		this.category = category;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public short getCategory() {
		return category;
	}

	public void setCategory(short category) {
		this.category = category;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

}
