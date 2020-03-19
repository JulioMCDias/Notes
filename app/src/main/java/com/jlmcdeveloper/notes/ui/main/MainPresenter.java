package com.jlmcdeveloper.notes.ui.main;

import com.jlmcdeveloper.notes.data.DataManager;
import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.ui.base.BasePresenter;
import com.jlmcdeveloper.notes.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {
    private List<Note> notes;
    @Inject
    public MainPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
        notes = dataManager.getNotes();
    }


    @Override
    public void searchNotes() {
        getMvpView().showLoading();

        getCompositeDisposable().add(Flowable.concat(
                getDataManager().getAllNotesLocal()         //local database
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .toFlowable(),

                getDataManager().getAllNotesRemote()        //remote database
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .toFlowable()
        )
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(notes1 -> {
                            for (Note note : notes1) {
                                if(!notes.contains(note))
                                    notes.add(note);
                            }
                }, throwable-> {
                    getMvpView().displayError("erro network");
                    getMvpView().hideLoading();
                    getMvpView().updateNotes();
                }, () ->{
                    getMvpView().hideLoading();
                    getMvpView().updateNotes();
                }));
    }

    @Override
    public void createNote() {
        getMvpView().openNoteActivity(Constants.ID_NEW_NOTE);
    }

    @Override
    public void editNote(Note note) {
        getMvpView().openNoteActivity(note.getNoteID());
    }

    public List<Note> getNotes() {
        return notes;
    }
}
