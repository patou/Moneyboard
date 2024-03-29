package com.sencha.gxt.theme.custom.client.slider;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.sencha.gxt.theme.base.client.slider.SliderHorizontalBaseAppearance;

public class BlueSliderHorizontalAppearance extends SliderHorizontalBaseAppearance {

  public interface BlueHorizontalSliderStyle extends SliderHorizontalStyle, CssResource {
  }

  public static class BlueSliderHorizontalAppearanceHelper {

    public static String getTrackHorizontalLeft() {
      return new StringBuilder("url(").append(GWT.getModuleBaseURL()).append(
          "/blue/images/slider/trackHorizontalLeft.png);").toString();
    }

    public static String getTrackHorizontalMiddle() {
      return new StringBuilder("url(").append(GWT.getModuleBaseURL()).append(
          "blue/images/slider/trackHorizontalMiddle.png);").toString();
    }

    public static String getTrackHorizontalRight() {
      return new StringBuilder("url(").append(GWT.getModuleBaseURL()).append(
          "blue/images/slider/trackHorizontalRight.png);").toString();
    }

  }

  public interface BlueSliderHorizontalResources extends SliderHorizontalResources, ClientBundle {

    @Source({
        "com/sencha/gxt/theme/base/client/slider/Slider.css",
        "com/sencha/gxt/theme/base/client/slider/SliderHorizontal.css", "BlueSliderHorizontal.css"})
    BlueHorizontalSliderStyle style();

    ImageResource thumbHorizontal();

    ImageResource thumbHorizontalDown();

    ImageResource thumbHorizontalOver();

    ImageResource trackHorizontalLeft();

    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    ImageResource trackHorizontalMiddle();

    ImageResource trackHorizontalRight();

  }

  public BlueSliderHorizontalAppearance() {
    this(GWT.<BlueSliderHorizontalResources> create(BlueSliderHorizontalResources.class),
        GWT.<SliderHorizontalTemplate> create(SliderHorizontalTemplate.class));
  }

  public BlueSliderHorizontalAppearance(BlueSliderHorizontalResources resources,
      SliderHorizontalTemplate template) {
    super(resources, template);
  }

}
