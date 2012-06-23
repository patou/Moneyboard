package com.sfeir.common.gwt.sample.moneyboard.client.listaccount;

import com.google.gwt.place.shared.Place;
import com.sfeir.common.gwt.client.place.IgnorePlace;

@IgnorePlace
public class ListAccountPlace extends Place {
	String selectedAccount;

	public ListAccountPlace() {
	}

	public ListAccountPlace(String selectedAccount) {
		this.selectedAccount = selectedAccount;
	}

	public String getSelectedAccount() {
		return selectedAccount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListAccountPlace other = (ListAccountPlace) obj;
		return true;
	}

}
