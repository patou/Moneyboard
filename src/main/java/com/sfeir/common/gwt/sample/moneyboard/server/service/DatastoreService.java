package com.sfeir.common.gwt.sample.moneyboard.server.service;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Account;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.BankBranch;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Operations;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.User;

public class DatastoreService {
	static {
		ObjectifyService.register(BankBranch.class);
		ObjectifyService.register(Account.class);
		ObjectifyService.register(User.class);
		ObjectifyService.register(Operations.class);
	}
	
	public static Objectify ofy() {
		return ObjectifyService.begin();
	}
}
