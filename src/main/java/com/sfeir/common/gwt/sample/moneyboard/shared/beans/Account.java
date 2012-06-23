package com.sfeir.common.gwt.sample.moneyboard.shared.beans;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.PrePersist;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.NotSaved;
import com.googlecode.objectify.annotation.Parent;

public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4049665750770893609L;
	@Id
	String rib;
	@Parent
	Key<User> user;
	Long idBank;
	String codeBank;
	String codeBranch;
	String codeAccount;
	String ribKey;
	String name;
	Double currentAmount;
	@NotSaved
	BankBranch bank;
	
	public Account() {
	}
	
	public String getRib() {
		return rib;
	}
	public void setRib(String rib) {
		this.rib = rib;
	}
	public Long getIdBank() {
		return idBank;
	}
	public void setIdBank(Long idBank) {
		this.idBank = idBank;
	}
	public String getCodeBank() {
		return codeBank;
	}
	public void setCodeBank(String codeBank) {
		this.codeBank = codeBank;
	}
	public String getCodeBranch() {
		return codeBranch;
	}
	public void setCodeBranch(String codeBranch) {
		this.codeBranch = codeBranch;
	}
	public String getCodeAccount() {
		return codeAccount;
	}
	public void setCodeAccount(String codeAccount) {
		this.codeAccount = codeAccount;
	}
	public String getRibKey() {
		return ribKey;
	}
	public void setRibKey(String ribKey) {
		this.ribKey = ribKey;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Key<User> getUser() {
		return user;
	}
	public void setUser(Key<User> user) {
		this.user = user;
	}
	
	public void setBank(BankBranch bank) {
		this.bank = bank;
	}
	
	public BankBranch getBank() {
		return bank;
	}
	
	public Double getCurrentAmount() {
		if (currentAmount == null)
			return 0.0;
		return currentAmount;
	}
	
	public void setCurrentAmount(Double currentAmount) {
		this.currentAmount = currentAmount;
	}
	
	@PrePersist
	public void prePresist() {
		if (rib == null)
			rib = createRib();
		if (idBank == null) {
			
		}
	}
	public String createRib() {
		rib = new StringBuilder(codeBank).append(codeBranch).append(codeAccount).append(ribKey).toString();
		return rib;
	}
}
