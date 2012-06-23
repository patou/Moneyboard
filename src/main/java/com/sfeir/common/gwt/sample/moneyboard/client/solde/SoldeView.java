package com.sfeir.common.gwt.sample.moneyboard.client.solde;

import java.util.List;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.visualization.client.DataTable;
import com.sfeir.common.gwt.client.mvp.View;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Account;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Operations;

public interface SoldeView extends View {
    void setPresenter(Presenter presenter);
    AcceptsOneWidget getListAccountPanel();
    void setOperationsList(List<Operations> list);
    void setChartDataTable(DataTable table);
    void setListAccount(List<Account> listAccount);
    public interface Presenter {

		void refresh();

		void updateTransaction(Operations object);

    }
}
