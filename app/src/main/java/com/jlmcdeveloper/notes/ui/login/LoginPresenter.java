package com.jlmcdeveloper.notes.ui.login;

import com.jlmcdeveloper.notes.data.DataManager;
import com.jlmcdeveloper.notes.ui.base.BasePresenter;

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {

    public LoginPresenter(DataManager dataManager) {
        super(dataManager);
    }


}
