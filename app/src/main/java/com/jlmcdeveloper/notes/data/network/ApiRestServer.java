package com.jlmcdeveloper.notes.data.network;


import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.data.model.User;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiRestServer {

    @POST("peoples/")
    Single<User> setLogin(@Query("method") String method, @Body User loginRequest);

    @POST("notes/")
    Single<List<Note>> getNotes(@Query("method") String method, @Body User loginResponse);

    @POST("notes/")
    Single<Note> setNote(@Query("method") String method, @Body Note noteRequest);

}
