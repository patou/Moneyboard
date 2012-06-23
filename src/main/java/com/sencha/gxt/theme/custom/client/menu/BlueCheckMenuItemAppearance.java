package com.sencha.gxt.theme.custom.client.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.sencha.gxt.theme.base.client.menu.CheckMenuItemBaseAppearance;
import com.sencha.gxt.theme.blue.client.menu.BlueMenuItemAppearance.BlueMenuItemResources;

public class BlueCheckMenuItemAppearance extends CheckMenuItemBaseAppearance {

  public interface BlueCheckMenuItemResources extends CheckMenuItemResources, BlueMenuItemResources, ClientBundle {

    @Source({"com/sencha/gxt/theme/base/client/menu/CheckMenuItem.css", "BlueCheckMenuItem.css"})
    BlueCheckMenuItemStyle checkStyle();

  }

  public interface BlueCheckMenuItemStyle extends CheckMenuItemStyle {
  }

  public BlueCheckMenuItemAppearance() {
    this(GWT.<BlueCheckMenuItemResources> create(BlueCheckMenuItemResources.class),
        GWT.<MenuItemTemplate> create(MenuItemTemplate.class));
  }

  public BlueCheckMenuItemAppearance(BlueCheckMenuItemResources resources, MenuItemTemplate template) {
    super(resources, template);
  }

}
