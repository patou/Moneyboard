package com.sfeir.common.gwt.sample.moneyboard.client.createaccount;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.Prefix;
import com.sfeir.common.gwt.client.place.Dialog;
import com.sfeir.common.gwt.client.place.PrefixAlias;
import com.sfeir.common.gwt.client.place.Tokenizer;

@Dialog(width="200px",height="300px",caption="Create account")
public class CreateAccountPlace extends Place {
	public CreateAccountPlace() {
	}
	
	@Prefix("create_account")
	@PrefixAlias("creer-compte")
	public interface PlTokenizer extends Tokenizer<CreateAccountPlace> {
	}
}
