package com.jlmcdeveloper.notes.di.component;

import android.content.Context;

import com.jlmcdeveloper.notes.AndroidApplication;
import com.jlmcdeveloper.notes.data.DataManager;
import com.jlmcdeveloper.notes.di.ContextApplication;
import com.jlmcdeveloper.notes.di.module.ApiRemoteModule;
import com.jlmcdeveloper.notes.di.module.ApplicationModule;
import com.jlmcdeveloper.notes.di.module.LocalDatabaseModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ApiRemoteModule.class, LocalDatabaseModule.class})
public interface ApplicationComponent {

    void inject(AndroidApplication androidApplication);

    @ContextApplication
    Context context();

    DataManager getDataManager();
}
