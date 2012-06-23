package com.sfeir.common.gwt.sample.moneyboard.shared.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.User;

public interface UserServiceAsync {

    void login(String login, String user, AsyncCallback<User> callback);

    void logout(AsyncCallback<Void> callback);

	void register(User user, AsyncCallback<Void> callback);

}
