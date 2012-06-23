package com.sfeir.common.gwt.sample.moneyboard.server;

import javax.servlet.http.HttpSession;

import com.google.common.base.Strings;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sfeir.common.gwt.sample.moneyboard.server.service.DatastoreService;
import com.sfeir.common.gwt.sample.moneyboard.server.utils.Crypto;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.User;
import com.sfeir.common.gwt.sample.moneyboard.shared.services.UserService;
import com.sfeir.common.gwt.shared.exceptions.NotLoginException;

public class UserServiceImpl extends RemoteServiceServlet implements UserService {
	private static final long serialVersionUID = 7468192687197093304L;
	private static final String SALT = "SALTPASSWORD"+serialVersionUID;

	@Override
	public User login(String login, String password) throws NotLoginException {
		if (login != null && password != null && !login.isEmpty() && !password.isEmpty()) {
			String pass = Crypto.SHA1(password + login + SALT);
			User user = DatastoreService.ofy().find(User.class, login);
			if (user != null && pass.equals(user.getPassword())) {
				getThreadLocalRequest().getSession(true).setAttribute("context", user);
				user.setPassword(""); //Remove Password for send to the client
				return user;
			}
			else {
				throw new NotLoginException();
			}
		}
		throw new NotLoginException();
	}
	
	@Override
	public void register(User user) {
		if (!Strings.isNullOrEmpty(user.getEmail()) && !Strings.isNullOrEmpty(user.getPassword()) && !Strings.isNullOrEmpty(user.getPhone())) {
			user.setPassword(Crypto.SHA1(user.getPassword() + user.getEmail() + SALT)); //hash the password
			DatastoreService.ofy().put(user);
		}
		else {
			throw new RuntimeException("some field are missing ! ");
		}
	}

	@Override
	public void logout() throws NotLoginException {
		HttpSession session = getThreadLocalRequest().getSession();
		if (session == null)
			throw new NotLoginException();
		session.removeAttribute("context");
	}

}
