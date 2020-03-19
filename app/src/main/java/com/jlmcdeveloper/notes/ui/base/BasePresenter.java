package com.jlmcdeveloper.notes.ui.base;

import com.jlmcdeveloper.notes.data.DataManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V mvpView;
    private final DataManager dataManager;
    private final CompositeDisposable compositeDisposable;

    @Inject
    public BasePresenter(DataManager dataManager, CompositeDisposable compositeDisposable){
        this.dataManager = dataManager;
        this.compositeDisposable = compositeDisposable;
    }


    @Override
    public void onAttach(V mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mvpView = null;
        compositeDisposable.dispose();
    }


    public V getMvpView() {
        return mvpView;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }
}
