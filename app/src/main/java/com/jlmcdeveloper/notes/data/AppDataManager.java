package com.jlmcdeveloper.notes.data;

import com.jlmcdeveloper.notes.data.db.AppObjectBox;
import com.jlmcdeveloper.notes.data.model.Note;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.objectbox.Box;


@Singleton
public class AppDataManager implements DataManager {

    private final AppObjectBox objectBox;

    @Inject
    AppDataManager(AppObjectBox objectBox){
        this.objectBox = objectBox;
    }


    @Override
    public Box<Note> getAllNotes() {
        return objectBox.getBox().boxFor(Note.class);
    }
}
