package com.jlmcdeveloper.notes.data.db;

import android.content.Context;

import com.jlmcdeveloper.notes.data.model.MyObjectBox;
import com.jlmcdeveloper.notes.di.ContextApplication;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.objectbox.BoxStore;

@Singleton
public class AppObjectBox implements ObjectBox {

    private final BoxStore boxStore;

    @Inject
    public AppObjectBox(@ContextApplication Context context) {
        this.boxStore = MyObjectBox.builder()
                .androidContext(context.getApplicationContext())
                .build();
    }

    @Override
    public BoxStore getBox() {
        return boxStore;
    }
}
