package com.jlmcdeveloper.notes.ui.note;

import com.jlmcdeveloper.notes.data.DataManager;
import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.ui.base.BasePresenter;
import com.jlmcdeveloper.notes.utils.Constants;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class NotePresenter<V extends NoteMvpView> extends BasePresenter<V> implements NoteMvpPresenter<V> {

    private Note note;

    @Inject
    public NotePresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }



    @Override
    public void setIdNote(Long id) {
        if(!id.equals(Constants.ID_NEW_NOTE)){
            for (Note n : getDataManager().getNotes()) {
                if(n.getNoteID().equals(id)) {
                    setNoteView(n);
                    return;
                }
            }
        }
    }



    private void setNoteView(Note note){
        this.note = note;
        getMvpView().setNoteTitle(note.getTitle());
        getMvpView().setNoteDescription(note.getDescription());
    }



    @Override
    public void save() {
        if(note == null)
            createNote();
        else
            updateNote();

        getMvpView().close();
    }

    private void updateNote(){
        note.setTitle(getMvpView().getNoteTitle());
        note.setDescription(getMvpView().getNoteDescription());
        getCompositeDisposable().add(getDataManager()
                .updateNoteRemote(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((note1, throwable) -> {
                    if(note1 == null || note1.getNoteID() == -1)
                        getMvpView().setMessage("Erro ao Atualizar");
                }));

        getDataManager().updateNoteLocal(note);
    }

    private void createNote(){
        note = new Note();
        note.setTitle(getMvpView().getNoteTitle());
        note.setDescription(getMvpView().getNoteDescription());
        note.setUserID(getDataManager().getUser().getId());
        getCompositeDisposable().add(getDataManager()
                .createNoteRemote(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((note1, throwable) -> {
                    if(note1 == null || note1.getNoteID() == -1)
                        getMvpView().setMessage("Erro ao Salvar");
                }));

        getCompositeDisposable().add(getDataManager()
                .createNoteLocal(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    @Override
    public void delete() {
        if(note != null) {
            getCompositeDisposable().add(getDataManager()
                    .deleteNoteRemote(note)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((note1, throwable) -> {
                        if(note1 == null || note1.getNoteID() == -1)
                            getMvpView().setMessage("Erro ao gravar");
                    }));

            getCompositeDisposable().add(getDataManager()   //local
                    .deleteNoteLocal(note)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe());
        }

        getMvpView().close();
    }

    @Override
    public void cancel() {
        getMvpView().close();
    }
}
