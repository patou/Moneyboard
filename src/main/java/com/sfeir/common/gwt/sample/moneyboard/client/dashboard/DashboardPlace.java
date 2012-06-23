package com.sfeir.common.gwt.sample.moneyboard.client.dashboard;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.Prefix;
import com.sfeir.common.gwt.client.place.PrefixAlias;
import com.sfeir.common.gwt.client.place.Tokenizer;

public class DashboardPlace extends Place {
	public DashboardPlace() {
	}
	
	@Prefix("dashboard")
	@PrefixAlias("tableau-de-bord")
	public interface PlTokenizer extends Tokenizer<DashboardPlace> {
	}
}
