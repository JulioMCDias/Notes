package com.jlmcdeveloper.notes.di.module;


import android.app.Application;
import android.content.Context;

import com.jlmcdeveloper.notes.data.AppDataManager;
import com.jlmcdeveloper.notes.data.DataManager;
import com.jlmcdeveloper.notes.di.ContextApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application){
        this.application = application;
    }


    @ContextApplication
    @Provides
    Context provideContext(){
        return application;
    }


    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager dataManager){
        return dataManager;
    }

}
