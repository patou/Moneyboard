package com.sencha.gxt.theme.custom.client.toolbar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.theme.base.client.toolbar.ToolBarBaseAppearance;

public class BlueToolBarAppearance extends ToolBarBaseAppearance {

  public interface BlueToolBarDefaultResources extends ClientBundle {
    @Source({"com/sencha/gxt/theme/base/client/toolbar/ToolBarBase.css", "BlueToolBar.css"})
    BlueToolBarDefaultStyle style();

    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    ImageResource background();

  }

  public interface BlueToolBarDefaultStyle extends ToolBarBaseStyle, CssResource {

  }

  private BlueToolBarDefaultStyle style;
  private BlueToolBarDefaultResources resources;

  public BlueToolBarAppearance() {
    this(GWT.<BlueToolBarDefaultResources> create(BlueToolBarDefaultResources.class));
  }

  public BlueToolBarAppearance(BlueToolBarDefaultResources resources) {
    this.resources = resources;
    this.style = this.resources.style();
   
    StyleInjectorHelper.ensureInjected(style, true);
  }

  @Override
  public String toolBarClassName() {
    return style.toolBar();
  }

}
