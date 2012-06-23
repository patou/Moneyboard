package com.sfeir.common.gwt.sample.moneyboard.client.dashboard;

import java.util.Date;
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
import com.sfeir.common.gwt.sample.moneyboard.client.listaccount.ListAccountActivity;
import com.sfeir.common.gwt.sample.moneyboard.client.listaccount.ListAccountPlace;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Account;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.BankBranch;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Operations;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Referentiels;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.User;
import com.sfeir.common.gwt.sample.moneyboard.shared.services.TransactionsService;
import com.sfeir.common.gwt.sample.moneyboard.shared.services.TransactionsServiceAsync;
import com.sfeir.common.gwt.sample.moneyboard.shared.utils.Filters;

public class DashboardActivity extends ActivityGroup<DashboardPlace> implements DashboardView.Presenter, ListAccountEventLoaded.Handler, SelectAccountsEvent.Handler {
	DashboardView view;

	SimpleBeanEditorDriver<User, ?> driver;

	private ListAccountActivity listAccountActivity;
	private List<Operations> listOperations;

	private List<Account> listAccount;

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);
		view = getClientFactory().getView(DashboardView.class);
		view.setPresenter(this);
		panel.setWidget(view);
		listAccountActivity = (ListAccountActivity) loadActivity(new ListAccountPlace());
		attachActivity(listAccountActivity, view.getListAccountPanel());
		getClientFactory().getService(TransactionsService.class, TransactionsServiceAsync.class).getOperationList(createAsync(new AsyncCallback<List<Operations>>() {

			@Override
			public void onSuccess(List<Operations> list) {
				listOperations = list;
				loadChart(list);
			}

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("", caught);
			}
		}));
		eventBus.addHandler(ListAccountEventLoaded.TYPE, this);
		eventBus.addHandler(SelectAccountsEvent.TYPE, this);
	}

	@Override
	public void onListAccountLoaded(ListAccountEventLoaded e) {
		this.listAccount = e.getListAccount();
		GWT.log("onListAccountLoaded");
		loadChart(listOperations);
	}
	
	@Override
	public void onSelectAccounts(SelectAccountsEvent e) {
		List<String> list = e.getSelectedAccount();
		loadChart(Filters.filterOperationsList(listOperations, list));		
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

			DataTable tableSolde = DataTable.create();
			tableSolde.addColumn(ColumnType.DATE, "Date");
			tableSolde.addColumn(ColumnType.NUMBER, "Solde");

			DataTable tableBalance = DataTable.create();
			tableBalance.addColumn(ColumnType.STRING, "Label");
			tableBalance.addColumn(ColumnType.NUMBER, "Balance");
			tableBalance.addRows(2);
			tableBalance.setValue(0, 0, "Dépense");
			tableBalance.setValue(1, 0, "Recette");

			DataTable tableGeo = DataTable.create();
			tableGeo.addColumn(ColumnType.STRING, "Ville");
			tableGeo.addColumn(ColumnType.NUMBER, "Nb");
			Map<String, Double> mapsGeo = new LinkedHashMap<String, Double>(listAccount.size());

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
			double balanceNeg = 0.0, balancePos = 0.0;
			tableSolde.addRows(rowIndex + 1);
			tableSolde.setValue(rowIndex, 0, new Date());
			tableSolde.setValue(rowIndex, 1, total);
			// GWT.log(rowIndex+":"+new Date() +"-"+total);
			for (Operations op : listOperations) {
				String account = op.getAccount().getName();
				Double amount = op.getAmount();
				total -= amount;
				tableSolde.setValue(rowIndex, 0, op.getDate());
				tableSolde.setValue(rowIndex, 1, total);
				// GWT.log(rowIndex+":"+op.getDate() +"-"+total);
				if (amount > 0) {
					balancePos += amount;
					Double cat = mapsRecetteCategory.get(op.getCategory());
					if (cat == null)
						cat = 0.0;
					mapsRecetteCategory.put(op.getCategory(), cat + amount);
				} else {
					balanceNeg += amount * -1;
					Double cat = mapsDepenseCategory.get(op.getCategory());
					if (cat == null)
						cat = 0.0;
					mapsDepenseCategory.put(op.getCategory(), cat + amount * -1);
				}
				Double geo = mapsGeo.get(account);
				if (geo == null)
					geo = 0.0;
				mapsGeo.put(account, geo);
				rowIndex--;
			}
			tableBalance.setValue(0, 1, balancePos);
			tableBalance.setValue(1, 1, balanceNeg);

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
			i = 0;
			for (Account account : listAccount) {
				BankBranch bank = account.getBank();
				if (bank != null) {
					tableGeo.addRow();
					tableGeo.setValue(i, 0, bank.getAddress());
					Double value = mapsGeo.get(account.getRib());
					if (value == null)
						value = 0.0;
					tableGeo.setValue(i, 1, value);
					i++;
				}
			}

			view.setChartSoldeTable(tableSolde);
			view.setChartBalanceTable(tableBalance);
			view.setChartDepenseCategoryTable(tableDepenseCategory);
			view.setChartRecetteCategoryTable(tableRecetteCategory);
			view.setChartGeoTable(tableGeo);
		}
	}
}
