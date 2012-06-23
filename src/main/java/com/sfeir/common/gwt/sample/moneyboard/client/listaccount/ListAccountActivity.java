package com.sfeir.common.gwt.sample.moneyboard.client.listaccount;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.sfeir.common.gwt.client.mvp.ActivityPresenter;
import com.sfeir.common.gwt.sample.moneyboard.client.createaccount.CreateAccountPlace;
import com.sfeir.common.gwt.sample.moneyboard.client.events.ListAccountEventLoaded;
import com.sfeir.common.gwt.sample.moneyboard.client.events.SelectAccountsEvent;
import com.sfeir.common.gwt.sample.moneyboard.client.listaccount.ListAccountView.Presenter;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Account;
import com.sfeir.common.gwt.sample.moneyboard.shared.services.AccountService;
import com.sfeir.common.gwt.sample.moneyboard.shared.services.AccountServiceAsync;

public class ListAccountActivity extends ActivityPresenter<ListAccountPlace> implements Presenter {

	private ListAccountView view;
	private List<Account> listAccount;
	private EventBus eventBus;

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.eventBus = eventBus;
		view = getClientFactory().getView(ListAccountView.class);
		view.setPresenter(this);
		panel.setWidget(view);
		loadListAccount();
	}

	public void loadListAccount() {
		getClientFactory().getService(AccountService.class, AccountServiceAsync.class).getAccountUserList(createAsync(new AsyncCallback<List<Account>>() {


			@Override
			public void onSuccess(List<Account> result) {
				listAccount = result;
				view.setListAccount(result, getPlace().getSelectedAccount());
				eventBus.fireEvent(new ListAccountEventLoaded(result));
			}

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("", caught);
			}
		}));
	}

	@Override
	public void onCreateNewAccount() {
		goTo(new CreateAccountPlace());
	}
	
	@Override
	public void onSelectAccount(List<String> accounts) {
//		if (rib != null && !rib.equals(getPlace().getSelectedAccount())) {
//			goTo(new OperationPlace(rib));
//		}
		eventBus.fireEvent(new SelectAccountsEvent(accounts));
	}

	public List<Account> getListAccount() {
		return listAccount;
	}
}
