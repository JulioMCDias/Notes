package com.jlmcdeveloper.notes.data;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.google.gson.internal.LinkedTreeMap;
import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.data.model.User;
import com.jlmcdeveloper.notes.data.network.ApiEndPoint;
import com.jlmcdeveloper.notes.data.network.GsonRequest;
import com.jlmcdeveloper.notes.data.network.VolleyRequestQueue;
import com.jlmcdeveloper.notes.di.ContextApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class AppDataManager implements DataManager {
    private final VolleyRequestQueue volleyRequestQueue;
    private final Context context;
    private List<Note> notes;
    private User user;

    @Inject
    AppDataManager(VolleyRequestQueue volleyRequestQueue, @ContextApplication Context context){
        this.volleyRequestQueue = volleyRequestQueue;
        this.context = context;
    }





    @Override
    public void setLogin(User user, Listener.Login lisUser) {
        this.user = user;

        HashMap<String, Object> params = new HashMap<>();
        params.put(ApiEndPoint.method, ApiEndPoint.setLogin);
        params.put("user", user);

        GsonRequest<User> gsonRequest = new GsonRequest<>(ApiEndPoint.urlSetLogin, User.class, params,
                response -> {
                    if(response != null) {
                        user.setId(response.getId());
                        user.setName(response.getName());
                        user.setEmail(response.getPassword());
                    }else
                        Toast.makeText(context ,"Erro ", Toast.LENGTH_SHORT).show();
                    lisUser.handle(user);

                }, error -> {

                    if (error instanceof NetworkError)
                        Toast.makeText(context, "No network available", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                    lisUser.handle(user);
                });

        volleyRequestQueue.getRequestQueue().add(gsonRequest);
    }

    @Override
    public void createLogin(User user, Listener.Login lisUser) {

    }


    @Override
    public List<Note> getNotesServer(Listener.ResponseError responseError) {
        notes = new ArrayList<>();

        HashMap<String, Object> params = new HashMap<>();
        params.put(ApiEndPoint.method, ApiEndPoint.getAllNotes);
        params.put("user", user);

        GsonRequest<List> gsonRequest = new GsonRequest<>(ApiEndPoint.urlGetNotes, List.class, params,
                response -> {
                    if (response != null) {
                        for ( Object o : response) {
                            Note n = new Note();
                            n.setTitle((String) ((LinkedTreeMap) o).get("title"));
                            n.setDescription((String) ((LinkedTreeMap) o).get("description"));
                            n.setNoteID( Long.valueOf((String) ((LinkedTreeMap) o).get("noteID")));
                            n.setUserID( Long.valueOf((String) ((LinkedTreeMap) o).get("userID")));
                            notes.add(n);
                            responseError.handle(true);
                        }

                    } else {
                        Toast.makeText(context, "Erro", Toast.LENGTH_SHORT).show();
                        responseError.handle(true);
                    }

                }, error -> {
                    Toast.makeText(context ,"Erro : " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    responseError.handle(true);
                });
        volleyRequestQueue.getRequestQueue().add(gsonRequest);

        return notes;
    }



    @Override
    public void createNote(Note note, Listener.Note lisNote) {

        HashMap<String, Object> params = new HashMap<>();
        params.put(ApiEndPoint.method, ApiEndPoint.createNote);
        params.put("note", note);

        GsonRequest<Note> gsonRequest = new GsonRequest<>(ApiEndPoint.urlSetNotes, Note.class, params,
                response -> {
                    if(response != null) {
                        lisNote.handle(response);
                    }else
                        Toast.makeText(context ,"Erro ", Toast.LENGTH_SHORT).show();

                }, error -> {
            if (error instanceof NetworkError)
                Toast.makeText(context, "No network available", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
        });

        volleyRequestQueue.getRequestQueue().add(gsonRequest);
    }


    @Override
    public void updateNote(Note note, Listener.Note lisNote) {

        HashMap<String, Object> params = new HashMap<>();
        params.put(ApiEndPoint.method, ApiEndPoint.updateNote);
        params.put("note", note);

        GsonRequest<Note> gsonRequest = new GsonRequest<>(ApiEndPoint.urlSetNotes, Note.class, params,
                response -> {
                    if(response != null) {
                        lisNote.handle(response);
                    }else
                        Toast.makeText(context ,"Erro ", Toast.LENGTH_SHORT).show();

                }, error -> {
            if (error instanceof NetworkError)
                Toast.makeText(context, "No network available", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
        });

        volleyRequestQueue.getRequestQueue().add(gsonRequest);
    }

    @Override
    public void deleteNote(Note note, Listener.ResponseError responseError) {

        HashMap<String, Object> params = new HashMap<>();
        params.put(ApiEndPoint.method, ApiEndPoint.deleteNote);
        params.put("note", note);

        GsonRequest<Note> gsonRequest = new GsonRequest<>(ApiEndPoint.urlSetNotes, Note.class, params,
                response -> {
                    if(response != null)
                        responseError.handle(true);
                    else {
                        Toast.makeText(context ,"Erro na comunicação", Toast.LENGTH_SHORT).show();
                        responseError.handle(false);
                    }

                }, error -> {
            if (error instanceof NetworkError)
                Toast.makeText(context, "No network available", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
        });

        volleyRequestQueue.getRequestQueue().add(gsonRequest);
    }


    public List<Note> getNotes() {
        return notes;
    }

    public User getUser() {
        return user;
    }
}
