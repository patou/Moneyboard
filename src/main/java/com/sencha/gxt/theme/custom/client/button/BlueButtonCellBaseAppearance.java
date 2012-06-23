package com.sencha.gxt.theme.custom.client.button;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.sencha.gxt.theme.base.client.button.ButtonCellBaseAppearance;

public class BlueButtonCellBaseAppearance<C> extends ButtonCellBaseAppearance<C> {

  public interface BlueButtonCellBaseStyle extends ButtonCellStyle {

  }

  public interface BlueButtonCellDefaultResources extends ButtonCellResources, ClientBundle {

    @Source({"com/sencha/gxt/theme/base/client/button/ButtonCell.css", "BlueButtonCell.css"})
    @Override
    ButtonCellStyle style();
  }

  public BlueButtonCellBaseAppearance() {
    super(GWT.<ButtonCellResources> create(BlueButtonCellDefaultResources.class));
  }

}
