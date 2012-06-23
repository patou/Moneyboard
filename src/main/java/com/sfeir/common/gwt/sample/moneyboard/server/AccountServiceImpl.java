package com.sfeir.common.gwt.sample.moneyboard.server;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.beoui.geocell.GeocellManager;
import com.beoui.geocell.ObjectifyGeocellQueryEngine;
import com.beoui.geocell.model.GeocellQuery;
import com.beoui.geocell.model.Point;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.sfeir.common.gwt.sample.moneyboard.server.service.DatastoreService;
import com.sfeir.common.gwt.sample.moneyboard.server.utils.Validation;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Account;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.BankBranch;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.User;
import com.sfeir.common.gwt.sample.moneyboard.shared.exception.RibNotValid;
import com.sfeir.common.gwt.sample.moneyboard.shared.services.AccountService;
import com.sfeir.common.gwt.shared.exceptions.NotAccessAllowedException;

public class AccountServiceImpl extends RemoteServiceServlet implements AccountService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8986303173308288048L;

	@Override
	public List<BankBranch> getListBank(String codeBank) {
		List<BankBranch> list = DatastoreService.ofy().query(BankBranch.class).filter("codeBank", codeBank).list();
		return list;
	}
	
	@Override
	public List<BankBranch> getListBankNearUser(Double lat, Double lng) {
		log(lat + ":" + lng);
		List<BankBranch> proximitySearch = GeocellManager.proximitySearch(new Point(lat, lng), 10, 10000, BankBranch.class, new GeocellQuery(), new ObjectifyGeocellQueryEngine(DatastoreService.ofy()), 6);
		return proximitySearch;
	}
	
	@Override
	public void addAccount(Account account) throws RibNotValid, NotAccessAllowedException {
		log("addAccount");
		HttpSession session = getThreadLocalRequest().getSession();
		if (session == null)
			throw new NotAccessAllowedException();
		User user = (User) session.getAttribute("context");
		if (user == null)
			throw new NotAccessAllowedException();
		String rib = account.createRib();
		log(rib + " " + Validation.checkRib(rib));
//		if (!Validation.checkRib(rib)) {
//			throw new RibNotValid();
//		}
		Objectify ofy = DatastoreService.ofy();
		if (account.getIdBank() == null || account.getIdBank() == 0L) {
			List<Key<BankBranch>> listKeys = ofy.query(BankBranch.class).filter("codeBank =", account.getCodeBank()).filter("code =", account.getCodeBranch()).limit(1).listKeys();
			if (listKeys != null && listKeys.size() > 0) {
				account.setIdBank(listKeys.get(0).getId());
			}
		}
		account.setUser(new Key<User>(User.class, user.getEmail()));
		ofy.put(account);
	}
	
	@Override
	public List<Account> getAccountUserList() throws NotAccessAllowedException {
		HttpSession session = getThreadLocalRequest().getSession();
		if (session == null)
			throw new NotAccessAllowedException();
		User user = (User) session.getAttribute("context");
		if (user == null)
			throw new NotAccessAllowedException();
		Objectify ofy = DatastoreService.ofy();
		List<Account> list = ofy.query(Account.class).ancestor(user).list();
		for (Account account : list) {
			if (account.getIdBank() != null) {
				BankBranch bank = ofy.get(BankBranch.class,account.getIdBank());
				account.setBank(bank);
			}
		}
		return list;
	}
	
}
