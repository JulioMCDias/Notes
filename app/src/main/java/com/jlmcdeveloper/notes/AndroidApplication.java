package com.jlmcdeveloper.notes;

import android.app.Application;

import com.jlmcdeveloper.notes.di.component.ApplicationComponent;
import com.jlmcdeveloper.notes.di.component.DaggerApplicationComponent;
import com.jlmcdeveloper.notes.di.module.ApplicationModule;

public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();


        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }


    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
