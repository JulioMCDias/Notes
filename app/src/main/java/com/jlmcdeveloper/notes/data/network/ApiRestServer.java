package com.jlmcdeveloper.notes.data.network;


import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.data.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiRestServer {

    @POST("package/ctrl/CtrlLogin.php")
    Observable<User> setLogin(@Query("method") String method, @Body User loginRequest);

    @POST("package/ctrl/CtrlNotes.php")
    Observable<List<Note>> getNotes(@Query("method") String method, @Body User loginResponse);

    @POST("package/ctrl/CtrlNotes.php")
    Observable<Note> setNote(@Query("method") String method, @Body Note noteRequest);

}
