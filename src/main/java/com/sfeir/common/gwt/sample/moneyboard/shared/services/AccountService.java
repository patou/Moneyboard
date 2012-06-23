package com.sfeir.common.gwt.sample.moneyboard.shared.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Account;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.BankBranch;
import com.sfeir.common.gwt.sample.moneyboard.shared.exception.RibNotValid;
import com.sfeir.common.gwt.shared.exceptions.NotAccessAllowedException;

@RemoteServiceRelativePath("account") // /!\ Important ne pas oublié de définir le nom du service et de le remplir dans le web.xml
public interface AccountService extends RemoteService {
   public List<BankBranch> getListBank(String codeBank);
   public List<BankBranch> getListBankNearUser(Double lat, Double lng);
   public void addAccount(Account account) throws RibNotValid, NotAccessAllowedException;
   public List<Account> getAccountUserList() throws NotAccessAllowedException;
}
