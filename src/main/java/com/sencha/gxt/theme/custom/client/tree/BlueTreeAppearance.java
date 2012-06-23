package com.sencha.gxt.theme.custom.client.tree;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.sencha.gxt.theme.base.client.tree.TreeBaseAppearance;

public class BlueTreeAppearance extends TreeBaseAppearance {

  public interface BlueTreeResources extends TreeBaseResources, ClientBundle {

    @Source({"com/sencha/gxt/theme/base/client/tree/Tree.css", "TreeDefault.css"})
    TreeBaseStyle style();

  }

  public BlueTreeAppearance() {
    super((TreeBaseResources) GWT.create(BlueTreeResources.class));
  }
}
