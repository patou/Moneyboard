package com.sfeir.common.gwt.sample.moneyboard.client.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.sfeir.common.gwt.client.component.event.KeyEnterPressEvent;
import com.sfeir.common.gwt.client.component.label.LabelPasswordBox;
import com.sfeir.common.gwt.client.component.label.LabelTextBox;
import com.sfeir.common.gwt.client.resource.BluePrint;
import com.sfeir.common.gwt.sample.moneyboard.client.resource.Resources;

public class LoginViewImpl extends Composite implements LoginView {

	private static ViewUiBinder uiBinder = GWT.create(ViewUiBinder.class);

	interface ViewUiBinder extends UiBinder<Widget, LoginViewImpl> {
	}

	@UiField
	LabelTextBox login;
	@UiField
	LabelPasswordBox password;
	@UiField
	Button submit;
	@UiField
	Button register;

	private Presenter presenter;

	public LoginViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("submit")
	void onSubmit(ClickEvent e) {
		presenter.onSubmit();
	}
	@UiHandler("register")
	void onRegister(ClickEvent e) {
		presenter.onRegister();
	}

	 @UiHandler({"login","password"})
	void onSubmit(KeyEnterPressEvent e) {
		presenter.onSubmit();
	}

	@Override
	public void setErrorState(boolean isError) {
		if (isError) {
			//login.addStyleName(BluePrint.INSTANCE.style().error());
			login.addStyleName(Resources.INSTANCE.style().inputError());
			//password.addStyleName(BluePrint.INSTANCE.style().error());
			password.addStyleName(Resources.INSTANCE.style().inputError());
		} else {
			//login.removeStyleName(BluePrint.INSTANCE.style().error());
			login.removeStyleName(Resources.INSTANCE.style().inputError());
			//password.removeStyleName(BluePrint.INSTANCE.style().error());
			password.removeStyleName(Resources.INSTANCE.style().inputError());
		}
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		login.setFocus(true);
	}

	@Override
	public String getLoginValue() {
		return login.getValue();
	}

	@Override
	public String getPasswordValue() {
		return password.getValue();
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
}
