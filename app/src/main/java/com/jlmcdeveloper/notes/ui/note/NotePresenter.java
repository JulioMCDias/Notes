package com.jlmcdeveloper.notes.ui.note;

import com.jlmcdeveloper.notes.data.DataManager;
import com.jlmcdeveloper.notes.ui.base.BasePresenter;

import javax.inject.Inject;

public class NotePresenter<V extends NoteMvpView> extends BasePresenter<V> implements NoteMvpPresenter<V> {


    @Inject
    public NotePresenter(DataManager dataManager) {
        super(dataManager);

    }


}
