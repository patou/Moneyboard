package com.sfeir.common.gwt.sample.moneyboard.client.operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.CompositeCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.HasCell;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTable.Resources;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.IdentityColumn;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.LegendPosition;
import com.google.gwt.visualization.client.visualizations.PieChart;
import com.sfeir.common.gwt.sample.moneyboard.client.chart.PieChartWidget;
import com.sfeir.common.gwt.sample.moneyboard.client.messages.I18n;
import com.sfeir.common.gwt.sample.moneyboard.client.resource.CellTableResources;
import com.sfeir.common.gwt.sample.moneyboard.client.view.EmptyTransaction;
import com.sfeir.common.gwt.sample.moneyboard.client.view.ImportTransactions;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Account;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.BankBranch;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Operations;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Referentiels;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.User;

public class OperationViewImpl extends Composite implements OperationView, Editor<User> {

	private static ViewUiBinder uiBinder = GWT.create(ViewUiBinder.class);

	interface ViewUiBinder extends UiBinder<Widget, OperationViewImpl> {
	}

	@UiField
	SimplePanel tablePanel;
	
	CellTable<Operations> table;

	@UiField(provided = true)
	SimplePager pager;

	@UiField
	SimplePanel listAccount;
	
	@UiField
	CSS style;

	@UiField
	I18n msg;
	
	@UiField
	EmptyTransaction emptyTransaction;
	
	@UiField
	PieChartWidget pieChart;

	private Presenter presenter;

	private ListDataProvider<Operations> dataProvider;
	private Map<String,Account> accounts = new HashMap<String, Account>();
	private ImportTransactions importTransactions;

	private String currentAccount;

	public OperationViewImpl() {
		createPager();
		initWidget(uiBinder.createAndBindUi(this));
		table = createTable();
		tablePanel.setWidget(table);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	CellTable<Operations> createTable() {
		CellTable<Operations> table = new CellTable<Operations>(25, (Resources) GWT.create(CellTableResources.class));
		table.setWidth("100%", true);
		// Attach a column sort handler to the ListDataProvider to sort the list.
		// ListHandler<Operations> sortHandler = new ListHandler<Operations>(
		// ContactDatabase.get().getDataProvider().getList());
		// cellTable.addColumnSortHandler(sortHandler);

		dataProvider = new ListDataProvider<Operations>();

		// Connect the table to the data provider.
		dataProvider.addDataDisplay(table);

		// Create a Pager to control the table.
		pager.setDisplay(table);

		// Add a selection model so we can select cells.
		final SelectionModel<Operations> selectionModel = new MultiSelectionModel<Operations>();
		table.setSelectionModel(selectionModel, DefaultSelectionEventManager.<Operations> createCheckboxManager());

		Column<Operations, Boolean> checkColumn = new Column<Operations, Boolean>(new CheckboxCell(true, false)) {
			@Override
			public Boolean getValue(Operations object) {
				// Get the value from the selection model.
				return selectionModel.isSelected(object);
			}
		};
		table.addColumn(checkColumn);
		checkColumn.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		table.setColumnWidth(checkColumn, 40, Unit.PX);
		Column<Operations, Date> dateColumn = new Column<Operations, Date>(new DateCell(DateTimeFormat.getFormat("dd MMM"))) {
			@Override
			public Date getValue(Operations object) {
				return object.getDate();
			}
		};
		table.addColumn(dateColumn);
		dateColumn.setCellStyleNames(style.dateColumn());
		dateColumn.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		table.setColumnWidth(dateColumn, 45, Unit.PX);
		List<HasCell<Operations, ?>> listCompositeCell = new ArrayList<HasCell<Operations,?>>(2);
		listCompositeCell.add(new HasCell<Operations, Operations>() {
			AbstractCell<Operations> cell = new AbstractCell<Operations>(){

				private Account account;
				private BankBranch bank;

				@Override
				public void render(com.google.gwt.cell.client.Cell.Context context, Operations value, SafeHtmlBuilder sb) {
					sb.appendHtmlConstant("<b>");
					sb.appendEscaped(Strings.nullToEmpty(value.getDescription()));
					sb.appendHtmlConstant("</b>");
					if (value.getAccount() != null) {
						account = accounts.get(value.getAccount().getName());
						bank = account.getBank();
						if (account != null) {
							sb.appendHtmlConstant("<img src=\"/images/" + (bank != null ? bank.getImage() : "none.jpg") + "\" width=\"20px\" height=\"16px\" title=\"" + (bank != null ? bank.getNameBank() : "") + "\"/><span>");
							sb.appendEscaped(Strings.nullToEmpty(account.getName()));
							sb.appendHtmlConstant("&nbsp;/&nbsp;</span>");
						}
					}
//					sb.appendHtmlConstant("<input type=\"text\" value=\"" + Strings.nullToEmpty(value.getNote())+ "\"/>");
//					sb.appendEscaped();
//					sb.appendHtmlConstant("</input>");
					//return SafeHtmlUtils.fromSafeConstant("<b>" + object.getDescription() + "</b><br/><i>" + object.getNote() + "</i>"); // TODO use template !!!!
				}
			};
			@Override
			public Cell<Operations> getCell() {
				return cell;
			}

			@Override
			public FieldUpdater<Operations, Operations> getFieldUpdater() {
				return null;
			}

			@Override
			public Operations getValue(Operations object) {
				return object;
			}
		});
		listCompositeCell.add(new HasCell<Operations, String>() {
			EditTextCell cell = new EditTextCell();
			FieldUpdater<Operations, String> fieldUpdater = new FieldUpdater<Operations, String>() {
				@Override
				public void update(int index, Operations object, String value) {
					object.setNote(value);
					presenter.updateTransaction(object);
				}
			};
			@Override
			public Cell<String> getCell() {
				return cell;
			}

			@Override
			public FieldUpdater<Operations, String> getFieldUpdater() {
				return fieldUpdater;
			}

			@Override
			public String getValue(Operations object) {
				String note = Strings.nullToEmpty(object.getNote());
				if (note.isEmpty()) {
					return msg.enterPersonnalNote();
				}
				return note;
			}
		});
		CompositeCell<Operations> compositeCell = new CompositeCell<Operations>(listCompositeCell);
		Column<Operations, Operations> descriptionColumn = new IdentityColumn<Operations>(compositeCell);
		table.addColumn(descriptionColumn);
		descriptionColumn.setCellStyleNames(style.descriptionColum());
		descriptionColumn.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
//		table.setColumnWidth(descriptionColumn, 100, Unit.PCT);
		Column<Operations, String> categoryColumn = new Column<Operations, String>(new SelectionCell(Arrays.asList(Referentiels.category))) {
			@Override
			public String getValue(Operations object) {
				return object.getCategory();
			}
		};
		categoryColumn.setFieldUpdater(new FieldUpdater<Operations, String>() {
				@Override
				public void update(int index, Operations object, String value) {
					object.setCategory(value);
					presenter.updateTransaction(object);
				}
			});
		table.addColumn(categoryColumn);
		categoryColumn.setCellStyleNames(style.catColum());
		categoryColumn.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		table.setColumnWidth(categoryColumn, 120, Unit.PX);
		Column<Operations, Operations> amoutColumn = new IdentityColumn<Operations>(new AbstractCell<Operations>(){

			private Double currentAmount;

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context, Operations value, SafeHtmlBuilder sb) {
				currentAmount = value.getAmount();
				if (currentAmount == null)
					currentAmount = 0.0;
				sb.appendHtmlConstant("<big class=\""+(currentAmount>0?style.green():style.red())+"\">");
				sb.appendEscaped(NumberFormat.getCurrencyFormat().format(currentAmount));
				sb.appendHtmlConstant("</big>");
				sb.appendHtmlConstant("<i>");
				sb.appendEscaped(Strings.nullToEmpty(value.getClearedStatus()));
				sb.appendHtmlConstant("</i>");
				//return SafeHtmlUtils.fromSafeConstant("<big>" + object.getAmount() + "</big><br/><i>" + object.getClearedStatus() + "</i>"); // TODO use template !!!!
			}
		});
		table.addColumn(amoutColumn);
		amoutColumn.setCellStyleNames(style.amountColum());
		amoutColumn.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		table.setColumnWidth(amoutColumn, 80, Unit.PX);
		return table;
	}

	public void createPager() {
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
	}
	
	@UiHandler("emptyTransaction")
	public void onemptyTransactionClick(ClickEvent event) {
		GWT.log("emptyTransaction");
		importTransactions = new ImportTransactions();
		importTransactions.addCloseHandler(new CloseHandler<PopupPanel>() {

			@Override
			public void onClose(CloseEvent<PopupPanel> event) {
				presenter.refresh();
			}
		});
		importTransactions.setAccount(currentAccount);
		importTransactions.center();
	}

	@Override
	public AcceptsOneWidget getListAccountPanel() {
		return listAccount;
	}

	@Override
	public void setOperationsList(List<Operations> list) {
		dataProvider.setList(list);
		emptyTransaction.setVisible((list.size() == 0));
	}
	
	@Override
	public void setListAccount(List<Account> listAccount) {
		for (Account account : listAccount) {
			if (currentAccount == null) {
				currentAccount = account.getRib();
			}
			accounts.put(account.getRib(), account);
		}
	}
	
	@Override
	public void setCurrentAccount(String accountRib) {
		currentAccount = accountRib;
	}
	
	@Override
	public void setChartCategoryTable(DataTable tableCategory) {
		PieChart.Options options = PieChart.Options.create(); 
		options.setLegend(LegendPosition.BOTTOM);
		options.set3D(true);
		pieChart.setOptions(options);
		pieChart.setTable(tableCategory);
	}
	
	interface CSS extends CssResource {
		String cell();
		String dateColumn();
		String green();
		String red();
		String descriptionColum();
		String amountColum();
		String catColum();
	}

}
