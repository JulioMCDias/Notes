package com.jlmcdeveloper.notes.ui.note;

import com.jlmcdeveloper.notes.ui.base.MvpPresenter;

public interface NoteMvpPresenter<V extends NoteMvpView> extends MvpPresenter<V> {

    void setIdNote(int id);

    void save();

    void cancel();
}
