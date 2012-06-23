package com.sfeir.common.gwt.sample.moneyboard.client.register;

import com.google.common.base.Strings;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.sfeir.common.gwt.client.mvp.ActivityPresenter;
import com.sfeir.common.gwt.sample.moneyboard.client.Moneyboard;
import com.sfeir.common.gwt.sample.moneyboard.client.messages.I18n;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.User;
import com.sfeir.common.gwt.sample.moneyboard.shared.services.UserService;
import com.sfeir.common.gwt.sample.moneyboard.shared.services.UserServiceAsync;
import com.sfeir.common.gwt.shared.exceptions.NotLoginException;

public class RegisterActivity extends ActivityPresenter<RegisterPlace> implements RegisterView.Presenter {
	RegisterView view;

	SimpleBeanEditorDriver<User, ?> driver;

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = getClientFactory().getView(RegisterView.class);
		view.setPresenter(this);
		driver = view.getDriver();
		User user = new User();
		user.setEmail(getPlace().getEmail());
		driver.edit(user);
		getClientFactory().setUserData(null);
		panel.setWidget(view);
	}
	
	@Override
	public void onSubmit() {
		User user = driver.flush();
		if (Strings.isNullOrEmpty(user.getEmail()) || Strings.isNullOrEmpty(user.getPassword()) || Strings.isNullOrEmpty(user.getPhone())) {
			setErrorMessage(getClientFactory().getMessage(I18n.class).errorLoginOrPassword());
			view.setErrorState(true);
		} else {
			setErrorMessage(null);
			view.setErrorState(false);
			setLoading(true);
			getClientFactory().getService(UserService.class, UserServiceAsync.class).register(user, new AsyncCallback<Void>() {

				@Override
				public void onSuccess(Void result) {
					goTo(Moneyboard.defaultPlace);
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
}
