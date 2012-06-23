package com.sfeir.common.gwt.sample.moneyboard.client.operation;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.Prefix;
import com.sfeir.common.gwt.client.place.PrefixAlias;
import com.sfeir.common.gwt.client.place.Property;
import com.sfeir.common.gwt.client.place.Tokenizer;

public class OperationPlace extends Place {
	@Property(defaultToken=true)
	String accountRib = null;
	
	DisplayOperationType operationType = null;

	public OperationPlace() {
	}
	
	public OperationPlace(DisplayOperationType operationType) {
		this.operationType = operationType;
	}
	
	public OperationPlace(String accountRib) {
		super();
		this.accountRib = accountRib;
	}

	public String getAccountRib() {
		return accountRib;
	}
	
	public Boolean isDisplayOnlyRecette() {
		return operationType == DisplayOperationType.RECIPE;
	}
	
	@Prefix("list_operation")
	@PrefixAlias({"liste-operation","list-operation"})
	public interface PlTokenizer extends Tokenizer<OperationPlace> {
	}
	
	public enum DisplayOperationType {
		RECIPE, EXPENSES
	}
}
