package com.sencha.gxt.theme.custom.client.container;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.theme.base.client.container.BorderLayoutBaseAppearance;

public class BlueBorderLayoutAppearance extends BorderLayoutBaseAppearance {

  public interface BlueBorderLayoutResources extends BorderLayoutResouces {
    @Override
    @Source({"com/sencha/gxt/theme/base/client/container/BorderLayout.css", "BlueBorderLayout.css"})
    public BlueBorderLayoutStyle css();
  }

  public interface BlueBorderLayoutStyle extends BorderLayoutStyle {

  }

  public BlueBorderLayoutAppearance() {
    super(GWT.<BlueBorderLayoutResources> create(BlueBorderLayoutResources.class));
  }

}
