package com.sfeir.common.gwt.sample.moneyboard.client.operation;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;
import com.sfeir.common.gwt.client.mvp.ActivityGroup;
import com.sfeir.common.gwt.sample.moneyboard.client.events.ListAccountEventLoaded;
import com.sfeir.common.gwt.sample.moneyboard.client.events.SelectAccountsEvent;
import com.sfeir.common.gwt.sample.moneyboard.client.listaccount.ListAccountPlace;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Account;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Operations;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Referentiels;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.User;
import com.sfeir.common.gwt.sample.moneyboard.shared.services.TransactionsService;
import com.sfeir.common.gwt.sample.moneyboard.shared.services.TransactionsServiceAsync;
import com.sfeir.common.gwt.sample.moneyboard.shared.utils.Filters;

public class OperationActivity extends ActivityGroup<OperationPlace> implements OperationView.Presenter, ListAccountEventLoaded.Handler, SelectAccountsEvent.Handler {
	OperationView view;

	SimpleBeanEditorDriver<User, ?> driver;
	private List<Operations> listOperations;
	private List<Account> listAccount;

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);
		view = getClientFactory().getView(OperationView.class);
		view.setPresenter(this);
		view.setCurrentAccount(getPlace().getAccountRib());
		panel.setWidget(view);
		attachActivity(new ListAccountPlace(getPlace().getAccountRib()), view.getListAccountPanel());
		eventBus.addHandler(ListAccountEventLoaded.TYPE, this);
		eventBus.addHandler(SelectAccountsEvent.TYPE, this);
		refresh();
	}
	
	@Override
	public void onSelectAccounts(SelectAccountsEvent e) {
		List<String> list = e.getSelectedAccount();
//		if (list.size() == 1)
//			goTo(new OperationPlace(list.get(0)));
		//else ...
		loadOperations(Filters.filterOperationsList(listOperations, list));
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
			loadChart(listOperations);
		}
	}

	private void loadChart(List<Operations> listOperations) {
		GWT.log("loadChart");
		if (listAccount != null && listOperations != null) {
			double total = 0.0;
			for (Account account : listAccount) {
				if (account != null) {
					Double currentAmount = account.getCurrentAmount();
					if (currentAmount != null)
						total += currentAmount;
				}
			}
			GWT.log("total=" + total);

			DataTable tableDepenseCategory = DataTable.create();
			tableDepenseCategory.addColumn(ColumnType.STRING, "Label");
			tableDepenseCategory.addColumn(ColumnType.NUMBER, "Depense");
			// tableDepenseCategory.addRows(Referentiels.categoryDepense.length);
			Map<String, Double> mapsDepenseCategory = new LinkedHashMap<String, Double>(Referentiels.categoryDepense.length);
			for (int i = 0; i < Referentiels.categoryDepense.length; i++) {
				mapsDepenseCategory.put(Referentiels.categoryDepense[i], 0.0);
			}

			DataTable tableRecetteCategory = DataTable.create();
			tableRecetteCategory.addColumn(ColumnType.STRING, "Label");
			tableRecetteCategory.addColumn(ColumnType.NUMBER, "Recette");
			// tableRecetteCategory.addRows(Referentiels.categoryRecette.length);
			Map<String, Double> mapsRecetteCategory = new LinkedHashMap<String, Double>(Referentiels.categoryRecette.length);
			for (int i = 0; i < Referentiels.categoryRecette.length; i++) {
				mapsRecetteCategory.put(Referentiels.categoryRecette[i], 0.0);
			}

			int rowIndex = listOperations.size();
			// GWT.log(rowIndex+":"+new Date() +"-"+total);
			for (Operations op : listOperations) {
				Double amount = op.getAmount();
				total -= amount;
				// GWT.log(rowIndex+":"+op.getDate() +"-"+total);
				if (amount > 0) {
					Double cat = mapsRecetteCategory.get(op.getCategory());
					if (cat == null)
						cat = 0.0;
					mapsRecetteCategory.put(op.getCategory(), cat + amount);
				} else {
					Double cat = mapsDepenseCategory.get(op.getCategory());
					if (cat == null)
						cat = 0.0;
					mapsDepenseCategory.put(op.getCategory(), cat + amount * -1);
				}
				rowIndex--;
			}

			int i = 0;
			for (Entry<String, Double> entry : mapsDepenseCategory.entrySet()) {
				tableDepenseCategory.addRow();
				tableDepenseCategory.setValue(i, 0, entry.getKey());
				tableDepenseCategory.setValue(i, 1, entry.getValue());
				i++;
			}
			i = 0;
			for (Entry<String, Double> entry : mapsRecetteCategory.entrySet()) {
				tableRecetteCategory.addRow();
				tableRecetteCategory.setValue(i, 0, entry.getKey());
				tableRecetteCategory.setValue(i, 1, entry.getValue());
				i++;
			}
			if (getPlace().isDisplayOnlyRecette() != null) {
				if (getPlace().isDisplayOnlyRecette() == true)
					view.setChartCategoryTable(tableRecetteCategory);
				else if (getPlace().isDisplayOnlyRecette() == false)
					view.setChartCategoryTable(tableDepenseCategory);
			}
		}
	}

	@Override
	public void refresh() {
//		if (getPlace().getAccountRib() == null) {
			getClientFactory().getService(TransactionsService.class, TransactionsServiceAsync.class).getOperationList(getPlace().isDisplayOnlyRecette(), createAsync(new AsyncCallback<List<Operations>>() {

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
//		} else {
//			getClientFactory().getService(TransactionsService.class, TransactionsServiceAsync.class).getOperationList(getPlace().getAccountRib(),
//					createAsync(new AsyncCallback<List<Operations>>() {
//
//						@Override
//						public void onSuccess(List<Operations> list) {
//							listOperations = list;
//							loadOperations(list);
//						}
//
//						@Override
//						public void onFailure(Throwable caught) {
//							GWT.log("", caught);
//						}
//					}));
//		}
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
