package com.sfeir.common.gwt.sample.moneyboard.server.service;

import com.beoui.geocell.GeocellManager;
import com.beoui.geocell.model.Point;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.BankBranch;

public class BankBranchService {
	
//	public static BankBranch createBankBranch(BankBranch bankBranch) {
//		generateGeoCell(bankBranch);
//		ObjectifyService.begin().put(bankBranch);
//		return bankBranch;
//	}
	
	public static void createBankBranch(BankBranch... listBankBranch) {
		for (BankBranch bankBranch : listBankBranch) {
			generateGeoCell(bankBranch);
		}
		DatastoreService.ofy().put(listBankBranch);
	}

	public static void generateGeoCell(BankBranch bankBranch) {
		Point p = new Point(bankBranch.getLat(), bankBranch.getLng());
		bankBranch.setCells(GeocellManager.generateGeoCell(p));
	}
}
