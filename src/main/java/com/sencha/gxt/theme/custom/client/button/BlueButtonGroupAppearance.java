package com.sencha.gxt.theme.custom.client.button;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safecss.shared.SafeStylesBuilder;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.theme.base.client.frame.TableFrame;
import com.sencha.gxt.theme.base.client.frame.TableFrame.TableFrameStyle;
import com.sencha.gxt.widget.core.client.button.ButtonGroup.ButtonGroupAppearance;

public class BlueButtonGroupAppearance implements ButtonGroupAppearance {

  public interface GroupTemplate extends XTemplates {
    @XTemplate("<div class='{style.group}'><div class='{style.body}'></div></div>")
    SafeHtml render(BlueButtonGroupStyle style);
  }

  public interface BlueButtonGroupResources extends ClientBundle {
    @Source("BlueButtonGroup.css")
    BlueButtonGroupStyle css();
  }

  public interface BlueButtonGroupStyle extends CssResource {
    String group();

    String header();

    String text();

    String body();

  }

  public interface BlueButtonGroupTableFrameStyle extends TableFrameStyle {
    String noheader();
  }

  private BlueButtonGroupResources resources;
  private BlueButtonGroupStyle style;
  private TableFrame frame;
  private GroupTemplate template;

  public BlueButtonGroupAppearance() {
    this(GWT.<BlueButtonGroupResources> create(BlueButtonGroupResources.class));
  }

  public BlueButtonGroupAppearance(BlueButtonGroupResources resources) {
    this.resources = resources;
    this.style = this.resources.css();

    template = GWT.<GroupTemplate> create(GroupTemplate.class);

    StyleInjectorHelper.ensureInjected(this.style, true);

    frame = new TableFrame(GWT.<BlueButtonGroupTableFrameResources> create(BlueButtonGroupTableFrameResources.class));

  }

  @Override
  public void render(SafeHtmlBuilder sb) {
    frame.render(sb, template.render(style), new SafeStylesBuilder().toSafeStyles());
  }

  @Override
  public void updateText(XElement parent, String text) {
    frame.getHeaderElem(parent).setInnerHTML("<span class=" + style.text() + ">" + text + "</span>");
  }

  @Override
  public int getFrameHeight(XElement parent) {
    int h = frame.getResources().bottomBorder().getHeight();
    h += frame.getContentElem(parent).getFrameSize().getHeight();
    return h;
  }

  @Override
  public int getFrameWidth(XElement parent) {
    int w = frame.getResources().leftBorder().getWidth();
    w += frame.getResources().rightBorder().getWidth();
    return w;
  }

  @Override
  public XElement getHeaderElement(XElement parent) {
    return parent.selectNode("." + style.header());
  }

  @Override
  public XElement getContentElem(XElement parent) {
    return parent.selectNode("." + style.body());
  }

  @Override
  public void onHideHeader(XElement parent, boolean hide) {
    XElement head = frame.getHeaderElem(parent);
    if (head != null && head.getChildCount() > 0) {
      head.getFirstChildElement().getStyle().setDisplay(hide ? Display.NONE : Display.BLOCK);
    }
    BlueButtonGroupTableFrameStyle s = (BlueButtonGroupTableFrameStyle) frame.getStyle();
    parent.setClassName(s.noheader(), hide);
  }

}
