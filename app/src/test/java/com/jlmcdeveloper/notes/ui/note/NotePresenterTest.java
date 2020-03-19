package com.jlmcdeveloper.notes.ui.note;

import com.jlmcdeveloper.notes.RxImmediateSchedulerRule;
import com.jlmcdeveloper.notes.data.DataManager;
import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.data.model.User;
import com.jlmcdeveloper.notes.utils.Constants;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class NotePresenterTest {

    @Mock DataManager mockDataManager;
    @Mock NoteMvpView mockNoteMvpView;
    @ClassRule
    public static final RxImmediateSchedulerRule schedulers = new RxImmediateSchedulerRule();

    private List<Note> notes = new ArrayList<>(Arrays.asList(
            new Note(1L, 1L, "title1", "description1"),
            new Note(2L, 1L, "title2", "description2"),
            new Note(3L, 1L, "title3", "description3")));

    private NotePresenter<NoteMvpView> notePresenter;

    @Before
    public void setUp() throws Exception {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        notePresenter = new NotePresenter<>(mockDataManager,compositeDisposable);
        notePresenter.onAttach(mockNoteMvpView);
    }

    @After
    public void tearDown() throws Exception {
        notePresenter.onDetach();
    }



    @Test
    public void testSetIdNoteExistent(){
        doReturn(notes).when(mockDataManager).getNotes();

        notePresenter.setIdNote(notes.get(0).getNoteID());

        verify(mockNoteMvpView).setNoteTitle(notes.get(0).getTitle());
        verify(mockNoteMvpView).setNoteDescription(notes.get(0).getDescription());
    }


    @Test
    public void testSetIdNoteNew(){
        notePresenter.setIdNote(Constants.ID_NEW_NOTE);
    }


    @Test
    public void testSaveUpdateSuccess() {
        testSetIdNoteExistent();

        Note note = new Note(1L, 1L, "title", "description");
        doReturn(Single.just(note))             // remote
                .when(mockDataManager)
                .updateNoteRemote(notes.get(0));

        doReturn(Single.just(1))        // local
                .when(mockDataManager)
                .updateNoteLocal(notes.get(0));

        notePresenter.save();

        verify(mockNoteMvpView).getNoteTitle();
        verify(mockNoteMvpView).getNoteDescription();
        verify(mockNoteMvpView).close();
    }

    @Test
    public void testSaveUpdateError() {
        testSetIdNoteExistent();

        doReturn(Single.error(new Throwable()))             // remote
                .when(mockDataManager)
                .updateNoteRemote(notes.get(0));

        doReturn(Single.just(1))        // local
                .when(mockDataManager)
                .updateNoteLocal(notes.get(0));

        notePresenter.save();

        verify(mockNoteMvpView).getNoteTitle();
        verify(mockNoteMvpView).getNoteDescription();
        verify(mockNoteMvpView).setMessage("Erro ao Atualizar");
        verify(mockNoteMvpView).close();
    }



    @Test
    public void testSaveNewSuccess() {
        Note note = new Note(1L, 1L, "title", "description");

        doReturn(Single.just(note))
                .when(mockDataManager)
                .createNoteRemote(any(Note.class));

        doReturn(Completable.complete())
                .when(mockDataManager)
                .createNoteLocal(any(Note.class));

        doReturn(new User(1L,"test", "test@test","test123"))
                .when(mockDataManager)
                .getUser();

        notePresenter.save();

        verify(mockNoteMvpView).getNoteTitle();
        verify(mockNoteMvpView).getNoteDescription();
        verify(mockNoteMvpView).close();
    }

    @Test
    public void testSaveNewError() {

        doReturn(Single.error(new Throwable()))
                .when(mockDataManager)
                .createNoteRemote(any(Note.class));

        doReturn(Completable.complete())
                .when(mockDataManager)
                .createNoteLocal(any(Note.class));

        doReturn(new User(1L,"test", "test@test","test123"))
                .when(mockDataManager)
                .getUser();

        notePresenter.save();

        verify(mockNoteMvpView).getNoteTitle();
        verify(mockNoteMvpView).getNoteDescription();
        verify(mockNoteMvpView).setMessage("Erro ao Salvar");
        verify(mockNoteMvpView).close();
    }


    @Test
    public void testDeleteSuccess(){
        testSetIdNoteExistent();

        doReturn(Single.just(notes.get(0)))
                .when(mockDataManager).deleteNoteRemote(notes.get(0));

        doReturn(Single.just(1))        // local
                .when(mockDataManager)
                .deleteNoteLocal(notes.get(0));

        notePresenter.delete();

        verify(mockNoteMvpView).close();
    }

    @Test
    public void testDeleteError(){
        testSetIdNoteExistent();

        doReturn(Single.error(new Throwable()))
                .when(mockDataManager).deleteNoteRemote(notes.get(0));

        doReturn(Single.just(1))        // local
                .when(mockDataManager)
                .deleteNoteLocal(notes.get(0));

        notePresenter.delete();

        verify(mockNoteMvpView).setMessage("Erro ao gravar");
        verify(mockNoteMvpView).close();
    }

}