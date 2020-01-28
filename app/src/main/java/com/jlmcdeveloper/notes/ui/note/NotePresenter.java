package com.jlmcdeveloper.notes.ui.note;

import com.jlmcdeveloper.notes.data.DataManager;
import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.ui.base.BasePresenter;
import com.jlmcdeveloper.notes.util.Constants;

import javax.inject.Inject;

public class NotePresenter<V extends NoteMvpView> extends BasePresenter<V> implements NoteMvpPresenter<V> {

    private Note note;

    @Inject
    public NotePresenter(DataManager dataManager) {
        super(dataManager);

    }



    @Override
    public void setIdNote(int id) {
        if(id != Constants.ID_NEW_NOTE){
            setNoteView(getDataManager().getAllNotes().get(id));
        }else
            note = new Note();
    }



    private void setNoteView(Note note){
        this.note = note;
        getMvpView().setNoteTitle(note.getTitle());
        getMvpView().setNoteDescription(note.getDescription());
    }



    @Override
    public void save() {
        note.setTitle(getMvpView().getNoteTitle());
        note.setDescription(getMvpView().getNoteDescription());

        //salvar no dataManager

        getMvpView().close();
    }

    @Override
    public void cancel() {
        getMvpView().close();
    }
}
