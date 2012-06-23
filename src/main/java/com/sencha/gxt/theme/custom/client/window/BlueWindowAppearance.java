package com.sencha.gxt.theme.custom.client.window;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.sencha.gxt.theme.base.client.frame.Frame;
import com.sencha.gxt.theme.base.client.frame.NestedDivFrame;
import com.sencha.gxt.theme.base.client.frame.NestedDivFrame.NestedDivFrameResources;
import com.sencha.gxt.theme.base.client.frame.NestedDivFrame.NestedDivFrameStyle;
import com.sencha.gxt.theme.base.client.panel.FramedPanelAppearance;
import com.sencha.gxt.theme.base.client.widget.HeaderBaseAppearance;
import com.sencha.gxt.theme.base.client.widget.HeaderBaseAppearance.HeaderDefaultResources;
import com.sencha.gxt.theme.base.client.widget.HeaderBaseAppearance.HeaderStyle;
import com.sencha.gxt.theme.blue.client.panel.BlueFramedPanelAppearance.FramedPanelStyle;
import com.sencha.gxt.widget.core.client.Window.WindowAppearance;

public class BlueWindowAppearance extends FramedPanelAppearance implements WindowAppearance {

  public interface BlueWindowDivFrameStyle extends NestedDivFrameStyle {

  }

  public interface BlueWindowDivFrameResources extends FramedPanelDivFrameResources, ClientBundle {

    @Source({"com/sencha/gxt/theme/base/client/frame/NestedDivFrame.css", "BlueWindowDivFrame.css"})
    @Override
    BlueWindowDivFrameStyle style();

    @Source("com/sencha/gxt/theme/base/client/shared/clear.gif")
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

    @Override
    @ImageOptions(repeatStyle = RepeatStyle.Both)
    ImageResource bottomLeftBorder();

    @ImageOptions(repeatStyle = RepeatStyle.Both)
    @Override
    ImageResource bottomBorder();

    @Override
    @ImageOptions(repeatStyle = RepeatStyle.Both)
    ImageResource bottomRightBorder();

  }

  public interface BlueWindowStyle extends FramedPanelStyle {
    String ghost();
  }

  public interface BlueHeaderResources extends HeaderDefaultResources {
    @Source({"com/sencha/gxt/theme/base/client/widget/Header.css", "BlueWindowHeader.css"})
    HeaderStyle style();
  }

  public interface BlueWindowResources extends ContentPanelDefaultResources, ClientBundle {

    @Source({
        "com/sencha/gxt/theme/base/client/panel/ContentPanel.css",
        "com/sencha/gxt/theme/base/client/window/Window.css", "BlueWindow.css"})
    @Override
    BlueWindowStyle style();

  }

  private BlueWindowStyle style;

  public BlueWindowAppearance() {
    this((BlueWindowResources) GWT.create(BlueWindowResources.class));
  }

  public BlueWindowAppearance(BlueWindowResources resources) {
    super(resources);

    this.style = resources.style();

    // if (GXT.isIE6()) {
    // frame = new TableFrame((TableFrameResources)
    // GWT.create(ButtonTableFrameResources.class));
    // } else {
    frame = new NestedDivFrame((NestedDivFrameResources) GWT.create(BlueWindowDivFrameResources.class));
    // }
  }

  @Override
  public HeaderBaseAppearance getHeaderAppearance() {
    return new HeaderBaseAppearance(GWT.<HeaderDefaultResources> create(BlueHeaderResources.class));
  }

  @Override
  protected Frame createFrame() {
    return new NestedDivFrame(GWT.<BlueWindowDivFrameResources> create(BlueWindowDivFrameResources.class));
  }

  @Override
  public String ghostClass() {
    return style.ghost();
  }
}
