package com.sfeir.common.gwt.sample.moneyboard.client.listaccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.base.Strings;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.CompositeCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.HasCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellList.Resources;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.sfeir.common.gwt.sample.moneyboard.client.resource.CellListResources;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Account;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.BankBranch;

public class ListAccountViewImpl extends Composite implements ListAccountView {

	private static ListAccountUiBinder uiBinder = GWT.create(ListAccountUiBinder.class);

	interface ListAccountUiBinder extends UiBinder<Widget, ListAccountViewImpl> {
	}

	@UiField
	CSS style;
	
	@UiField
	CellList<Account> list;

	public ListAccountViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	private Presenter presenter;
	private MultiSelectionModel<Account> selectionModel;
	private List<Account> listAccount;
	private boolean selectAll = false;

	public ListAccountViewImpl(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("createNewAccount")
	public void onCreateAccount(ClickEvent e) {
		presenter.onCreateNewAccount();
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setListAccount(List<Account> result, String selectedAccount) {
		this.listAccount = result;
		ArrayList<Account> newList = new ArrayList<Account>(result);
		// newList.add(0, null);
		for (Account account : result) {
			if (selectedAccount == null || selectedAccount.equals(account.getRib())) {
				selectionModel.setSelected(account, true);
			} else {
				selectionModel.setSelected(account, false);
			}
		}
		list.setRowData(newList);
	}

	@UiFactory
	CellList<Account> createCellList() {
		selectionModel = new MultiSelectionModel<Account>();
		List<HasCell<Account, ?>> hasCells = new ArrayList<HasCell<Account, ?>>();
		hasCells.add(new HasCell<Account, Boolean>() {

			private CheckboxCell cell = new CheckboxCell(true, false);

			public Cell<Boolean> getCell() {
				return cell;
			}

			public FieldUpdater<Account, Boolean> getFieldUpdater() {
				return null;
			}

			public Boolean getValue(Account object) {
				if (object == null)
					return false;
				return selectionModel.isSelected(object);
			}
		});
		hasCells.add(new HasCell<Account, Account>() {
			AbstractCell<Account> cell = new AbstractCell<Account>() {

				private BankBranch bank;
				private Double currentAmount;

				@Override
				public void render(com.google.gwt.cell.client.Cell.Context context, Account value, SafeHtmlBuilder sb) {
					sb.appendHtmlConstant("<div width=\"206px\">");
					currentAmount = value.getCurrentAmount();
					if (currentAmount == null)
						currentAmount = 0.0;
					sb.appendHtmlConstant("<b class=\"" + (currentAmount > 0 ? style.green() : style.red()) + "\">");
					sb.appendEscaped(NumberFormat.getCurrencyFormat().format(currentAmount));
					sb.appendHtmlConstant("</b>");
					bank = value.getBank();
					if (bank != null) {
						sb.appendHtmlConstant("<img src=\"/images/" + bank.getImage() + "\" width=\"20px\" height=\"16px\" title=\"" + bank.getNameBank() + "\"/><span>");
						sb.appendEscaped(Strings.nullToEmpty(bank.getName()));
						sb.appendHtmlConstant("</span><i>");
						sb.appendEscaped(Strings.nullToEmpty(value.getName()));
						sb.appendHtmlConstant("</i>");
					} else {
						sb.appendHtmlConstant("<span>");
						sb.appendEscaped(Strings.nullToEmpty(value.getName()));
						sb.appendHtmlConstant("</span>");
					}
					sb.appendHtmlConstant("</div>");
				}
			};

			@Override
			public Cell<Account> getCell() {
				return cell;
			}

			@Override
			public FieldUpdater<Account, Account> getFieldUpdater() {
				return null;
			}

			@Override
			public Account getValue(Account object) {
				return object;
			}
		});
		CellList<Account> list = new CellList<Account>(new CompositeCell<Account>(hasCells) {
			@Override
			public void render(Context context, Account value, SafeHtmlBuilder sb) {
				sb.appendHtmlConstant("<table class=\"" + style.cell() + "\"><tbody><tr>");
				super.render(context, value, sb);
				sb.appendHtmlConstant("</tr></tbody></table>");
			}

			@Override
			protected Element getContainerElement(Element parent) {
				// Return the first TR element in the table.
				return parent.getFirstChildElement().getFirstChildElement().getFirstChildElement();
			}

			@Override
			protected <X> void render(Context context, Account value, SafeHtmlBuilder sb, HasCell<Account, X> hasCell) {
				Cell<X> cell = hasCell.getCell();
				sb.appendHtmlConstant("<td>");
				cell.render(context, hasCell.getValue(value), sb);
				sb.appendHtmlConstant("</td>");
			}
		}, (Resources) GWT.create(CellListResources.class));
		list.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				Set<Account> accounts = selectionModel.getSelectedSet();
				List<String> selectedAccounts = new ArrayList<String>();
				if (accounts != null && accounts.size() > 0) {
					for (Account account : accounts) {
						selectedAccounts.add(account.getRib());
					}
				}
				presenter.onSelectAccount(selectedAccounts);
			}
		});
		return list;
	}

	@UiHandler("selectAll")
	public void selectAllAccount(ClickEvent e) {
		selectAll = !selectAll;
		for (Account account : listAccount) {
			selectionModel.setSelected(account, selectAll);
		}
	}

	interface CSS extends CssResource {
		String cell();

		String green();

		String red();
	}
}
