package com.jlmcdeveloper.notes.di.module;

import android.content.Context;

import com.jlmcdeveloper.notes.data.local.NotesRoomDatabase;
import com.jlmcdeveloper.notes.di.ContextApplication;
import com.jlmcdeveloper.notes.utils.Constants;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalDatabaseTestModule {

    @Provides
    String provideDatabaseName(){
        return "note_db_test";
    }

    @Provides
    NotesRoomDatabase provideNotesRoomDatabase(@ContextApplication Context context, String databaseName){
        return NotesRoomDatabase.getDatabase(context, databaseName);
    }
}
