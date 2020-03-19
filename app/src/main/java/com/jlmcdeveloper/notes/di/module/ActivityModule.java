package com.jlmcdeveloper.notes.di.module;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.jlmcdeveloper.notes.di.ContextActivity;
import com.jlmcdeveloper.notes.di.PerActivity;
import com.jlmcdeveloper.notes.ui.login.LoginMvpPresenter;
import com.jlmcdeveloper.notes.ui.login.LoginMvpView;
import com.jlmcdeveloper.notes.ui.login.LoginPresenter;
import com.jlmcdeveloper.notes.ui.main.MainMvpPresenter;
import com.jlmcdeveloper.notes.ui.main.MainMvpView;
import com.jlmcdeveloper.notes.ui.main.MainPresenter;
import com.jlmcdeveloper.notes.ui.note.NoteMvpPresenter;
import com.jlmcdeveloper.notes.ui.note.NoteMvpView;
import com.jlmcdeveloper.notes.ui.note.NotePresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {

    private AppCompatActivity activity;

    public ActivityModule(AppCompatActivity appCompatActivity){
        this.activity = appCompatActivity;
    }

    public ActivityModule(){}

    @Provides
    @ContextActivity
    Context provideContext(){
        return activity;
    }

    @Provides
    AppCompatActivity provideActivity(){
        return activity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    MainMvpPresenter<MainMvpView> provideMainPresenter(MainPresenter<MainMvpView> presenter){
        return presenter;
    }

    @Provides
    NoteMvpPresenter<NoteMvpView> provideNotePresenter(NotePresenter<NoteMvpView> presenter){
        return presenter;
    }

    @Provides
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(LoginPresenter<LoginMvpView> presenter){
        return presenter;
    }
}
