package com.sfeir.common.gwt.sample.moneyboard.client.login;

import com.sfeir.common.gwt.client.mvp.View;

public interface LoginView extends View {
    void setPresenter(Presenter presenter);
    void setErrorState(boolean isError);
    String getLoginValue();
    String getPasswordValue();
    public interface Presenter {

	void onSubmit();

	void onRegister();
	
    }
}
