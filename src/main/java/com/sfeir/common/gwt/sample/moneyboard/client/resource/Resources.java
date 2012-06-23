package com.sfeir.common.gwt.sample.moneyboard.client.resource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.CellTable;
import com.sfeir.common.gwt.client.component.label.base.LabelComponentBase;
import com.sfeir.common.gwt.client.component.label.base.LabelComponentResources;
import com.sfeir.common.gwt.client.resource.BluePrint;

/**
 * Interface de resources pour le projet
 * Contient les images du projet, et les styles CSS
 */
public interface Resources extends ClientBundle {

	@Source("logo.png")
    ImageResource logo();

	@Source("fleche2.png")
    ImageResource fleche();
	
	@Source("setting.png")
    ImageResource setting();
	
    @Source("style.css")
    Style style();
    
    CellTableStyle cellTableStyle();
    
    Resources INSTANCE = GWT.create(Resources.class);
    LabelResources LABEL_RESOURCES = GWT.create(LabelResources.class);
    /**
     * Initialise les resources du projet
     * 
     * A appelé depuis l'entrypoint
     */
    public static final class Init {
	public static void initResources() {
	    BluePrint.INSTANCE.style().ensureInjected();
	    INSTANCE.style().ensureInjected();
	    LABEL_RESOURCES.style().ensureInjected();
	    LabelComponentBase.setResources(LABEL_RESOURCES);
	}
    }

    /**
     * Style pour les tableaux CellTable
     * 
     */
    public interface CellTableStyle extends CellTable.Resources {
	    @Source("CellTable.css")
	    CellTable.Style cellTableStyle();
    }
    
    /**
     * Style pour les champs de formulaires
     * 
     */
    public interface LabelResources extends LabelComponentResources {

	    @Source("labelcomponentstyle.css")
	    @NotStrict
	    LabelComponentResources.Style style();
    }
}
