package com.sfeir.common.gwt.sample.moneyboard.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Widget;
import com.sfeir.common.gwt.sample.moneyboard.client.messages.I18n;

public class ImportTransactions extends DialogBox {

	private static ImportTransactionsUiBinder uiBinder = GWT.create(ImportTransactionsUiBinder.class);

	interface ImportTransactionsUiBinder extends UiBinder<Widget, ImportTransactions> {
	}

	public ImportTransactions() {
		super(false, true);
		setWidget(uiBinder.createAndBindUi(this));
		setGlassEnabled(true);
		setAnimationEnabled(true);
		setSize("400px", "250px");
		setText(msg.importOperation());
	}

	@UiField
	Button button;
	@UiField
	FormPanel form;
	@UiField
	I18n msg;
	@UiField
	Hidden account;

	@UiHandler("button")
	void onClick(ClickEvent e) {
		form.submit();
	}
	
	@UiHandler("form")
	void onSubmitComplete(SubmitCompleteEvent e) {
		hide();
	}

	public void setAccount(String rib) {
		account.setValue(rib);
	}
}
