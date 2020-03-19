package com.jlmcdeveloper.notes.di.component;


import com.jlmcdeveloper.notes.di.PerActivity;
import com.jlmcdeveloper.notes.di.module.ActivityModule;
import com.jlmcdeveloper.notes.di.module.LocalDatabaseModule;
import com.jlmcdeveloper.notes.ui.login.LoginActivity;
import com.jlmcdeveloper.notes.ui.main.MainActivity;
import com.jlmcdeveloper.notes.ui.note.NoteActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(NoteActivity noteActivity);

    void inject(LoginActivity loginActivity);
}
