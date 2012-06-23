package com.sencha.gxt.theme.custom.client.button;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.sencha.gxt.theme.base.client.frame.TableFrame.TableFrameResources;
import com.sencha.gxt.theme.blue.client.button.BlueButtonGroupAppearance.BlueButtonGroupTableFrameStyle;

public interface BlueButtonGroupTableFrameResources extends TableFrameResources, ClientBundle {

  @Source({"com/sencha/gxt/theme/base/client/frame/TableFrame.css", "BlueButtonGroupTableFrame.css"})
  @Override
  BlueButtonGroupTableFrameStyle style();

  @Source("com/sencha/gxt/theme/base/client/shared/clear.gif")
  @ImageOptions(repeatStyle = RepeatStyle.Both)
  ImageResource background();

  @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
  ImageResource backgroundOverBorder();

  @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
  ImageResource backgroundPressedBorder();

  @Source("groupTopLeftBorder.gif")
  @ImageOptions(repeatStyle = RepeatStyle.Vertical)
  @Override
  ImageResource topLeftBorder();

  @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
  @Source("groupTopBorder.gif")
  @Override
  ImageResource topBorder();

  @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
  ImageResource topOverBorder();

  @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
  ImageResource topPressedBorder();

  @Override
  @Source("groupTopRightBorder.gif")
  ImageResource topRightBorder();

  @ImageOptions(repeatStyle = RepeatStyle.Vertical)
  @Source("groupLeftBorder.gif")
  @Override
  ImageResource leftBorder();

  @ImageOptions(repeatStyle = RepeatStyle.Vertical)
  ImageResource leftOverBorder();

  @ImageOptions(repeatStyle = RepeatStyle.Vertical)
  ImageResource leftPressedBorder();

  @ImageOptions(repeatStyle = RepeatStyle.Vertical)
  @Source("groupRightBorder.gif")
  @Override
  ImageResource rightBorder();

  @ImageOptions(repeatStyle = RepeatStyle.Both, preventInlining = true)
  @Override
  ImageResource bottomLeftBorder();



  @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
  @Source("groupBottomBorder.gif")
  @Override
  ImageResource bottomBorder();

  @ImageOptions(repeatStyle = RepeatStyle.Both, preventInlining = true)
  @Override
  ImageResource bottomRightBorder();
  
  @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
  @Source("groupTopNoHeadBorder.gif")
  ImageResource topNoHeadBorder();

}
