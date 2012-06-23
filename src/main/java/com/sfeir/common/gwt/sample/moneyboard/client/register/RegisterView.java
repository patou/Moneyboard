package com.sfeir.common.gwt.sample.moneyboard.client.register;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sfeir.common.gwt.client.mvp.View;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.User;

public interface RegisterView extends View {
    void setPresenter(Presenter presenter);
    void setErrorState(boolean isError);
    String getLoginValue();
    String getPasswordValue();
    SimpleBeanEditorDriver<User, ?> getDriver();
    public interface Presenter {

	void onSubmit();
	
    }
}
