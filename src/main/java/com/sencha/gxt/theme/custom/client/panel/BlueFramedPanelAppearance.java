package com.sencha.gxt.theme.custom.client.panel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.sencha.gxt.theme.base.client.frame.Frame;
import com.sencha.gxt.theme.base.client.frame.NestedDivFrame;
import com.sencha.gxt.theme.base.client.frame.NestedDivFrame.NestedDivFrameStyle;
import com.sencha.gxt.theme.base.client.panel.FramedPanelAppearance;
import com.sencha.gxt.theme.base.client.widget.HeaderBaseAppearance;

public class BlueFramedPanelAppearance extends FramedPanelAppearance {

  public interface FramedPanelStyle extends ContentPanelStyle {

  }

  public interface BlueFramePanelResources extends ContentPanelDefaultResources {
    @Source({"com/sencha/gxt/theme/base/client/panel/ContentPanel.css", "BlueFramedPanel.css"})
    @Override
    FramedPanelStyle style();
  }

  public interface BlueFramePanelNestedDivFrameStyle extends NestedDivFrameStyle {

  }

  public interface BlueFramedPanelDivFrameResources extends FramedPanelDivFrameResources, ClientBundle {

    @Source({"BlueFramedPanelDivFrame.css"})
    @Override
    BlueFramePanelNestedDivFrameStyle style();

    @ImageOptions(repeatStyle = RepeatStyle.Both)
    ImageResource background();

    @Override
    ImageResource topLeftBorder();

    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    @Override
    ImageResource topBorder();

    @Override
    @ImageOptions(repeatStyle = RepeatStyle.Both)
    ImageResource topRightBorder();

    @ImageOptions(repeatStyle = RepeatStyle.Vertical)
    @Override
    ImageResource leftBorder();

    @ImageOptions(repeatStyle = RepeatStyle.Both)
    @Override
    ImageResource rightBorder();

    @ImageOptions(repeatStyle = RepeatStyle.Both)
    @Override
    ImageResource bottomLeftBorder();

    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    @Override
    ImageResource bottomBorder();

    @ImageOptions(repeatStyle = RepeatStyle.Both)
    @Override
    ImageResource bottomRightBorder();

  }

  public BlueFramedPanelAppearance() {
    this(GWT.<BlueFramePanelResources> create(BlueFramePanelResources.class));
  }

  public BlueFramedPanelAppearance(BlueFramePanelResources resources) {
    super(resources, GWT.<Template> create(Template.class));
  }

  
  @Override
  protected Frame createFrame() {
    return new NestedDivFrame(GWT.<FramedPanelDivFrameResources>create(BlueFramedPanelDivFrameResources.class));
  }

  @Override
  public HeaderBaseAppearance getHeaderAppearance() {
    return new BlueHeaderFramedAppearance();
  }

}
