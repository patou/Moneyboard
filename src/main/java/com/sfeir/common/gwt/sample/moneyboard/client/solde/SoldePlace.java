package com.sfeir.common.gwt.sample.moneyboard.client.solde;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.Prefix;
import com.sfeir.common.gwt.client.place.PrefixAlias;
import com.sfeir.common.gwt.client.place.Tokenizer;

public class SoldePlace extends Place {
	public SoldePlace() {
	}
	
	@Prefix("balance")
	@PrefixAlias("solde")
	public interface PlTokenizer extends Tokenizer<SoldePlace> {
	}
}
