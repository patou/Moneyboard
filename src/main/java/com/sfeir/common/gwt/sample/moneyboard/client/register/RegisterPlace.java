package com.sfeir.common.gwt.sample.moneyboard.client.register;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.Prefix;
import com.sfeir.common.gwt.client.place.PrefixAlias;
import com.sfeir.common.gwt.client.place.Tokenizer;

public class RegisterPlace extends Place {
	String email;
	public RegisterPlace() {
	}
	
	public RegisterPlace(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}

	@Prefix("register")
	@PrefixAlias("inscription")
	public interface PlTokenizer extends Tokenizer<RegisterPlace> {
	}
}
