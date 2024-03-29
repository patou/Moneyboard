package com.sencha.gxt.theme.custom.client.slider;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.sencha.gxt.theme.base.client.slider.SliderVerticalBaseAppearance;

public class BlueSliderVerticalAppearance extends SliderVerticalBaseAppearance {

  public static class BlueSliderVerticalAppearanceHelper {

    public static String getTrackVerticalBottom() {
      return new StringBuilder("url(").append(GWT.getModuleBaseURL()).append(
          "/blue/images/slider/trackVerticalBottom.png);").toString();
    }

    public static String getTrackVerticalMiddle() {
      return new StringBuilder("url(").append(GWT.getModuleBaseURL()).append(
          "/blue/images/slider/trackVerticalMiddle.png);").toString();
    }

    public static String getTrackVerticalTop() {
      return new StringBuilder("url(").append(GWT.getModuleBaseURL()).append(
          "/blue/images/slider/trackVerticalTop.png);").toString();
    }

  }

  public interface BlueSliderVerticalResources extends SliderVerticalResources, ClientBundle {

    @Source({
        "com/sencha/gxt/theme/base/client/slider/Slider.css",
        "com/sencha/gxt/theme/base/client/slider/SliderVertical.css", "BlueSliderVertical.css"})
    BlueVerticalSliderStyle style();

    ImageResource thumbVertical();

    ImageResource thumbVerticalDown();

    ImageResource thumbVerticalOver();

    ImageResource trackVerticalBottom();

    @ImageOptions(repeatStyle = RepeatStyle.Vertical)
    ImageResource trackVerticalMiddle();

    ImageResource trackVerticalTop();

  }

  public interface BlueVerticalSliderStyle extends BaseSliderVerticalStyle, CssResource {
  }

  public BlueSliderVerticalAppearance() {
    this(GWT.<BlueSliderVerticalResources> create(BlueSliderVerticalResources.class),
        GWT.<SliderVerticalTemplate> create(SliderVerticalTemplate.class));
  }

  public BlueSliderVerticalAppearance(BlueSliderVerticalResources resources, SliderVerticalTemplate template) {
    super(resources, template);
  }

}
