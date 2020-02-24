package com.jlmcdeveloper.notes.ui.login;

import com.jlmcdeveloper.notes.ui.base.MvpPresenter;

public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {

    void setUser(String name, String password);

    void createUser(String name, String password);
}
