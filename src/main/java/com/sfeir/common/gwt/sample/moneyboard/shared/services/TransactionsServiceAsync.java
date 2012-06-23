package com.sfeir.common.gwt.sample.moneyboard.shared.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.objectify.Key;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Account;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Operations;



public interface TransactionsServiceAsync {
	void getOperationList(AsyncCallback<List<Operations>> callback);

	void getOperationList(Key<Account> account, AsyncCallback<List<Operations>> callback);

	void getOperationList(String rib, AsyncCallback<List<Operations>> callback);

	void updateTransaction(Operations operation, AsyncCallback<Void> callback);

	void getOperationList(Boolean displayOnlyRecette, AsyncCallback<List<Operations>> callback);
}
