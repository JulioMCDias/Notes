package com.jlmcdeveloper.notes.ui.login;


import com.jlmcdeveloper.notes.data.DataManager;
import com.jlmcdeveloper.notes.data.Listener;
import com.jlmcdeveloper.notes.data.model.User;
import com.jlmcdeveloper.notes.ui.base.BasePresenter;

import javax.inject.Inject;


public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {

    private Listener.Login listenerLogin;

    @Inject
    public LoginPresenter(DataManager dataManager) {
        super(dataManager);

        listenerLogin = user -> {
            if(user.getId() == -1)
                getMvpView().finishedAnimation("erro no login");

            else{
                getMvpView().hideLoading();
                getMvpView().openMainActivity();
            }
        };
    }


    @Override
    public void setUser(String name, String password) {
        getMvpView().showLoading();
        getDataManager().setLogin(new User(name, password), listenerLogin);
    }


    @Override
    public void createUser(String name, String password) {
        getMvpView().showLoading();
        getDataManager().createLogin(new User(name, password), listenerLogin);
    }
}
