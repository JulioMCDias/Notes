package com.jlmcdeveloper.notes.di.component;

import android.content.Context;

import com.jlmcdeveloper.notes.data.AppManagerLocalTest;
import com.jlmcdeveloper.notes.data.AppManagerNetworkTest;
import com.jlmcdeveloper.notes.data.DataManager;
import com.jlmcdeveloper.notes.di.ContextApplication;
import com.jlmcdeveloper.notes.di.module.ActivityModule;
import com.jlmcdeveloper.notes.di.module.ApiRemoteModuleTest;
import com.jlmcdeveloper.notes.di.module.ApplicationTestModule;
import com.jlmcdeveloper.notes.di.module.LocalDatabaseTestModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationTestModule.class, ApiRemoteModuleTest.class, LocalDatabaseTestModule.class, ActivityModule.class})
public interface TestComponent extends ApplicationComponent {

    void inject(AppManagerNetworkTest appManagerNetworkTest);

    void inject(AppManagerLocalTest appManagerLocalTest);


    @ContextApplication
    Context context();

    DataManager getDataManager();
}
