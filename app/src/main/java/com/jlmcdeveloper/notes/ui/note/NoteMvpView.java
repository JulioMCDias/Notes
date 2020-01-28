package com.jlmcdeveloper.notes.ui.note;

import com.jlmcdeveloper.notes.ui.base.MvpView;

public interface NoteMvpView extends MvpView {

    String getNoteTitle();
    String getNoteDescription();

    void setNoteTitle(String title);
    void setNoteDescription(String description);

    void close();
}
