package com.sfeir.common.gwt.sample.moneyboard.server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.QueryResultIterator;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.sfeir.common.gwt.sample.moneyboard.server.service.DatastoreService;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Account;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Operations;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Referentiels;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.User;
import com.sfeir.common.gwt.sample.moneyboard.shared.services.TransactionsService;
import com.sfeir.common.gwt.shared.exceptions.NotAccessAllowedException;

public class TransactionsServiceImpl extends RemoteServiceServlet implements TransactionsService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5660198174875975651L;
	
	@Override
	public List<Operations> getOperationList() throws NotAccessAllowedException {
		HttpSession session = getThreadLocalRequest().getSession();
		if (session == null)
			throw new NotAccessAllowedException();
		User user = (User) session.getAttribute("context");
		if (user == null)
			throw new NotAccessAllowedException();
		Objectify ofy = DatastoreService.ofy();
//		List<Key<Account>> listAccount = ofy.query(Account.class).ancestor(user).listKeys();
//		return generateOperationList((Key<Account>[]) listAccount.toArray(new Key<?>[]{}));
		List<Operations> list = ofy.query(Operations.class).ancestor(user).limit(50).order("-date").list();
		return list;
	}


	

	@Override
	public List<Operations> getOperationList(Key<Account> account) throws NotAccessAllowedException {
		HttpSession session = getThreadLocalRequest().getSession();
		if (session == null)
			throw new NotAccessAllowedException();
		User user = (User) session.getAttribute("context");
		if (user == null)
			throw new NotAccessAllowedException();
		Key<Account>[] arrayAccount = (Key<Account>[]) new Key<?>[]{account};
		//return generateOperationList(arrayAccount);
		Objectify ofy = DatastoreService.ofy();
		List<Operations> list = ofy.query(Operations.class).ancestor(account).limit(50).order("-date").list();
		return list;
	}
	
	@Override
	public List<Operations> getOperationList(String rib) throws NotAccessAllowedException {
		HttpSession session = getThreadLocalRequest().getSession();
		if (session == null)
			throw new NotAccessAllowedException();
		User user = (User) session.getAttribute("context");
		if (user == null)
			throw new NotAccessAllowedException();
		Key<Account> key = new Key<Account>(new Key<User>(User.class, user.getEmail()), Account.class, rib);
		return getOperationList(key);
	}
	
	@Override
	public List<Operations> getOperationList(Boolean displayOnlyRecette) throws NotAccessAllowedException {
		HttpSession session = getThreadLocalRequest().getSession();
		if (session == null)
			throw new NotAccessAllowedException();
		User user = (User) session.getAttribute("context");
		if (user == null)
			throw new NotAccessAllowedException();
		Objectify ofy = DatastoreService.ofy();
//		List<Key<Account>> listAccount = ofy.query(Account.class).ancestor(user).listKeys();
//		return generateOperationList((Key<Account>[]) listAccount.toArray(new Key<?>[]{}));
		List<Operations> list = new ArrayList<Operations>();
		QueryResultIterator<Operations> iterator = ofy.query(Operations.class).ancestor(user).limit(50).order("-date").iterator();
		while (iterator.hasNext()) {
			Operations operations = (Operations) iterator.next();
			Double amount = operations.getAmount();
			if (amount == null)
				continue;
			if ((displayOnlyRecette == null) || (displayOnlyRecette && amount >= 0) || (!displayOnlyRecette && amount < 0))
				list.add(operations);
		}
		return list;
	}
	
	@Override
	public void updateTransaction(Operations operation) throws NotAccessAllowedException {
		Objectify ofy = DatastoreService.ofy();
		ofy.put(operation);
	}

	public void importOperations(String account, User user) throws NotAccessAllowedException {
		List<Operations> list = new ArrayList<Operations>(50);
		Key<Account> key = new Key<Account>(new Key<User>(User.class, user.getEmail()), Account.class, account);
		Key<Account>[] arrayAccount = (Key<Account>[]) new Key<?>[]{key};
		for (int i = 0; i < 50; i++) {
			Operations op = generateOperations(i, arrayAccount);
			list.add(op);
		}
		Objectify ofy = DatastoreService.ofy();
		ofy.put(list);
	}
	
	public List<Operations> generateOperationList(Key<Account>[] account) {
		List<Operations> list = new ArrayList<Operations>();
		for (int i = 0; i < 50; i++) {
			list.add(generateOperations(i, account));
		}
		return list;
	}

	private Operations generateOperations(int i, Key<Account> account[]) {
		Random random = new Random();
		Double amount = round2(random.nextInt(350) - random.nextInt(1000) + random.nextDouble());
		if (amount > 80.0) {
			amount += random.nextInt(5000);
		}
		Operations operations = new Operations();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, - (i * 24  +  random.nextInt( 48)));
		operations.setDate(calendar.getTime());
		operations.setAmount(amount);
		operations.setDescription("DESCRIPTION " + i);
		operations.setNote("");
		operations.setPayee("payee " + i);
		if (account != null && account.length > 0)
			operations.setAccount(account[random.nextInt(account.length)]);
		if (amount > 0) {
			operations.setCategory(Referentiels.categoryRecette[random.nextInt(Referentiels.categoryRecette.length)]);
			operations.setDescription(Referentiels.descriptionRecette[random.nextInt(Referentiels.descriptionRecette.length)]);
		}
		else {
			operations.setCategory(Referentiels.categoryDepense[random.nextInt(Referentiels.categoryDepense.length)]);
			operations.setDescription(Referentiels.descriptionDepense[random.nextInt(Referentiels.descriptionDepense.length)]);
		}
		operations.setNum(Integer.toString(i));
		return operations;
	}
	
	public static double round2(double value) {
		double result = value * 100;
		result = Math.round(result);
		result = result / 100;
		return result;
		}


}
