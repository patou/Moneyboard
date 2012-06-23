package com.sfeir.common.gwt.sample.moneyboard.client.layout;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.sfeir.common.gwt.client.layout.AbstractLayoutView;
import com.sfeir.common.gwt.client.mvp.View;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.User;

public interface LayoutView extends View, AcceptsOneWidget, AbstractLayoutView {
	void setPresenter(Presenter presenter);

	void setErrorMessage(String error);

	void setLoading(boolean loading);

	void displayUser(User utilisateur);

	void displayRecetteMenuItem();

	void displayDepenseMenuItem();

	void displaySoldeMenuItem();

	void displayDefaultMenuItem();

	public interface Presenter {
		void logout();

		void onLoadComplete();

		void onMenuDashboard();

		void onMenuDepense();

		void onMenuSolde();

		void onMenuRecette();
	}

	void displaySyntheseMenuItem();
}
