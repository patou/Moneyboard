package com.sencha.gxt.theme.custom.client.panel;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.theme.base.client.panel.AccordionLayoutBaseAppearance;
import com.sencha.gxt.theme.base.client.widget.HeaderBaseAppearance;

public class BlueAccordionLayoutAppearance extends AccordionLayoutBaseAppearance {
  
  public interface BlueAccordionLayoutResources extends ContentPanelDefaultResources {

    @Source({"com/sencha/gxt/theme/base/client/panel/ContentPanel.css", "BlueContentPanel.css"})
    @Override
    BlueAccordionLayoutStyle style();

  }

  public interface BlueAccordionLayoutStyle extends ContentPanelStyle {

  }
  
  public BlueAccordionLayoutAppearance() {
    super(GWT.<BlueAccordionLayoutResources> create(BlueAccordionLayoutResources.class));
  }

  public BlueAccordionLayoutAppearance(BlueAccordionLayoutResources resources) {
    super(resources);
  }
  
  @Override
  public HeaderBaseAppearance getHeaderAppearance() {
    return new BlueAccordionHeaderAppearance();
  }
}
