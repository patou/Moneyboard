package com.sencha.gxt.theme.custom.client.panel;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.theme.base.client.panel.ContentPanelBaseAppearance;
import com.sencha.gxt.theme.base.client.widget.HeaderBaseAppearance;

public class BlueContentPanelAppearance extends ContentPanelBaseAppearance {

  public interface BlueContentPanelDefaultResources extends ContentPanelDefaultResources {

    @Source({"com/sencha/gxt/theme/base/client/panel/ContentPanel.css", "BlueContentPanel.css"})
    @Override
    BlueContentPanelStyle style();

  }

  public interface BlueContentPanelStyle extends ContentPanelStyle {

  }

  public BlueContentPanelAppearance() {
    super(GWT.<BlueContentPanelDefaultResources> create(BlueContentPanelDefaultResources.class),
        GWT.<ContentPanelTemplate> create(ContentPanelTemplate.class));
  }

  public BlueContentPanelAppearance(BlueContentPanelDefaultResources resources) {
    super(resources, GWT.<ContentPanelTemplate> create(ContentPanelTemplate.class));
  }

  @Override
  public HeaderBaseAppearance getHeaderAppearance() {
    return new BlueHeaderAppearance();
  }

}
