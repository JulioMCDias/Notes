package com.jlmcdeveloper.notes.ui.main;

import com.jlmcdeveloper.notes.data.DataManager;
import com.jlmcdeveloper.notes.data.Listener;
import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.ui.base.BasePresenter;
import com.jlmcdeveloper.notes.utils.Constants;

import java.util.List;

import javax.inject.Inject;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {
    private Listener.ResponseError responseError;
    private List<Note> notes;
    @Inject
    public MainPresenter(DataManager dataManager) {
        super(dataManager);

        responseError = error -> {
            getMvpView().hideLoading();
            getMvpView().updateNotes(notes);
        };
    }


    @Override
    public void searchNotes() {
        getMvpView().showLoading();
        notes = getDataManager().getNotesServer(responseError);
    }

    @Override
    public void createNote() {
        getMvpView().openNoteActivity(Constants.ID_NEW_NOTE);
    }

    @Override
    public void editNote(Note note) {
        getMvpView().openNoteActivity(note.getNoteID());
    }
}
