package com.sencha.gxt.theme.custom.client.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.sencha.gxt.theme.base.client.menu.SeparatorMenuItemBaseAppearance;

public class BlueSeparatorMenuItemAppearance extends SeparatorMenuItemBaseAppearance {

  public interface BlueSeparatorMenuItemResources extends ClientBundle, SeparatorMenuItemResources {

    @Source({"com/sencha/gxt/theme/base/client/menu/SeparatorMenuItem.css", "BlueSeparatorMenuItem.css"})
    BlueSeparatorMenuItemStyle style();

  }

  public interface BlueSeparatorMenuItemStyle extends SeparatorMenuItemStyle {
  }

  public BlueSeparatorMenuItemAppearance() {
    this(GWT.<BlueSeparatorMenuItemResources> create(BlueSeparatorMenuItemResources.class),
        GWT.<SeparatorMenuItemTemplate> create(SeparatorMenuItemTemplate.class));
  }

  public BlueSeparatorMenuItemAppearance(BlueSeparatorMenuItemResources resources,
      SeparatorMenuItemTemplate template) {
    super(resources, template);
  }

}
