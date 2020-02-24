package com.jlmcdeveloper.notes.ui.note;

import com.jlmcdeveloper.notes.data.DataManager;
import com.jlmcdeveloper.notes.data.Listener;
import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.ui.base.BasePresenter;
import com.jlmcdeveloper.notes.utils.Constants;

import javax.inject.Inject;

public class NotePresenter<V extends NoteMvpView> extends BasePresenter<V> implements NoteMvpPresenter<V> {

    private Note note;
    private Listener.Note noteListener;
    private Listener.ResponseError responseError;

    @Inject
    public NotePresenter(DataManager dataManager) {
        super(dataManager);
        noteListener = note1 -> {
            if(note1.getNoteID() == -1)
                getMvpView().setMessage("Erro ao gravar");

        };

        responseError = error -> {
            if(!error)
                getMvpView().setMessage("Erro ao deletar");
        };
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
        getDataManager().updateNote(note, noteListener);
    }

    private void createNote(){
        note = new Note();
        note.setTitle(getMvpView().getNoteTitle());
        note.setDescription(getMvpView().getNoteDescription());
        note.setUserID(getDataManager().getUser().getId());
        getDataManager().createNote(note, noteListener);
    }

    @Override
    public void delete() {
        if(note != null)
            getDataManager().deleteNote(note, responseError);
        getMvpView().close();
    }

    @Override
    public void cancel() {
        getMvpView().close();
    }
}
