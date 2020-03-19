package com.jlmcdeveloper.notes.ui.base;

public interface MvpView {
    void showLoading();

    void hideLoading();

    void displayError(String info);
}
