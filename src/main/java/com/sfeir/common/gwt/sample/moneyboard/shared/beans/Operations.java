package com.sfeir.common.gwt.sample.moneyboard.shared.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Operations implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4049665750770893609L;
	@Id
	Long id;
	@Parent
	Key<Account> account;
	String description;
	Date date;
	Double amount;
	String note;
	String category;
    String clearedStatus;
    String num;
    String payee;
	
	public Operations() {
	}

	public Operations(String description, Date date, Double amount, String note, String category, String clearedStatus, String num, String payee) {
		super();
		this.description = description;
		this.date = date;
		this.amount = amount;
		this.note = note;
		this.category = category;
		this.clearedStatus = clearedStatus;
		this.num = num;
		this.payee = payee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Key<Account> getAccount() {
		return account;
	}

	public void setAccount(Key<Account> account) {
		this.account = account;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getClearedStatus() {
		return clearedStatus;
	}

	public void setClearedStatus(String clearedStatus) {
		this.clearedStatus = clearedStatus;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}
}
