package com.jlmcdeveloper.notes.data;

import com.jlmcdeveloper.notes.data.local.NotesRoomDatabase;
import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.data.model.User;
import com.jlmcdeveloper.notes.data.network.ApiManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


@Singleton
public class AppDataManager implements DataManager {
    private final ApiManager apiManager;
    private NotesRoomDatabase roomDatabase;
    private List<Note> notes;
    private User user;

    @Inject
    AppDataManager(ApiManager apiManager, NotesRoomDatabase notesRoomDatabase){
        this.apiManager = apiManager;
        this.roomDatabase = notesRoomDatabase;
        notes = new ArrayList<>();

    }


    @Override
    public List<Note> getNotes() {
        return notes;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }




    // ----- remote -------
    @Override
    public void setNotes(List<Note> notes1){
        notes.addAll(notes1);
    }

    @Override
    public Single<User> setLoginRemote(User user) {
        this.user = user;
       return apiManager.setLogin(user);
    }

    @Override
    public Single<User> createLoginRemote(User user) {
        return apiManager.createLogin(user);
    }


    @Override
    public Single<List<Note>> getAllNotesRemote() {
        return apiManager.getNotes(user);
    }

    @Override
    public Single<Note> createNoteRemote(Note note) {
        return apiManager.createNote(note);
    }


    @Override
    public Single<Note> updateNoteRemote(Note note) {
        return apiManager.updateNote(note);
    }

    @Override
    public Single<Note> deleteNoteRemote(Note note) {
        return apiManager.deleteNote(note);
    }





    // ------------ Local --------
    @Override
    public Single<User> setLoginLocal(User user) {
        return roomDatabase.userDao().getUser(user.getName(), user.getPassword());
    }


    @Override
    public Completable createLoginLocal(User user) {
        return roomDatabase.userDao().insertUser(user);
    }

    @Override
    public Maybe<List<User>> getAllUserLocal() {
        return roomDatabase.userDao().getAllUser();
    }

    @Override
    public Single<Integer> deleteUserLocal(User user) {
        return roomDatabase.userDao().deleteUser(user);
    }


    @Override
    public Maybe<List<Note>> getAllNotesLocal() {
        return roomDatabase.noteDao().getAllNotes(user.getId());
    }

    @Override
    public Maybe<List<Note>> getAllNotesLocal(User user) {
        return roomDatabase.noteDao().getAllNotes(user.getId());
    }


    @Override
    public Completable createNoteLocal(Note note) {
        return roomDatabase.noteDao().insertNote(note);
    }

    @Override
    public Completable createAllNoteLocal(List<Note> notes){
        return roomDatabase.noteDao().insertAllNote(notes);
    }

    @Override
    public Single<Integer> updateNoteLocal(Note note) {
        return roomDatabase.noteDao().updateNote(note);
    }

    @Override
    public Single<Integer> deleteNoteLocal(Note note) {
        return roomDatabase.noteDao().deleteNote(note);
    }
}
