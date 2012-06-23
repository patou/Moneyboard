package com.sfeir.common.gwt.sample.moneyboard.client.layout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.User;

public class LayoutViewImpl extends Composite implements LayoutView {

	private static ViewUiBinder uiBinder = GWT.create(ViewUiBinder.class);

	interface ViewUiBinder extends UiBinder<Widget, LayoutViewImpl> {
	}

	@UiField
	Label errorLabel;
	@UiField
	SimplePanel content;
	@UiField
	UListElement settingPanel;
	@UiField
	InlineLabel user;
	@UiField
	DivElement navfleche;

	private Presenter presenter;
	RootPanel loadingContainer = RootPanel.get("loading-container");

	public LayoutViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		setLoading(false);
	}

	@Override
	public void setErrorMessage(String errorMsg) {
		if (errorMsg != null) {
			errorLabel.setText(errorMsg);
			errorLabel.setVisible(true);
		} else {
			errorLabel.setText("");
			errorLabel.setVisible(false);
		}
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@UiHandler("logout")
	public void onLogoutClick(ClickEvent e) {
		presenter.logout();
	}

	@UiHandler("settings")
	public void onSettingsClick(ClickEvent e) {
		UIObject.setVisible(settingPanel, !UIObject.isVisible(settingPanel));
	}

	@UiHandler("menuRecette")
	public void onMenuRecette(ClickEvent e) {
		presenter.onMenuRecette();
	}

	@UiHandler("logo")
	public void onLogo(ClickEvent e) {
		presenter.onMenuDashboard();
	}

	@UiHandler("menuSynthese")
	public void onMenuSynthese(ClickEvent e) {
		presenter.onMenuDashboard();
	}

	@UiHandler("menuDepense")
	public void onMenuDepense(ClickEvent e) {
		presenter.onMenuDepense();
	}

	@UiHandler("menuSolde")
	public void onMenuSolde(ClickEvent e) {
		presenter.onMenuSolde();
	}

	@Override
	public void setWidget(IsWidget w) {
		content.setWidget(w);
		presenter.onLoadComplete();
	}

	@Override
	public void setLoading(boolean loading) {
		if (loadingContainer != null)
			loadingContainer.setVisible(loading);
	}

	@Override
	public void displayUser(User utilisateur) {
		if (utilisateur == null) {
			// settingPanel.setVisible(false);
		} else {
			// settingPanel.setVisible(true);
			user.setText(utilisateur.getLogin());
		}

	}

	@Override
	public void displaySyntheseMenuItem() {
		navfleche.getStyle().setLeft(265, Unit.PX);
	}

	@Override
	public void displayRecetteMenuItem() {
		navfleche.getStyle().setLeft(415, Unit.PX);
	}

	@Override
	public void displayDepenseMenuItem() {
		navfleche.getStyle().setLeft(565, Unit.PX); // 495px => 704
	}

	@Override
	public void displaySoldeMenuItem() {
		navfleche.getStyle().setLeft(715, Unit.PX);
	}

	@Override
	public void displayDefaultMenuItem() {
		navfleche.getStyle().setLeft(34, Unit.PX);
	}
}
