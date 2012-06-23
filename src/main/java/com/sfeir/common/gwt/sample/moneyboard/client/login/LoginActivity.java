package com.sfeir.common.gwt.sample.moneyboard.client.login;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.sfeir.common.gwt.client.mvp.ActivityPresenter;
import com.sfeir.common.gwt.client.place.DefaultPlace;
import com.sfeir.common.gwt.sample.moneyboard.client.messages.I18n;
import com.sfeir.common.gwt.sample.moneyboard.client.register.RegisterPlace;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.User;
import com.sfeir.common.gwt.sample.moneyboard.shared.services.UserService;
import com.sfeir.common.gwt.sample.moneyboard.shared.services.UserServiceAsync;
import com.sfeir.common.gwt.shared.exceptions.NotLoginException;

public class LoginActivity extends ActivityPresenter<DefaultPlace> implements LoginView.Presenter {
	LoginView view;

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = getClientFactory().getView(LoginView.class);
		view.setPresenter(this);
		getClientFactory().setUserData(null);
		panel.setWidget(view);
	}

	@Override
	public void onSubmit() {
		String login = view.getLoginValue();
		String password = view.getPasswordValue();
		if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
			setErrorMessage(getClientFactory().getMessage(I18n.class).errorLoginOrPassword());
			view.setErrorState(true);
		} else {
			setErrorMessage(null);
			view.setErrorState(false);
			setLoading(true);
			getClientFactory().getService(UserService.class, UserServiceAsync.class).login(login, password, new AsyncCallback<User>() {

				@Override
				public void onSuccess(User result) {
					getClientFactory().setUserData(result);
					goTo(getPlace().getSuccessLoginPlace());
				}

				@Override
				public void onFailure(Throwable caught) {
					if (caught instanceof NotLoginException) {
						setErrorMessage(getClientFactory().getMessage(I18n.class).errorLoginOrPassword());
						view.setErrorState(true);
					}
					setLoading(false);
				}
			});
		}
	}
	
	@Override
	public void onRegister() {
		goTo(new RegisterPlace(view.getLoginValue()));
	}

}
