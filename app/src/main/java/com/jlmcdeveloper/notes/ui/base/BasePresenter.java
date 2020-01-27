package com.jlmcdeveloper.notes.ui.base;

import com.jlmcdeveloper.notes.data.DataManager;

import javax.inject.Inject;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V mvpView;
    private final DataManager dataManager;

    @Inject
    public BasePresenter(DataManager dataManager){
        this.dataManager = dataManager;
    }


    @Override
    public void onAttach(V mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mvpView = null;
    }


    public V getMvpView() {
        return mvpView;
    }

    public DataManager getDataManager() {
        return dataManager;
    }
}
