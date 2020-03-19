package com.jlmcdeveloper.notes.data.network;

import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.data.model.User;

import java.util.List;

import io.reactivex.Single;

public interface ManagerNetwork {

    Single<User> setLoginRemote(User user);

    Single<User> createLoginRemote(User user);

    Single<List<Note>> getAllNotesRemote();

    Single<Note> createNoteRemote(Note note);

    Single<Note> updateNoteRemote(Note note);

    Single<Note> deleteNoteRemote(Note note);
}
