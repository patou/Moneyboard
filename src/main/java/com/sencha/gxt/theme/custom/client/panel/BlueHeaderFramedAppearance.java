package com.sencha.gxt.theme.custom.client.panel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;

public class BlueHeaderFramedAppearance extends BlueHeaderAppearance{

  public interface BlueHeaderFramedDeafultStlyle extends HeaderStyle {

  }
  
  public interface BlueFramedHeaderDefaultResources extends HeaderDefaultResources {

    @Source({"com/sencha/gxt/theme/base/client/widget/Header.css", "BlueHeader.css", "BlueFramedHeader.css"})
    BlueHeaderFramedDeafultStlyle style();
    
    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    ImageResource headerBackground();
  }
  

  public BlueHeaderFramedAppearance() {
    this(GWT.<BlueFramedHeaderDefaultResources> create(BlueFramedHeaderDefaultResources.class),
        GWT.<Template> create(Template.class));
  }

  public BlueHeaderFramedAppearance(BlueFramedHeaderDefaultResources resources, Template template) {
    super(resources, template);
  }
}
