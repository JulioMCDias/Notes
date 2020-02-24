package com.jlmcdeveloper.notes.data.network;


import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.data.model.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Singleton
public class AppApiRetrofit implements ApiRetrofit {

    private ApiRestServer apiRestServer;

    @Inject
    public AppApiRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiEndPoint.url)
                .build();

        apiRestServer = retrofit.create(ApiRestServer.class);
    }


    @Override
    public Observable<User> setLogin(User loginRequest) {
        return apiRestServer.setLogin(ApiEndPoint.setLogin, loginRequest);
    }

    @Override
    public Observable<List<Note>> getNotes(User loginResponse) {
        return apiRestServer.getNotes(ApiEndPoint.getAllNotes, loginResponse);
    }

    @Override
    public Observable<Note> createNote(Note noteRequest) {
        return apiRestServer.setNote(ApiEndPoint.createNote, noteRequest);
    }

    @Override
    public Observable<Note> updateNote(Note noteRequest) {
        return apiRestServer.setNote(ApiEndPoint.updateNote, noteRequest);
    }

    @Override
    public Observable<Note> deleteNote(Note noteRequest) {
        return apiRestServer.setNote(ApiEndPoint.deleteNote, noteRequest);
    }
}
