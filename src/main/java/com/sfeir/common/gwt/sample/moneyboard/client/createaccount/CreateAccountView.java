package com.sfeir.common.gwt.sample.moneyboard.client.createaccount;

import java.util.List;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sfeir.common.gwt.client.mvp.View;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Account;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.BankBranch;

public interface CreateAccountView extends View {
    void setPresenter(Presenter presenter);
    SimpleBeanEditorDriver<Account, ?> getDriver();
    void setListBank(List<BankBranch> listBank);
    public interface Presenter {

		void onSubmit();

		void onCancel();

		void loadBank(String code);
    }
}
