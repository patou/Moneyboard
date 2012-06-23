package com.sencha.gxt.theme.custom.client.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.theme.base.client.menu.ItemBaseAppearance;

public class BlueItemAppearance extends ItemBaseAppearance {

  public interface BlueItemResources extends ItemBaseAppearance.ItemResources, ClientBundle {

    @Source({"com/sencha/gxt/theme/base/client/menu/Item.css", "BlueItem.css"})
    BlueItemStyle style();

    ImageResource itemOver();

  }

  public interface BlueItemStyle extends ItemStyle {

    String active();

  }

  public BlueItemAppearance() {
    this(GWT.<BlueItemResources> create(BlueItemResources.class));
  }

  public BlueItemAppearance(BlueItemResources resources) {
    super(resources);
  }

}
