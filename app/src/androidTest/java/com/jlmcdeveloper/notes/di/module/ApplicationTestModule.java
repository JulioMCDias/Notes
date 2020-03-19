package com.jlmcdeveloper.notes.di.module;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import com.jlmcdeveloper.notes.data.AppDataManager;
import com.jlmcdeveloper.notes.data.DataManager;
import com.jlmcdeveloper.notes.data.local.ManagerLocal;
import com.jlmcdeveloper.notes.data.network.ManagerNetwork;
import com.jlmcdeveloper.notes.di.ContextApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationTestModule {
    private Application myApplication;


    public ApplicationTestModule(@NonNull Application application){
        myApplication = application;
    }

    @Provides
    @ContextApplication
    Context provideContext(){
        return myApplication;
    }

    @Provides
    @Singleton
    ManagerLocal provideManagerLocal(AppDataManager dataManager){
        return dataManager;
    }

    @Provides
    @Singleton
    ManagerNetwork provideManagerNetwork(AppDataManager dataManager){
        return dataManager;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager dataManager){
        return dataManager;
    }
}
