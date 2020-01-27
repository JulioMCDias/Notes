package com.jlmcdeveloper.notes.ui.main;

import com.jlmcdeveloper.notes.data.DataManager;
import com.jlmcdeveloper.notes.ui.base.BasePresenter;

import javax.inject.Inject;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {

    @Inject
    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }




}
