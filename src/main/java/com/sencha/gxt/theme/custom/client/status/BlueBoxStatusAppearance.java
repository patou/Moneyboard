package com.sencha.gxt.theme.custom.client.status;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.theme.base.client.status.BoxStatusBaseAppearance;



public class BlueBoxStatusAppearance extends BoxStatusBaseAppearance {

  public interface BlueBoxStatusStyle extends BaseBoxStatusStyle {
    
    String status();

    String statusIcon();

    String statusText();
    
    String statusBox();

  }
  
  public interface BlueBoxResources extends BaseBoxStatusResources, ClientBundle {

    @Override
    @Source({"com/sencha/gxt/theme/base/client/status/Status.css", "BlueBoxStatus.css"})
    BlueBoxStatusStyle style();

    @Override
    @Source("com/sencha/gxt/theme/blue/client/grid/loading.gif")
    ImageResource loading();

  }
  
  public BlueBoxStatusAppearance() {
    this(GWT.<BlueBoxResources> create(BlueBoxResources.class), GWT.<BoxTemplate> create(BoxTemplate.class));
  }
  
  public BlueBoxStatusAppearance(BlueBoxResources resources, BoxTemplate template) {
    super(resources, template);
  }

}
