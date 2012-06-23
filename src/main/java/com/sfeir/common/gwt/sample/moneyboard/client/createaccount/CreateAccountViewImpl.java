package com.sfeir.common.gwt.sample.moneyboard.client.createaccount;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.client.ui.Widget;
import com.sfeir.common.gwt.client.component.label.LabelDoubleBox;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Account;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.BankBranch;

public class CreateAccountViewImpl extends Composite implements CreateAccountView, Editor<Account> {

	private static ViewUiBinder uiBinder = GWT.create(ViewUiBinder.class);

	interface ViewUiBinder extends UiBinder<Widget, CreateAccountViewImpl> {
	}
	@UiField(provided=true)
	@Ignore
	ValueListBox<BankBranch> listBank = new ValueListBox<BankBranch>(new AbstractRenderer<BankBranch>() {

		@Override
		public String render(BankBranch object) {
			if (object == null) {
				return "Selectionnez une banque";
			}
			return object.getNameBank() + " - " + object.getName();
		}
	});
	@UiField
	TextBox codeBank;
	@UiField
	TextBox codeBranch;
	@UiField
	TextBox codeAccount;
	@UiField
	TextBox ribKey;
	@UiField
	TextArea name;
	@UiField
	LabelDoubleBox currentAmount;
	
	
	private Presenter presenter;

	public CreateAccountViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	
	@Override
	public void setListBank(List<BankBranch> listBank) {
		listBank.add(0, null);
		this.listBank.setAcceptableValues(listBank);
	}
	
	@UiHandler("submit")
	public void onSubmit(ClickEvent e) {
		presenter.onSubmit();
	}
	
	@UiHandler("cancel")
	public void onCancel(ClickEvent e) {
		presenter.onCancel();
	}
	
	@UiHandler({"codeBank","codeBranch","codeAccount","ribKey"})
	public void moveToNextField(KeyUpEvent e) {
		TextBox tb = (TextBox) e.getSource();
		int numMax = tb.getMaxLength();
		int length = tb.getValue().length();
		if (length == numMax) {
			if (tb.equals(codeBank)) {
				codeBranch.setFocus(true);
				presenter.loadBank(codeBank.getText());
			}
			if (tb.equals(codeBranch)) {
				codeAccount.setFocus(true);
			}
			if (tb.equals(codeAccount)) {
				ribKey.setFocus(true);
			}
			if (tb.equals(ribKey)) {
				name.setFocus(true);
			}
		}
	}
	
	@UiHandler("listBank")
	public void onListBankChange(ValueChangeEvent<BankBranch> e) {
		BankBranch b = e.getValue();
		codeBank.setValue(b.getCodeBank());
		codeBranch.setValue(b.getCode());
		codeAccount.setFocus(true);
	}
	
	@Override
	public SimpleBeanEditorDriver<Account, ?> getDriver() {
		SimpleBeanEditorDriver<Account, CreateAccountViewImpl> driver = GWT.create(Driver.class);
		driver.initialize(this);
		return driver;
	}
	
	interface Driver extends SimpleBeanEditorDriver<Account, CreateAccountViewImpl>{}

}
