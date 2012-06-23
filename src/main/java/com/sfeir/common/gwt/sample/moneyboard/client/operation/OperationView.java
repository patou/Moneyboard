package com.sfeir.common.gwt.sample.moneyboard.client.operation;

import java.util.List;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.visualization.client.DataTable;
import com.sfeir.common.gwt.client.mvp.View;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Account;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Operations;

public interface OperationView extends View {
    void setPresenter(Presenter presenter);
    AcceptsOneWidget getListAccountPanel();
    void setOperationsList(List<Operations> list);
    void setListAccount(List<Account> listAccount);
    void setCurrentAccount(String accountRib);
    void setChartCategoryTable(DataTable tableCategory);
    public interface Presenter {

		void refresh();

		void updateTransaction(Operations object);

    }
}
