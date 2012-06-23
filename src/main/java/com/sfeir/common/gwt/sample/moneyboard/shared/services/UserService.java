package com.sfeir.common.gwt.sample.moneyboard.shared.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.User;
import com.sfeir.common.gwt.shared.exceptions.NotLoginException;

@RemoteServiceRelativePath("user") // /!\ Important ne pas oublié de définir le nom du service et de le remplir dans le web.xml
public interface UserService extends RemoteService {
    /**
     * Authentifie un utilisateur dans l'application
     * @param email
     * @param user
     * @return
     * @throws NotLoginException
     */
    public User login(String login, String user) throws NotLoginException;
    /**
     * Delogge l'utilisateur de l'application
     * @throws NotLoginException
     */
    public void logout() throws NotLoginException;
    /**
     * Enregistre un nouveau utilisateur
     * @param user The new user to save on the server
     */
	public void register(User user);
}
