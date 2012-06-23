package com.sfeir.common.gwt.sample.moneyboard.shared.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.googlecode.objectify.Key;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Account;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.Operations;
import com.sfeir.common.gwt.shared.exceptions.NotAccessAllowedException;

@RemoteServiceRelativePath("transactions") // /!\ Important ne pas oublier de définir le nom du service et de le remplir dans le web.xml
public interface TransactionsService extends RemoteService {
   public List<Operations> getOperationList() throws NotAccessAllowedException;
   public List<Operations> getOperationList(Key<Account> account) throws NotAccessAllowedException;
   public List<Operations> getOperationList(String rib) throws NotAccessAllowedException;
   public List<Operations> getOperationList(Boolean displayOnlyRecette) throws NotAccessAllowedException;
   public void updateTransaction(Operations operation)  throws NotAccessAllowedException;
}
