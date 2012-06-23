package com.sfeir.common.gwt.sample.moneyboard.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.AreaChart;
import com.google.gwt.visualization.client.visualizations.GeoMap;
import com.google.gwt.visualization.client.visualizations.LineChart;
import com.google.gwt.visualization.client.visualizations.PieChart;
import com.sfeir.common.gwt.client.layout.LayoutActivityManager;
import com.sfeir.common.gwt.client.mvp.ClientFactory;
import com.sfeir.common.gwt.client.place.DefaultPlace;
import com.sfeir.common.gwt.sample.moneyboard.client.dashboard.DashboardPlace;
import com.sfeir.common.gwt.sample.moneyboard.client.messages.I18n;
import com.sfeir.common.gwt.sample.moneyboard.client.messages.TokenTranslated;
import com.sfeir.common.gwt.sample.moneyboard.client.resource.Resources;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Moneyboard implements EntryPoint, Runnable {
	Logger log = Logger.getLogger(Moneyboard.class.getName());
	public static DefaultPlace defaultPlace = new DefaultPlace(new DashboardPlace());

	@Override
	public void onModuleLoad() {
		GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {

			@Override
			public void onUncaughtException(Throwable e) {
				log.log(Level.SEVERE, "UncaughtException", e);
				GWT.log("UncaughtException:"+e.getMessage(), e);
			}
		});
		VisualizationUtils.loadVisualizationApi(this, PieChart.PACKAGE, LineChart.PACKAGE, GeoMap.PACKAGE, AreaChart.PACKAGE);
	}

	@Override
	public void run() {

		// Création de la factory
		ClientFactory clientFactory = GWT.create(ClientFactory.class);
		// Changement du text de chargement dans langue de l'utilisateur
		DOM.getElementById("loading-text").setInnerText(clientFactory.getMessage(I18n.class).loadingText());
		// Injection des feuilles de styles
		Resources.Init.initResources();
		// Panel principale où ce met les composants
		SimplePanel panel = new SimplePanel();
		// ----------- Initialisation du MVP ----------------------
		// ActivityMapper = fait le lien entre place (représentation d'une URL) avec une activité (un écran de l'application)
		ActivityMapper activityMapper = clientFactory.getActivityMapper();
		// ActivityManager gère les activity (ici, le LayoutActivityManager gère les layouts de l'application)
		ActivityManager activityManager = new LayoutActivityManager(activityMapper, clientFactory.getEventBus());
		// Indique le display de l'application
		activityManager.setDisplay(panel);
		clientFactory.getPlaceHistoryMapper().setTranslation((ConstantsWithLookup) GWT.create(TokenTranslated.class));
		// S'occupe de gérer l'URL et l'historique de l'application
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(clientFactory.getPlaceHistoryMapper());
		// Initialisation de l'historique
		// PlaceController permet de naviguer d'une place à une autre (méthode goTo(Place))
		// L'éventBus permet de communiquer entre les composants (Activité) par des évènements
		// Le troisième paramètre est la place par défaut si pas de place détecté dans l'URL
		historyHandler.register(clientFactory.getPlaceController(), clientFactory.getEventBus(), defaultPlace);

		// Affichage du composant principale sur la page
		RootPanel.get().add(panel);

		// Regarde si il y a une place dans l'URL et la lance sinon lance la place par défaut
		// Equivant à
		// clientFactory.getPlaceController().goTo(new LoginPlace(new RechercheClientPlace());
		historyHandler.handleCurrentHistory();

	}
}
