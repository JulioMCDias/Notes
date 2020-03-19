package com.jlmcdeveloper.notes.ui.login;


import android.util.Log;

import com.jlmcdeveloper.notes.data.DataManager;
import com.jlmcdeveloper.notes.data.model.User;
import com.jlmcdeveloper.notes.ui.base.BasePresenter;

import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {


    @Inject
    public LoginPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }


    @Override
    public void setUser(String name, String password) {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .setLoginRemote(new User(name, password))
                .doOnSuccess(user -> getDataManager().setUser(user))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((user, throwable) -> {
                    if(user != null && user.getId() != -1){
                        getMvpView().hideLoading();
                        createUserLocal(name, password);
                        getMvpView().openMainActivity();
                    } else
                        setUserLocal(name, password);
                }));

    }

    // buscar usuario em database local
    private void setUserLocal(String name, String password){
        getCompositeDisposable().add(getDataManager()
                .setLoginLocal(new User(name, password))
                .doOnSuccess(user -> getDataManager().setUser(user))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user-> {
                    if(user != null && user.getId() != -1){
                        createUser(name, password);     // cria usuario remoto
                        getMvpView().hideLoading();
                        getMvpView().openMainActivity();
                    } else
                        getMvpView().finishedAnimation("erro no login");
                }, throwable -> getMvpView().finishedAnimation("erro no login")));

    }

    @Override
    public void createUser(String name, String password) {
        getMvpView().showLoading();
        createUserLocal(name, password);        // cria usuario local

        getCompositeDisposable().add(getDataManager()
                .createLoginRemote(new User(name, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user->{
                    if(user != null && user.getId() != -1)
                        getMvpView().hideLoading();
                     else
                        getMvpView().finishedAnimation("erro no login");
                },throwable -> getMvpView().finishedAnimation("Erro na comunicação")));
    }




    private void createUserLocal(String name, String password){
        getCompositeDisposable().add(getDataManager()
                .createLoginLocal(new User(null, name, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorComplete(throwable -> {
                    getMvpView().displayError("erro ao criar usuario local");
                    return true;
                }).subscribe(() -> getMvpView().finishedAnimation("usuario criado")));
    }
}
