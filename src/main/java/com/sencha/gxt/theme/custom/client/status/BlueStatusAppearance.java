package com.sencha.gxt.theme.custom.client.status;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.theme.base.client.status.StatusBaseAppearance;

public class BlueStatusAppearance extends StatusBaseAppearance {

  public interface BlueResources extends StatusBaseAppearance.BaseStatusResources, ClientBundle {

    @Override
    @Source({"com/sencha/gxt/theme/base/client/status/Status.css", "BlueStatus.css"})
    BaseStatusStyle style();

    @Override
    @Source("com/sencha/gxt/theme/blue/client/grid/loading.gif")
    ImageResource loading();

  }

  public BlueStatusAppearance() {
    super(GWT.<BaseStatusResources> create(BlueResources.class), GWT.<Template> create(Template.class));
  }
  
  public BlueStatusAppearance(BlueResources resources, Template template) {
    super(resources, template);
  }

}
