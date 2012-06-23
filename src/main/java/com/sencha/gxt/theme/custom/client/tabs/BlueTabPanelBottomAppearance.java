package com.sencha.gxt.theme.custom.client.tabs;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.dom.XElement;

public class BlueTabPanelBottomAppearance extends BlueTabPanelAppearance {

  public interface BottomTemplate extends Template {

    @XTemplate(source = "TabPanelBottom.html")
    SafeHtml render(TabPanelBaseStyle style);

  }

  public BlueTabPanelBottomAppearance() {
    this(GWT.<BlueTabPanelResources> create(BlueTabPanelResources.class),
        GWT.<BottomTemplate> create(BottomTemplate.class), GWT.<ItemTemplate> create(ItemTemplate.class));
  }

  public BlueTabPanelBottomAppearance(BlueTabPanelResources resources, BottomTemplate template,
      ItemTemplate itemTemplate) {
    super(resources, template, itemTemplate);

    this.style = resources.style();
    this.style.ensureInjected();

    this.template = template;
    this.itemTemplate = itemTemplate;
  }

  @Override
  public XElement getBar(XElement parent) {
    return parent.selectNode("." + style.tabFooter());
  }

  @Override
  public XElement getStrip(XElement parent) {
    return parent.selectNode("." + style.tabStripBottom());
  }

}
