package com.sfeir.common.gwt.sample.moneyboard.shared.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Account;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.BankBranch;


public interface AccountServiceAsync {
	void getListBank(String codeBank, AsyncCallback<List<BankBranch>> callback);
	void getListBankNearUser(Double lat, Double lng, AsyncCallback<List<BankBranch>> callback);
	void addAccount(Account account, AsyncCallback<Void> callback);
	void getAccountUserList(AsyncCallback<List<Account>> callback);
}
