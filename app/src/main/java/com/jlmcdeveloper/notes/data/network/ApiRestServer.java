package com.jlmcdeveloper.notes.data.network;


import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiRestServer {

    @POST("package/ctrl/CtrlLogin.php")
    Call<User> setLogin(@Query("method") String method, @Body User loginRequest);

    @POST("package/ctrl/CtrlNotes.php")
    Call<List<Note>> getNotes(@Query("method") String method, @Body User loginResponse);

    @POST("package/ctrl/CtrlNotes.php")
    Call<Note> setNote(@Query("method") String method, @Body Note noteRequest);

}
