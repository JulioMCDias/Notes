package com.jlmcdeveloper.notes.ui.login;

import com.jlmcdeveloper.notes.ui.base.MvpView;

public interface LoginMvpView extends MvpView {

    void finishedAnimation(String info);

    void openMainActivity();
}
