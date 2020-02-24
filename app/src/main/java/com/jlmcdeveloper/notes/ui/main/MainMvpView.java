package com.jlmcdeveloper.notes.ui.main;

import com.jlmcdeveloper.notes.data.Listener;
import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.ui.base.MvpView;

import java.util.List;

public interface MainMvpView extends MvpView {

    void openNoteActivity(Long idNode);

    void updateNotes(List<Note> notes);
}
