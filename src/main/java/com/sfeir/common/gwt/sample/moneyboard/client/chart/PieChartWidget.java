package com.sfeir.common.gwt.sample.moneyboard.client.chart;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.visualizations.PieChart;

public class PieChartWidget extends SimplePanel {
	DataTable table;
	PieChart.Options options = null;
	PieChart chart = null;
	public PieChartWidget() {
	}
	
	public void setTable(DataTable table) {
		this.table = table;
		if (isAttached()) {
			draw();
		}
	}
	

	public DataTable getTable() {
		return table;
	}
	
	public void setOptions(PieChart.Options options) {
		this.options = options;
		if (isAttached()) {
			draw();
		}
	}
	
	public PieChart.Options getOptions() {
		return options;
	}
	
	@Override
	protected void onAttach() {
		super.onAttach();
		GWT.log("onAttach !");
		draw();
	}
	
	@Override
	protected void onDetach() {
		super.onDetach();
		GWT.log("onDetach !");
		chart = null;
	}

	private void draw() {
		if (table != null) {
			GWT.log("draw !");
			chart = new PieChart(table, options);
			chart.setSize("100%", "100%");
			setWidget(chart);
			chart.draw(table, options);
		}
	}
	
	public void refresh() {
		chart = null;
		draw();
	}
	
}
