package com.sfeir.common.gwt.sample.moneyboard.client.listaccount;

import java.util.List;

import com.sfeir.common.gwt.client.mvp.View;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Account;

public interface ListAccountView extends View {
    void setPresenter(Presenter presenter);
    void setListAccount(List<Account> result, String selectedAccount);
    public interface Presenter {
		void onCreateNewAccount();

		void onSelectAccount(List<String> selectedAccounts);
    }
}
