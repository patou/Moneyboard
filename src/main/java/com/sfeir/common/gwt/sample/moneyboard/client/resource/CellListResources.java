package com.sfeir.common.gwt.sample.moneyboard.client.resource;

import com.google.gwt.user.cellview.client.CellList.Style;
  public interface CellListResources extends com.google.gwt.user.cellview.client.CellList.Resources {
    /**
     * The background used for selected items.
     */
//    @ImageOptions(repeatStyle = RepeatStyle.Horizontal, flipRtl = true)
    //ImageResource cellListSelectedBackground();

    /**
     * The styles used in this widget.
     */
    @Source("CellList.css")
    Style cellListStyle();
  }