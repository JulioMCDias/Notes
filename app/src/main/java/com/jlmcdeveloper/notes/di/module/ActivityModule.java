package com.jlmcdeveloper.notes.di.module;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.jlmcdeveloper.notes.di.ContextActivity;
import com.jlmcdeveloper.notes.di.PerActivity;
import com.jlmcdeveloper.notes.ui.main.MainMvpPresenter;
import com.jlmcdeveloper.notes.ui.main.MainMvpView;
import com.jlmcdeveloper.notes.ui.main.MainPresenter;
import com.jlmcdeveloper.notes.ui.note.NoteMvpPresenter;
import com.jlmcdeveloper.notes.ui.note.NoteMvpView;
import com.jlmcdeveloper.notes.ui.note.NotePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private AppCompatActivity activity;

    public ActivityModule(AppCompatActivity appCompatActivity){
        this.activity = appCompatActivity;
    }


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
    MainMvpPresenter<MainMvpView> provideMainPresenter(MainPresenter<MainMvpView> presenter){
        return presenter;
    }

    @Provides
    NoteMvpPresenter<NoteMvpView> provideNotePresenter(NotePresenter<NoteMvpView> presenter){
        return presenter;
    }

}
