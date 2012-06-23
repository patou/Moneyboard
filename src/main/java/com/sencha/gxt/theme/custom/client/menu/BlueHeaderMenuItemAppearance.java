package com.sencha.gxt.theme.custom.client.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.Element;
import com.sencha.gxt.widget.core.client.menu.HeaderMenuItem.HeaderMenuItemAppearance;

public class BlueHeaderMenuItemAppearance extends BlueItemAppearance implements HeaderMenuItemAppearance {

  public interface BlueHeaderMenuItemResources extends BlueItemResources {

    @Source("BlueHeaderMenuItem.css")
    BlueHeaderMenuItemStyle headerStyle();

  }

  public interface BlueHeaderMenuItemStyle extends CssResource {

    public String menuText();

  }

  private BlueHeaderMenuItemStyle headerStyle;

  public BlueHeaderMenuItemAppearance() {
    this(GWT.<BlueHeaderMenuItemResources> create(BlueHeaderMenuItemResources.class));
  }

  public BlueHeaderMenuItemAppearance(BlueHeaderMenuItemResources resources) {
    super(resources);
    headerStyle = resources.headerStyle();
    headerStyle.ensureInjected();
  }

  @Override
  public void applyItemStyle(Element element) {
    element.addClassName(headerStyle.menuText());
  }

}
