package com.jlmcdeveloper.notes.di.component;

import android.content.Context;

import com.jlmcdeveloper.notes.AndroidApplication;
import com.jlmcdeveloper.notes.data.DataManager;
import com.jlmcdeveloper.notes.di.ContextApplication;
import com.jlmcdeveloper.notes.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(AndroidApplication androidApplication);

    @ContextApplication
    Context context();

    DataManager getDataManager();
}
