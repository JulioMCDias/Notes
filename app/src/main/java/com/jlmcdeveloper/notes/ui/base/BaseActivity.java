package com.jlmcdeveloper.notes.ui.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jlmcdeveloper.notes.AndroidApplication;
import com.jlmcdeveloper.notes.di.component.ActivityComponent;
import com.jlmcdeveloper.notes.di.component.DaggerActivityComponent;
import com.jlmcdeveloper.notes.di.module.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((AndroidApplication) getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();

    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }
}
