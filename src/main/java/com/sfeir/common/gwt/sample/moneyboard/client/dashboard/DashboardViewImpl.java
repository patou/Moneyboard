package com.sfeir.common.gwt.sample.moneyboard.client.dashboard;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.LegendPosition;
import com.google.gwt.visualization.client.visualizations.GeoMap;
import com.google.gwt.visualization.client.visualizations.LineChart;
import com.google.gwt.visualization.client.visualizations.PieChart;
import com.sencha.gxt.widget.core.client.Portlet;
import com.sencha.gxt.widget.core.client.button.IconButton.IconConfig;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.container.PortalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.PortalDropEvent;
import com.sfeir.common.gwt.sample.moneyboard.client.chart.GeoChartWidget;
import com.sfeir.common.gwt.sample.moneyboard.client.chart.LineChartWidget;
import com.sfeir.common.gwt.sample.moneyboard.client.chart.PieChartWidget;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.User;

public class DashboardViewImpl extends Composite implements DashboardView, Editor<User> {

	private static ViewUiBinder uiBinder = GWT.create(ViewUiBinder.class);

	interface ViewUiBinder extends UiBinder<Widget, DashboardViewImpl> {
	}

	enum ToolConfig {
		GEAR(ToolButton.GEAR), CLOSE(ToolButton.CLOSE);

		private IconConfig config;

		private ToolConfig(IconConfig config) {
			this.config = config;
		}
	}

	@UiField
	PortalLayoutContainer portal;

	@UiField
	SimplePanel listAccount;
	
	@UiField
	LineChartWidget solde;
	
	@UiField
	PieChartWidget balance;
	
	@UiField
	PieChartWidget depenseCategories;
	
	@UiField
	PieChartWidget recetteCategories;

	@UiField
	GeoChartWidget geo;

	private Presenter presenter;

	public DashboardViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		portal.setColumnWidth(0, .50);
		portal.setColumnWidth(1, .50);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@UiFactory
	protected ToolButton createToolButton(ToolConfig icon) {
		ToolButton toolButton = new ToolButton(icon.config);
		return toolButton;
	}

	@UiHandler({ "portlet1Close", "portlet2Close", "portlet3Close", "portlet4Close" })
	protected void onClosePortlet(ClickEvent event) {
		ToolButton tool = (ToolButton) event.getSource();
		Widget parent = tool.getParent();
		while (!(parent instanceof Portlet)) {
			parent = parent.getParent();
			if (parent == null)
				return;
		}
		parent.removeFromParent();
	}
	
	@UiHandler("portal")
	protected void onMovePortlet(PortalDropEvent e) {
		GWT.log("move");
		solde.refresh();
		balance.refresh();
		depenseCategories.refresh();
		recetteCategories.refresh();
	}

	@Override
	public AcceptsOneWidget getListAccountPanel() {
		return listAccount;
	}
	
	@Override
	public void setChartSoldeTable(DataTable table) {
		LineChart.Options options = LineChart.Options.create(); 
		options.setLegend(LegendPosition.BOTTOM);
		options.setSmoothLine(true);
		solde.setOptions(options);
		solde.setTable(table);
	}
	
	@Override
	public void setChartBalanceTable(DataTable tableBalance) {
		PieChart.Options options = PieChart.Options.create(); 
		options.setLegend(LegendPosition.BOTTOM);
		options.set3D(true);
		balance.setOptions(options);
		balance.setTable(tableBalance);
	}
	
	@Override
	public void setChartDepenseCategoryTable(DataTable tableCategory) {
		PieChart.Options options = PieChart.Options.create(); 
		options.setLegend(LegendPosition.BOTTOM);
		options.set3D(true);
		depenseCategories.setOptions(options);
		depenseCategories.setTable(tableCategory);
	}
	
	@Override
	public void setChartRecetteCategoryTable(DataTable tableRecetteCategory) {
		PieChart.Options options = PieChart.Options.create(); 
		options.setLegend(LegendPosition.BOTTOM);
		options.set3D(true);
		recetteCategories.setOptions(options);
		recetteCategories.setTable(tableRecetteCategory);
	}
	
	@Override
	public void setChartGeoTable(DataTable tableGeo) {
		GeoMap.Options options = GeoMap.Options.create(); 
//		options.setLegend(LegendPosition.BOTTOM);
//		options.set3D(true);
		geo.setOptions(options);
		geo.setTable(tableGeo);
	}
}
