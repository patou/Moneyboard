package com.sfeir.common.gwt.sample.moneyboard.client.layout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sfeir.common.gwt.client.events.ErrorMessageEvent;
import com.sfeir.common.gwt.client.events.UserLoggedEvent;
import com.sfeir.common.gwt.client.layout.AbstractLayout;
import com.sfeir.common.gwt.client.layout.AbstractLayoutView;
import com.sfeir.common.gwt.sample.moneyboard.client.dashboard.DashboardPlace;
import com.sfeir.common.gwt.sample.moneyboard.client.operation.OperationPlace;
import com.sfeir.common.gwt.sample.moneyboard.client.operation.OperationPlace.DisplayOperationType;
import com.sfeir.common.gwt.sample.moneyboard.client.solde.SoldePlace;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.User;
import com.sfeir.common.gwt.sample.moneyboard.shared.services.UserService;
import com.sfeir.common.gwt.sample.moneyboard.shared.services.UserServiceAsync;

public class LayoutActivity extends AbstractLayout<LayoutPlace> implements LayoutView.Presenter {
	LayoutView view;

	@Override
	protected AbstractLayoutView onCreateView() {
		setLoading(true);
		view = getClientFactory().getView(LayoutView.class);
		view.setPresenter(this);
		return view;
	}

	@Override
	protected void init() {
		updateMenuItem(getFirstPlace());
	}

	@Override
	protected void onLoading(Boolean loading) {
		view.setLoading(loading);
	}

	@Override
	public void onErrorMessage(ErrorMessageEvent e) {
		view.setErrorMessage(e.getErrorMessage());
	}

	@Override
	public void onUserLogged(UserLoggedEvent e) {
		view.displayUser((User)e.getUserData());
	}

	@Override
	public void onLoadComplete() {
		setLoading(false);
	}

	@Override
	public void onPlaceChange(PlaceChangeEvent event) {
		super.onPlaceChange(event);
		Place place = event.getNewPlace();
		updateMenuItem(place);
	}

	public void updateMenuItem(Place place) {
		if (place instanceof OperationPlace) {
			Boolean displayOnlyRecette = ((OperationPlace) place).isDisplayOnlyRecette();
			if (displayOnlyRecette != null && displayOnlyRecette == true)
				view.displayRecetteMenuItem();
			else if (displayOnlyRecette != null && displayOnlyRecette == false)
				view.displayDepenseMenuItem();
		} else if (place instanceof SoldePlace) {
			view.displaySoldeMenuItem();
		} else if (place instanceof DashboardPlace) {
			view.displaySyntheseMenuItem();
		} else
			view.displayDefaultMenuItem();
	}

	@Override
	public void logout() {
		UserServiceAsync service = getClientFactory().getService(UserService.class);
		service.logout(createAsync(new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(Void result) {
				GWT.log("logout OK" + firstPlace);
				History.newItem("");
			}
		}));
	}

	@Override
	public void onMenuDashboard() {
		goTo(new DashboardPlace());
	}

	@Override
	public void onMenuDepense() {
		goTo(new OperationPlace(DisplayOperationType.EXPENSES));
	}

	@Override
	public void onMenuSolde() {
		goTo(new SoldePlace());
	}

	@Override
	public void onMenuRecette() {
		goTo(new OperationPlace(DisplayOperationType.RECIPE));
	}
}
