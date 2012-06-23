package com.sfeir.common.gwt.sample.moneyboard.client.register;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
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
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.User;

public class RegisterViewImpl extends Composite implements RegisterView, Editor<User> {

	private static ViewUiBinder uiBinder = GWT.create(ViewUiBinder.class);

	interface ViewUiBinder extends UiBinder<Widget, RegisterViewImpl> {
	}

	@UiField
	LabelTextBox email;
	@UiField
	LabelTextBox firstname;
	@UiField
	LabelTextBox lastname;
	@UiField
	LabelTextBox phone;
	@UiField
	LabelPasswordBox password;
	@UiField
	Button submit;

	private Presenter presenter;

	public RegisterViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("submit")
	void onSubmit(ClickEvent e) {
		presenter.onSubmit();
	}

	 @UiHandler({"email","password"})
	void onSubmit(KeyEnterPressEvent e) {
		presenter.onSubmit();
	}

	@Override
	public void setErrorState(boolean isError) {
		if (isError) {
			email.addStyleName(BluePrint.INSTANCE.style().error());
			email.addStyleName(Resources.INSTANCE.style().inputError());
			password.addStyleName(BluePrint.INSTANCE.style().error());
			password.addStyleName(Resources.INSTANCE.style().inputError());
		} else {
			email.removeStyleName(BluePrint.INSTANCE.style().error());
			email.removeStyleName(Resources.INSTANCE.style().inputError());
			password.removeStyleName(BluePrint.INSTANCE.style().error());
			password.removeStyleName(Resources.INSTANCE.style().inputError());
		}
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		email.setFocus(true);
	}

	@Override
	public String getLoginValue() {
		return email.getValue();
	}

	@Override
	public String getPasswordValue() {
		return password.getValue();
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public SimpleBeanEditorDriver<User, ?> getDriver() {
		SimpleBeanEditorDriver<User, RegisterViewImpl> driver = GWT.create(Driver.class);
		driver.initialize(this);
		return driver;
	}
	
	interface Driver extends SimpleBeanEditorDriver<User, RegisterViewImpl>{}
}
