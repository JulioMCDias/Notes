package com.jlmcdeveloper.notes.ui.main;

import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.ui.base.MvpPresenter;

import java.util.List;

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    List<Note> getAllNotes();

    void createNote();

    void editNote(Note note);
}
