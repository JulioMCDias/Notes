package com.jlmcdeveloper.notes.ui.note;

import com.jlmcdeveloper.notes.data.DataManager;
import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.ui.base.BasePresenter;
import com.jlmcdeveloper.notes.utils.Constants;

import javax.inject.Inject;

public class NotePresenter<V extends NoteMvpView> extends BasePresenter<V> implements NoteMvpPresenter<V> {

    private Note note;

    @Inject
    public NotePresenter(DataManager dataManager) {
        super(dataManager);
    }



    @Override
    public void setIdNote(Long id) {
        if(!id.equals(Constants.ID_NEW_NOTE))
            setNoteView(getDataManager().getAllNotes().get(id));
    }



    private void setNoteView(Note note){
        this.note = note;
        getMvpView().setNoteTitle(note.getTitle());
        getMvpView().setNoteDescription(note.getDescription());
    }



    @Override
    public void save() {
        if(note == null)
            note = new Note();
        note.setTitle(getMvpView().getNoteTitle());
        note.setDescription(getMvpView().getNoteDescription());
        //salvar no dataManager
        getDataManager().getAllNotes().put(note);
        getMvpView().close();
    }

    @Override
    public void delete() {
        if(note != null)
            getDataManager().getAllNotes().remove(note);
        getMvpView().close();
    }

    @Override
    public void cancel() {
        getMvpView().close();
    }
}
