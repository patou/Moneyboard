package com.sencha.gxt.theme.custom.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.widget.core.client.DatePicker.DatePickerAppearance;
import com.sencha.gxt.widget.core.client.DatePicker.DatePickerMessages;

public class BlueDatePickerAppearance implements DatePickerAppearance {

  public interface BlueDatePickerResources extends ClientBundle {
    @Source({"DatePicker.css", "DatePickerVisual.css"})
    @CssResource.NotStrict
    BlueDatePickerStyle css();
    
    ImageResource leftButton();
    
    ImageResource rightButton();
    
    @ImageOptions(preventInlining = true, repeatStyle = RepeatStyle.None)
    ImageResource leftIcon();
    
    @ImageOptions(preventInlining = true, repeatStyle = RepeatStyle.None)
    ImageResource rightIcon();
    
    @ImageOptions(preventInlining = true)
    ImageResource downIcon();
    
    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    ImageResource header();
    
    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    ImageResource footer();
  }

  public interface BlueDatePickerStyle extends CssResource {

    String datePicker();
    
    String inner();
    
    String middle();
    
    String monthButton();
    
    String monthButtonText();
    
    String monthLeftButton();
    
    String monthRightButton();
    
    String leftYearIcon();
    
    String rightYearIcon();
    
  }

  public BlueDatePickerAppearance() {
    this(GWT.<BlueDatePickerResources> create(BlueDatePickerResources.class));
  }

  private final BlueDatePickerResources resources;
  private final BlueDatePickerStyle style;

  public BlueDatePickerAppearance(BlueDatePickerResources resources) {
    this.resources = resources;
    this.style = this.resources.css();
    this.style.ensureInjected();
  }

  @Override
  public void render(SafeHtmlBuilder sb) {
    sb.appendHtmlConstant("<div class=" + style.datePicker() + " style='width: 177px'>");
    
    sb.appendHtmlConstant("<table width=100% cellpadding=0 cellspacing=0 class=header><tr>");
    sb.appendHtmlConstant("<td class=x-date-left><div class=" + style.monthLeftButton() + "></div></td>");
    sb.appendHtmlConstant("<td class=" + style.middle() + " align=center>");
    
    
    sb.appendHtmlConstant("<table cellpadding=0 cellspacing=0 class=" + style.monthButton() + "><tr>");
    sb.appendHtmlConstant("<td class=" + style.monthButtonText() + "></td><td><div class=downIcon>&nbsp;</div></td></tr></table>");
    
    sb.appendHtmlConstant("</td>");
    sb.appendHtmlConstant("<td class=x-date-right><div class=" + style.monthRightButton() + "></div></td></tr></table>");
    
    sb.appendHtmlConstant("<div role=grid><table width=100% cellpadding=0 cellspacing=0 class=x-date-days><tr>");
    for (int i = 0; i < 7; i++) {
      sb.appendHtmlConstant("<td><span>" + i + "</span></td>");
    }
    sb.appendHtmlConstant("</tr></table>");
    
    
    
    sb.appendHtmlConstant("<table width=100% cellpadding=0 cellspacing=0 class=" + style.inner() + ">");
    for (int i = 0; i < 6; i++) {
      sb.appendHtmlConstant("<tr>");
      for (int j = 0; j < 7; j++) {
        sb.appendHtmlConstant("<td><a href=# class=x-datepicker-date><span></span></a></td>");
      }
      sb.appendHtmlConstant("</tr>");
    }
    sb.appendHtmlConstant("</table></div>");
    
    
    sb.appendHtmlConstant("<table width=100% cellpadding=0 cellspacing=0><tr><td class=x-date-bottom align=center></td></tr></table>");
    
    
    sb.appendHtmlConstant("</div>");

  }

  @Override
  public void renderMonthPicker(SafeHtmlBuilder sb, DatePickerMessages messages, String[] monthNames) {
    sb.appendHtmlConstant("<div class=x-date-mp><table border=0 cellspacing=0>");
    
    for (int i = 0; i < 6; i++) {
      sb.appendHtmlConstant("<tr><td class=x-date-mp-month><a href=#>");
      sb.appendHtmlConstant(monthNames[i]);
      sb.appendHtmlConstant("</a></td>");
      sb.appendHtmlConstant("<td class='x-date-mp-month x-date-mp-sep'><a href=#>");
      sb.appendHtmlConstant(monthNames[i + 6]);
      sb.appendHtmlConstant("</a></td>");
      if (i == 0) {
        sb.appendHtmlConstant("<td class=x-date-mp-ybtn align=center>");
        sb.appendHtmlConstant("<div class=" + style.leftYearIcon() + "></div>");
        sb.appendHtmlConstant("</td><td class='x-date-mp-ybtn' align=center>");
        sb.appendHtmlConstant("<div class=" + style.rightYearIcon() + "></div>");
        sb.appendHtmlConstant("</td></tr>");
      } else {
        sb.appendHtmlConstant("<td class='x-date-mp-year'><a href='#'></a></td><td class='x-date-mp-year'><a href='#'></a></td></tr>");
      }
    }
    
    sb.appendHtmlConstant("<tr class=x-date-mp-btns><td colspan='4'><button type='button' class='x-date-mp-ok'>");
    sb.appendHtmlConstant(messages.okText());
    sb.appendHtmlConstant("</button><button type=button class=x-date-mp-cancel>");
    sb.appendHtmlConstant(messages.cancelText());
    sb.appendHtmlConstant("</button></td></tr></table></div>");
  }

  @Override
  public String leftYearSelector() {
    return "." + style.leftYearIcon();
  }

  @Override
  public String rightYearSelector() {
    return "." + style.rightYearIcon();
  }

  @Override
  public String monthButtonSelector() {
    return "." + style.monthButton();
  }

  @Override
  public String monthButtonTextSelector() {
    return "." + style.monthButtonText();
  }

  @Override
  public String dayOfWeekSelector() {
    return ".x-date-days span";
  }

  @Override
  public String todayButtonSelector() {
    return ".x-date-bottom";
  }

  @Override
  public String dayCellSelector() {
    return "table." + style.inner() + " tbody td";
  }

  @Override
  public String dayTextSelector() {
    return "table." + style.inner() + " tbody span";
  }

  @Override
  public String daySelector() {
    return "a.x-datepicker-date";
  }

  @Override
  public String leftMonthSelector() {
    return "." + style.monthLeftButton();
  }

  @Override
  public String rightMonthSelector() {
    return "." + style.monthRightButton();
  }

  @Override
  public String monthPickerMonthSelector() {
    return "td.x-date-mp-month";
  }

  @Override
  public String monthPickerYearSelector() {
    return "td.x-date-mp-year";
  }

  @Override
  public String monthPickerOkSelector() {
    return "button.x-date-mp-ok";
  }

  @Override
  public String monthPickerCancelSelector() {
    return "button.x-date-mp-cancel";
  }

  @Override
  public String activeDaySelector() {
    return "td.x-date-active";
  }

  @Override
  public String prevDaySelector() {
    return "td.x-date-prevday";
  }

  @Override
  public String nextDaySelector() {
    return "td.x-date-nextday";
  }

  @Override
  public String dayHoverClass() {
    return "x-date-active-hover";
  }

  @Override
  public String dayDisabledClass() {
    return "x-date-disabled";
  }

  @Override
  public String todayClass() {
    return "x-date-today";
  }

  @Override
  public String selectedClass() {
    return "x-date-selected";
  }

  @Override
  public String prevDayClass() {
    return "x-date-prevday";
  }

  @Override
  public String nextDayClass() {
    return "x-date-nextday";
  }

  @Override
  public String monthSelectedClass() {
    return "x-date-mp-sel";
  }

  @Override
  public String acitveDayClass() {
    return "x-date-active";
  }

}
