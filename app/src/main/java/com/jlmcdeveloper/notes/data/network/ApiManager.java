package com.jlmcdeveloper.notes.data.network;

import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.data.model.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;

@Singleton
public class ApiManager {
    private ApiRestServer apiRestServer;

    @Inject
    public ApiManager(ApiRestServer apiRestServer) {
        this.apiRestServer = apiRestServer;
    }



    public Single<User> setLogin(User loginRequest) {
        return apiRestServer.setLogin(ApiEndPoint.setLogin, loginRequest);
    }


    public Single<User> createLogin(User loginRequest) {
        return apiRestServer.setLogin(ApiEndPoint.createLogin, loginRequest);
    }


    public Single<List<Note>> getNotes(User loginResponse) {
        return apiRestServer.getNotes(ApiEndPoint.getAllNotes, loginResponse);
    }


    public Single<Note> createNote(Note noteRequest) {
        return apiRestServer.setNote(ApiEndPoint.createNote, noteRequest);
    }


    public Single<Note> updateNote(Note noteRequest) {
        return apiRestServer.setNote(ApiEndPoint.updateNote, noteRequest);
    }


    public Single<Note> deleteNote(Note noteRequest) {
        return apiRestServer.setNote(ApiEndPoint.deleteNote, noteRequest);
    }
}
