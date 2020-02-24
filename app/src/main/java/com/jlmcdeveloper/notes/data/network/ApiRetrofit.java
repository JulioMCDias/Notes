package com.jlmcdeveloper.notes.data.network;


import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.data.model.User;

import java.util.List;

import io.reactivex.Observable;



public interface ApiRetrofit {

    Observable<User> setLogin(User user);

    Observable<List<Note>> getNotes(User user);

    Observable<Note> createNote(Note note);

    Observable<Note> updateNote(Note note);

    Observable<Note> deleteNote(Note note);
}
