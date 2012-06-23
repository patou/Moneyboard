package com.sencha.gxt.theme.custom.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.core.client.resources.ThemeStyles.Styles;
import com.sencha.gxt.core.client.resources.ThemeStyles.ThemeAppearance;

public class BlueThemeAppearance implements ThemeAppearance {

  static interface Bundle extends ClientBundle {

    @Source({"com/sencha/gxt/theme/base/client/BaseTheme.css", "BlueTheme.css"})
    Styles css();

    ImageResource more();
  }

  private Bundle bundle;
  private Styles style;

  @Override
  public Styles style() {
    return style;
  }

  public BlueThemeAppearance() {
    this.bundle = GWT.create(Bundle.class);
    this.style = bundle.css();
    this.style.ensureInjected();
  }

  @Override
  public ImageResource moreIcon() {
    return bundle.more();
  }

}
