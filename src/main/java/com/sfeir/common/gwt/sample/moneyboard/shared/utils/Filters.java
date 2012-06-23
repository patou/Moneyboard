package com.sfeir.common.gwt.sample.moneyboard.shared.utils;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Operations;

public class Filters {
	public static List<Operations> filterOperationsList(List<Operations> filter, List<String> listAccount) {
		if (filter == null)
			return newArrayList();
		return Lists.newArrayList(Collections2.filter(filter, Predicates.compose(Predicates.in(listAccount), opToKey)));
	}
	
	private static Function<Operations, String> opToKey = new Function<Operations, String>() {
		@Override
		public String apply(Operations input) {
			return input.getAccount().getName();
		}
	};
}
