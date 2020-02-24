package com.jlmcdeveloper.notes.data.network;


import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.data.model.User;

import java.util.List;

import retrofit2.Call;

public interface ApiRetrofit {

    Call<User> setLogin(User user);

    Call<List<Note>> getNotes(User user);

    Call<Note> createNote(Note note);

    Call<Note> updateNote(Note note);

    Call<Note> deleteNote(Note note);
}
