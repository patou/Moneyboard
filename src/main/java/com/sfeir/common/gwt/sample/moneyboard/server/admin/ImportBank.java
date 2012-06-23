package com.sfeir.common.gwt.sample.moneyboard.server.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sfeir.common.gwt.sample.moneyboard.server.service.BankBranchService;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.BankBranch;

public class ImportBank extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -327825099695567763L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		BankBranchService.createBankBranch(new BankBranch("30002", "LCL", "Le Crédit Lyonnais", "06832", "LCL Chartres", "1 PLACE DES EPARS 28000 CHARTRES", 48.44415, 1.48458, "lcl.jpg")
//				,new BankBranch("30002", "LCL", "Le Crédit Lyonnais", "03832", "LCL St Malo Rocabey", "3 BD DE LA TOUR D AUVERGNE 35400 ST MALO", 48.65147, -2.00580, "lcl.jpg")
//				,new BankBranch("30002", "LCL", "Le Crédit Lyonnais", "07897", "LCL SURESNES VAL D'OR", "8 BOULEVARD LOUIS LOUCHEUR 92150 SURESNES", 48.86002, 2.21340, "lcl.jpg")
//				,new BankBranch("18206", "CA", "Crédit Agricole", "07897", "CA SURESNES", "92150 SURESNES", 48.85902, 2.21360, "ca.jpg")
//				,new BankBranch("30003", "SG", "Société Générale", "07897", "SG SURESNES", "92150 SURESNES", 48.86002, 2.21350, "sg.jpg")
//				,new BankBranch("10207", "BP", "Banque Populaire Rives de Paris", "07897", "BP SURESNES", "92150 SURESNES", 48.86402, 2.21240, "bp.jpg")
//				,new BankBranch("30004", "BNP", "BNP Parisbas", "07897", "BNP SURESNES", "92150 SURESNES", 48.86302, 2.21340, "bnp.jpg")
//		);
		BankBranchService.createBankBranch(
				new BankBranch("18206", "CA", "Crédit Agricole", "54654", "Paris", "Paris, France", 48.85902, 2.21360, "ca.jpg")
				,new BankBranch("30003", "SG", "Société Générale", "07897", "New York city", "New York, United States", 40.727, -73.996, "sg.jpg")
				,new BankBranch("30007", "HSBC", "HSBC", "07897", "San diego", "San diago, CA, United States", 32.716, -117.153, "hsbc.jpg")
				,new BankBranch("30004", "BNP", "BNP Parisbas", "07897", "Paris", "Paris, France", 48.86302, 2.21340, "bnp.jpg")
	   );
		
	}
}
