package com.jlmcdeveloper.notes.ui.main;

import com.jlmcdeveloper.notes.RxImmediateSchedulerRule;
import com.jlmcdeveloper.notes.data.DataManager;
import com.jlmcdeveloper.notes.data.model.Note;
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

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock DataManager mockDataManager;
    @Mock MainMvpView mockMainMVPView;
    private MainPresenter<MainMvpView> mainPresenter;

    @ClassRule
    public static final RxImmediateSchedulerRule schedulers = new RxImmediateSchedulerRule();

    @Before
    public void setUp() throws Exception {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        mainPresenter = new MainPresenter<>(mockDataManager, compositeDisposable);
        mainPresenter.onAttach(mockMainMVPView);
    }

    @After
    public void tearDown() throws Exception {
        mainPresenter.onDetach();
    }



    @Test
    public void testSearchNotesSuccessAll(){
        List<Note> notesLocal = new ArrayList<>(Arrays.asList(
                new Note(1L, 1L, "title1", "description1"),
                new Note(3L, 1L, "title3", "description3")));

        List<Note> notesRemote = new ArrayList<>(Arrays.asList(
                new Note(5L, 1L, "title5", "description5"),
                new Note(3L, 1L, "title3", "description3"),
                new Note(6L, 1L, "title6", "description6")));

        List<Note> notes = new ArrayList<>(Arrays.asList(
                new Note(1L, 1L, "title1", "description1"),
                new Note(3L, 1L, "title3", "description3"),
                new Note(5L, 1L, "title5", "description5"),
                new Note(6L, 1L, "title6", "description6")));

        doReturn(Maybe.just(notesLocal))
                .when(mockDataManager)
                .getAllNotesLocal();

        doReturn(Single.just(notesRemote))
                .when(mockDataManager)
                .getAllNotesRemote();

        mainPresenter.searchNotes();

        verify(mockMainMVPView).showLoading();
        verify(mockMainMVPView).hideLoading();
        verify(mockMainMVPView).updateNotes();
        assertArrayEquals("listas diferentes", mainPresenter.getNotes().toArray(), notes.toArray());
    }


    @Test
    public void testSearchNotesSuccessLocal(){
        List<Note> notes = new ArrayList<>(Arrays.asList(
                new Note(1L, 1L, "title1", "description1"),
                new Note(2L, 1L, "title2", "description2"),
                new Note(3L, 1L, "title3", "description3")));

        doReturn(Maybe.just(new ArrayList()))
                .when(mockDataManager)
                .getAllNotesLocal();

        doReturn(Single.just(notes))
                .when(mockDataManager)
                .getAllNotesRemote();

        mainPresenter.searchNotes();

        verify(mockMainMVPView).showLoading();
        verify(mockMainMVPView).hideLoading();
        verify(mockMainMVPView).updateNotes();
        assertArrayEquals("listas diferentes",notes.toArray(), mainPresenter.getNotes().toArray());
    }

    @Test
    public void testSearchNotesExceptionRemote(){
        List<Note> notes = new ArrayList<>(Arrays.asList(
                new Note(1L, 1L, "title1", "description1"),
                new Note(2L, 1L, "title2", "description2"),
                new Note(3L, 1L, "title3", "description3")));

        doReturn(Maybe.just(notes))
                .when(mockDataManager)
                .getAllNotesLocal();

        doReturn(Single.error(new Throwable()))
                .when(mockDataManager)
                .getAllNotesRemote();

        mainPresenter.searchNotes();

        verify(mockMainMVPView).showLoading();
        verify(mockMainMVPView).hideLoading();
        verify(mockMainMVPView).updateNotes();
        assertArrayEquals("listas diferentes",notes.toArray(), mainPresenter.getNotes().toArray());
    }

    @Test
    public void testSearchNotesSuccessRemote(){
        List<Note> notes = new ArrayList<>(Arrays.asList(
                new Note(1L, 1L, "title1", "description1"),
                new Note(2L, 1L, "title2", "description2"),
                new Note(3L, 1L, "title3", "description3")));

        doReturn(Maybe.just(notes))
                .when(mockDataManager)
                .getAllNotesLocal();

        doReturn(Single.just(new ArrayList()))
                .when(mockDataManager)
                .getAllNotesRemote();

        mainPresenter.searchNotes();

        verify(mockMainMVPView).showLoading();
        verify(mockMainMVPView).hideLoading();
        verify(mockMainMVPView).updateNotes();
        assertArrayEquals("listas diferentes",notes.toArray(), mainPresenter.getNotes().toArray());
    }



    @Test
    public void testSearchNotesErrorAll(){
        doReturn(Maybe.just(new ArrayList()))
                .when(mockDataManager)
                .getAllNotesLocal();

        doReturn(Single.just(new ArrayList()))
                .when(mockDataManager)
                .getAllNotesRemote();

        mainPresenter.searchNotes();

        verify(mockMainMVPView).showLoading();
        verify(mockMainMVPView).hideLoading();
        verify(mockMainMVPView).updateNotes();
        assertArrayEquals("listas diferentes", new ArrayList().toArray(), mainPresenter.getNotes().toArray());
    }



    @Test
    public void testEditNote(){
        Note note = new Note(1L, 1L, "title", "description");
        mainPresenter.editNote(note);
        verify(mockMainMVPView).openNoteActivity(note.getNoteID());
    }

    @Test
    public void testCreateNote(){
        mainPresenter.createNote();
        verify(mockMainMVPView).openNoteActivity(Constants.ID_NEW_NOTE);
    }
}