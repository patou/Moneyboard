package com.sencha.gxt.theme.custom.client.box;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.sencha.gxt.theme.blue.client.window.BlueWindowAppearance;

public class BlueMessageBoxAppearance extends BlueWindowAppearance {

  public interface BlueMessageBoxResources extends BlueWindowResources, ClientBundle {

    @Source({
        "com/sencha/gxt/theme/base/client/panel/ContentPanel.css",
        "com/sencha/gxt/theme/blue/client/window/BlueWindow.css"})
    @Override
    BlueWindowStyle style();

  }

  public BlueMessageBoxAppearance() {
    super((BlueMessageBoxResources) GWT.create(BlueMessageBoxResources.class));
  }
}
