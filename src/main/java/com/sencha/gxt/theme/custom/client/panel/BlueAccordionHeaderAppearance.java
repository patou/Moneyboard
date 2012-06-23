package com.sencha.gxt.theme.custom.client.panel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.sencha.gxt.theme.base.client.widget.HeaderBaseAppearance;

public class BlueAccordionHeaderAppearance extends HeaderBaseAppearance {

  public interface BlueAccordionHeaderStlyle extends HeaderStyle {
    String header();

    String headerIcon();

    String headerHasIcon();

    String headerText();

    String headerBar();
  }

  public interface BlueAccordionHeaderResources extends HeaderDefaultResources {

    @Source({"com/sencha/gxt/theme/base/client/widget/Header.css", "BlueHeader.css", "BlueAccordionHeader.css"})
    BlueAccordionHeaderStlyle style();
    
    @Source("light-hd.gif")
    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    ImageResource headerBackground();
  }
  

  public BlueAccordionHeaderAppearance() {
    super(GWT.<BlueAccordionHeaderResources> create(BlueAccordionHeaderResources.class));
  }


}
