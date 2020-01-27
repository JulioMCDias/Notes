package com.jlmcdeveloper.notes.di.component;

import com.jlmcdeveloper.notes.AndroidApplication;
import com.jlmcdeveloper.notes.data.DataManager;
import com.jlmcdeveloper.notes.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(AndroidApplication androidApplication);

    DataManager getDataManager();
}
