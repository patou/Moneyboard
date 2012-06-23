package com.sencha.gxt.theme.custom.client.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.theme.base.client.menu.MenuBaseAppearance;

public class BlueMenuAppearance extends MenuBaseAppearance {

  public interface BlueMenuResources extends MenuBaseAppearance.MenuResources, ClientBundle {

    @ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.Vertical)
    ImageResource itemOver();

    @ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.Vertical)
    ImageResource menu();

    ImageResource miniBottom();

    ImageResource miniTop();

    @Source({"com/sencha/gxt/theme/base/client/menu/Menu.css", "BlueMenu.css"})
    BlueMenuStyle style();

  }

  public interface BlueMenuStyle extends BaseMenuStyle {
  }

  public BlueMenuAppearance() {
    this(GWT.<BlueMenuResources> create(BlueMenuResources.class), GWT.<BaseMenuTemplate> create(BaseMenuTemplate.class));
  }

  public BlueMenuAppearance(BlueMenuResources resources, BaseMenuTemplate template) {
    super(resources, template);
  }

}
