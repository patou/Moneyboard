package com.sfeir.common.gwt.sample.moneyboard.client.messages;

import com.google.gwt.i18n.client.Constants;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;
import com.google.gwt.i18n.client.LocalizableResource.Generate;

/**
 * Interface pour les constants de l'application
 * 
 */
@DefaultLocale("fr")
@Generate(format={"com.google.gwt.i18n.rebind.format.PropertiesFormat"},locales={"en"})
public interface I18nConsts extends Constants {
	@DefaultStringArrayValue({"France","Espagne","Belgique"})
	String[] countries();
}
