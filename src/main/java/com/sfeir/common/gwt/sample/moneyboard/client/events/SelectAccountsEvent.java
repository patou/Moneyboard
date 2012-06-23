package com.sfeir.common.gwt.sample.moneyboard.client.events;

import java.util.List;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class SelectAccountsEvent extends GwtEvent<SelectAccountsEvent.Handler> {
	public static Type<Handler> TYPE = new Type<SelectAccountsEvent.Handler>();
	private final List<String> selectedAccount;
	
	public SelectAccountsEvent(List<String> selectedAccount) {
		super();
		this.selectedAccount = selectedAccount;
	}
	
	public List<String> getSelectedAccount() {
		return selectedAccount;
	}
	
	public interface Handler extends EventHandler {
		void onSelectAccounts(SelectAccountsEvent e);
	}

	@Override
	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onSelectAccounts(this);
	}

}
