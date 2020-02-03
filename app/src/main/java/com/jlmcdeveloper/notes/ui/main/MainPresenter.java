package com.jlmcdeveloper.notes.ui.main;

import com.jlmcdeveloper.notes.data.DataManager;
import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.ui.base.BasePresenter;
import com.jlmcdeveloper.notes.utils.Constants;

import java.util.List;

import javax.inject.Inject;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {

    @Inject
    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public List<Note> getAllNotes() {
        return getDataManager().getAllNotes().getAll();
    }

    @Override
    public void createNote() {
        getMvpView().openNoteActivity(Constants.ID_NEW_NOTE);
    }

    @Override
    public void editNote(Note note) {
        getMvpView().openNoteActivity(note.getId());
    }
}
