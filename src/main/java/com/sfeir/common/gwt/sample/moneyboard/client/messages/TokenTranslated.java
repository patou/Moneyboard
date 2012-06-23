package com.sfeir.common.gwt.sample.moneyboard.client.messages;

import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;
import com.google.gwt.i18n.client.LocalizableResource.Generate;

@DefaultLocale("fr")
@Generate(format={"com.google.gwt.i18n.rebind.format.PropertiesFormat"},locales={"en"})
public interface TokenTranslated extends ConstantsWithLookup {
	@DefaultStringValue("inscription")
	String register();
	
	@DefaultStringValue("liste_operation")
	String list_operation();
	
	@DefaultStringValue("creer_compte")
	String create_account();
	
	@DefaultStringValue("tableau-de-bord")
	String dashboard();
	
	@DefaultStringValue("solde")
	String balance();
}
