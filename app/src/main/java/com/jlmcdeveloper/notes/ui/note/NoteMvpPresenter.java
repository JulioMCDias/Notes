package com.jlmcdeveloper.notes.ui.note;

import com.jlmcdeveloper.notes.ui.base.MvpPresenter;

public interface NoteMvpPresenter<V extends NoteMvpView> extends MvpPresenter<V> {

    void setIdNote(Long id);

    void save();

    void delete();

    void cancel();
}
