package com.jlmcdeveloper.notes.ui.main;

import com.jlmcdeveloper.notes.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void openNoteActivity(Long idNode);

    void updateNotes();
}
