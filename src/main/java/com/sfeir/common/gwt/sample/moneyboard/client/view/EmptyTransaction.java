package com.sfeir.common.gwt.sample.moneyboard.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class EmptyTransaction extends Composite implements HasClickHandlers {

	private static EmptyTransactionUiBinder uiBinder = GWT.create(EmptyTransactionUiBinder.class);

	interface EmptyTransactionUiBinder extends UiBinder<Widget, EmptyTransaction> {
	}

	public EmptyTransaction() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Button button;
	
	@UiHandler("button")
	public void onClick(ClickEvent e) {
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		GWT.log("addClickHandler");
		return button.addClickHandler(handler);
	}
}
