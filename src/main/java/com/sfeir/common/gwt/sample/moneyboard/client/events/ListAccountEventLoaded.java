package com.sfeir.common.gwt.sample.moneyboard.client.events;

import java.util.List;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Account;

public class ListAccountEventLoaded extends GwtEvent<ListAccountEventLoaded.Handler> {
	public static Type<Handler> TYPE = new Type<ListAccountEventLoaded.Handler>();
	private List<Account> listAccount;
	
	public ListAccountEventLoaded(List<Account> listAccount) {
		super();
		this.listAccount = listAccount;
	}
	
	public List<Account> getListAccount() {
		return listAccount;
	}

	public interface Handler extends EventHandler {
		void onListAccountLoaded(ListAccountEventLoaded e);
	}

	@Override
	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onListAccountLoaded(this);
	}

}
