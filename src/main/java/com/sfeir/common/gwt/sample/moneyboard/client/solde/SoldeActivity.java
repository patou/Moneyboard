package com.sfeir.common.gwt.sample.moneyboard.client.solde;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.sfeir.common.gwt.client.mvp.ActivityGroup;
import com.sfeir.common.gwt.sample.moneyboard.client.events.ListAccountEventLoaded;
import com.sfeir.common.gwt.sample.moneyboard.client.events.SelectAccountsEvent;
import com.sfeir.common.gwt.sample.moneyboard.client.listaccount.ListAccountActivity;
import com.sfeir.common.gwt.sample.moneyboard.client.listaccount.ListAccountPlace;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Account;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Operations;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.User;
import com.sfeir.common.gwt.sample.moneyboard.shared.services.TransactionsService;
import com.sfeir.common.gwt.sample.moneyboard.shared.services.TransactionsServiceAsync;
import com.sfeir.common.gwt.sample.moneyboard.shared.utils.Filters;

public class SoldeActivity extends ActivityGroup<SoldePlace> implements SoldeView.Presenter, ListAccountEventLoaded.Handler, SelectAccountsEvent.Handler {
	SoldeView view;

	SimpleBeanEditorDriver<User, ?> driver;

	private ListAccountActivity listAccountActivity;
	private List<Operations> listOperations;

	private List<Account> listAccount;

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);
		view = getClientFactory().getView(SoldeView.class);
		view.setPresenter(this);
		panel.setWidget(view);
		listAccountActivity = (ListAccountActivity) loadActivity(new ListAccountPlace());
		attachActivity(listAccountActivity, view.getListAccountPanel());
		loadTransactions();
		eventBus.addHandler(ListAccountEventLoaded.TYPE, this);
		eventBus.addHandler(SelectAccountsEvent.TYPE, this);
	}

	@Override
	public void onSelectAccounts(SelectAccountsEvent e) {
		List<String> list = e.getSelectedAccount();
		loadOperations(Filters.filterOperationsList(listOperations, list));		
	}
	
	public void loadTransactions() {
		getClientFactory().getService(TransactionsService.class, TransactionsServiceAsync.class).getOperationList(createAsync(new AsyncCallback<List<Operations>>() {
			

			@Override
			public void onSuccess(List<Operations> list) {
				listOperations = list;
				loadOperations(list);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("", caught);
			}
		}));
	}

	@Override
	public void onListAccountLoaded(ListAccountEventLoaded e) {
		this.listAccount = e.getListAccount();
		GWT.log("onListAccountLoaded");
		loadOperations(listOperations);
	}

	private void loadOperations(List<Operations> listOperations) {
		GWT.log("loadChart");
		if (listAccount != null && listOperations != null) {
			view.setListAccount(listAccount);
			view.setOperationsList(listOperations);
			double total = 0.0;
			for (Account account : listAccount) {
				if (account != null) {
					Double currentAmount = account.getCurrentAmount();
					if (currentAmount != null)
						total += currentAmount;
				}
			}
			GWT.log("total="+total);
			DataTable table = DataTable.create();
			table.addColumn(ColumnType.DATE, "Date");
			table.addColumn(ColumnType.NUMBER, "Solde");
			int rowIndex = listOperations.size();
			table.addRows(rowIndex + 1);
			table.setValue(rowIndex, 0, new Date());
			table.setValue(rowIndex, 1, total);
			//GWT.log(rowIndex+":"+new Date() +"-"+total);
			for (Operations op : listOperations) {
				total -= op.getAmount();
				table.setValue(rowIndex, 0, op.getDate());
				table.setValue(rowIndex, 1, total);
				//GWT.log(rowIndex+":"+op.getDate() +"-"+total);
				rowIndex--;
			}
			view.setChartDataTable(table);
		}
	}
	
	@Override
	public void refresh() {
		loadTransactions();
	}

	@Override
	public void updateTransaction(Operations object) {
		getClientFactory().getService(TransactionsService.class, TransactionsServiceAsync.class).updateTransaction(object, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("", caught);
			}

			@Override
			public void onSuccess(Void result) {
				
			}
		});
	}
}
