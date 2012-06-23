package com.sencha.gxt.theme.custom.client.panel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.sencha.gxt.theme.base.client.widget.HeaderBaseAppearance;

public class BlueHeaderAppearance extends HeaderBaseAppearance {

  public interface BlueHeaderStlyle extends HeaderStyle {
    String header();

    String headerIcon();

    String headerHasIcon();

    String headerText();

    String headerBar();
  }

  public interface BlueHeaderDefaultResources extends HeaderDefaultResources {

    @Source({"com/sencha/gxt/theme/base/client/widget/Header.css", "BlueHeader.css"})
    BlueHeaderStlyle style();
    
    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    ImageResource headerBackground();
  }
  

  public BlueHeaderAppearance() {
    this(GWT.<BlueHeaderDefaultResources> create(BlueHeaderDefaultResources.class),
        GWT.<Template> create(Template.class));
  }

  public BlueHeaderAppearance(HeaderDefaultResources resources, Template template) {
    super(resources, template);
  }

}
