package com.sfeir.common.gwt.sample.moneyboard.client.dashboard;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.visualization.client.DataTable;
import com.sfeir.common.gwt.client.mvp.View;

public interface DashboardView extends View {
    void setPresenter(Presenter presenter);
    AcceptsOneWidget getListAccountPanel();
    void setChartSoldeTable(DataTable table);
    void setChartBalanceTable(DataTable tableBalance);
    void setChartDepenseCategoryTable(DataTable tableCategory);
    void setChartRecetteCategoryTable(DataTable tableRecetteCategory);
    void setChartGeoTable(DataTable tableGeo);
    public interface Presenter {

    }
}
