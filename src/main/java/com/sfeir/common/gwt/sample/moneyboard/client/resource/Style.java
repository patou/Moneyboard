package com.sfeir.common.gwt.sample.moneyboard.client.resource;

import com.google.gwt.resources.client.CssResource;

/**
 * Toutes les class CSS définie dans le fichier style.css
 * 
 */
public interface Style extends CssResource {

    String logo();

    String infoClient();

    String submit();

    String important();

    String paddingLabelVertical();

    String submitLong();

    String header();

    String menuNavigation();

    String footer();

    @ClassName("ui-menu")
    String uiMenu();

    String navigation();

    String borderBottomImportant();

    String labelError();

    String borderAll();

    String reset();

    String focus();

    String colsep();

    @ClassName("Ariane")
    String ariane();

    String rechercheAvanceContainer();

    String marginHeader();

    String tooltip();
    
    @ClassName("error")
    String inputError();
    @ClassName("Court")
    String inputCourt();
    @ClassName("Long")
    String inputLong();
    @ClassName("Moyen")
    String inputMoyen();
    
    String bugLogin();
    
    String navfleche();
    
    String setting();
}
