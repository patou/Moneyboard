package com.sfeir.common.gwt.sample.moneyboard.client.createaccount;

import java.util.List;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.geolocation.client.Geolocation;
import com.google.gwt.geolocation.client.Position;
import com.google.gwt.geolocation.client.PositionError;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.sfeir.common.gwt.client.mvp.ActivityPresenter;
import com.sfeir.common.gwt.sample.moneyboard.client.messages.I18n;
import com.sfeir.common.gwt.sample.moneyboard.client.operation.OperationPlace;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Account;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.BankBranch;
import com.sfeir.common.gwt.sample.moneyboard.shared.exception.RibNotValid;
import com.sfeir.common.gwt.sample.moneyboard.shared.services.AccountService;
import com.sfeir.common.gwt.sample.moneyboard.shared.services.AccountServiceAsync;

public class CreateAccountActivity extends ActivityPresenter<CreateAccountPlace> implements CreateAccountView.Presenter {
	CreateAccountView view;

	SimpleBeanEditorDriver<Account, ?> driver;

	private AccountServiceAsync service;

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = getClientFactory().getView(CreateAccountView.class);
		view.setPresenter(this);
		service = getClientFactory().getService(AccountService.class, AccountServiceAsync.class);
		driver = view.getDriver();
		driver.edit(new Account());
		panel.setWidget(view);
		Geolocation geo = Geolocation.getIfSupported();
		if (geo != null) {
			geo.getCurrentPosition(new Callback<Position, PositionError>() {
				
				@Override
				public void onSuccess(Position result) {
					service.getListBankNearUser(result.getCoordinates().getLatitude(), result.getCoordinates().getLongitude(), createAsync(new AsyncCallback<List<BankBranch>>() {

						@Override
						public void onFailure(Throwable caught) {
							GWT.log("", caught);
						}

						@Override
						public void onSuccess(List<BankBranch> listBank) {
							view.setListBank(listBank);
						}
					}));
				}
				
				@Override
				public void onFailure(PositionError reason) {
					GWT.log("", reason);
				}
			});
		}
	}
	
	@Override
	public void onSubmit() {
		GWT.log("onSubmit");
		setLoading(true);
		service.addAccount(driver.flush(), createAsync(new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				goTo(new OperationPlace());
				setLoading(false);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				if (caught instanceof RibNotValid)
					setErrorMessage(getClientFactory().getMessage(I18n.class).ribNotValid());
				GWT.log("", caught);
				setLoading(false);
			}
		}));
	}
	
	@Override
	public void loadBank(String code) {
		service.getListBank(code, createAsync(new AsyncCallback<List<BankBranch>>() {

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("", caught);
			}

			@Override
			public void onSuccess(List<BankBranch> listBank) {
				view.setListBank(listBank);
			}
		}));
	}
	
	@Override
	public void onCancel() {
		goTo(new OperationPlace());
	}
}
