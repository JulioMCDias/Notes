package com.jlmcdeveloper.notes.data;


import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.data.model.User;

import org.json.JSONException;

import java.util.List;

public interface DataManager {

    void setLogin(User user, Listener.Login lisUser);

    void createLogin(User user, Listener.Login lisUser);

    List<Note> getNotesServer(Listener.ResponseError responseError);

    void createNote(Note note, Listener.Note lisNote);

    void updateNote(Note note, Listener.Note lisNote);

    void deleteNote(Note note, Listener.ResponseError responseError);

    List<Note> getNotes();

    User getUser();
}
