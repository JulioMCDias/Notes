package com.jlmcdeveloper.notes.data;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.data.model.User;
import com.jlmcdeveloper.notes.data.network.ApiRetrofit;
import com.jlmcdeveloper.notes.data.network.AppApiRetrofit;
import com.jlmcdeveloper.notes.di.ContextApplication;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@Singleton
public class AppDataManager implements DataManager {
    private final ApiRetrofit apiRetrofit;
    private final Context context;
    private List<Note> notes;
    private User user;

    @Inject
    AppDataManager(AppApiRetrofit apiRetrofit,@ContextApplication Context context){
        this.apiRetrofit = apiRetrofit;
        this.context = context;
    }





    @Override
    public void setLogin(User user, Listener.Login lisUser) {
        this.user = user;
        Call<User> loginResponse = apiRetrofit.setLogin(user);
        loginResponse.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if(response.body() !=null) {
                    user.setId(response.body().getId());
                    user.setName(response.body().getName());
                    user.setEmail(response.body().getEmail());
                }else
                    Toast.makeText(context ,"Erro :"+response.toString(), Toast.LENGTH_SHORT).show();
                lisUser.handle(user);
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                Toast.makeText(context ,"Erro na comunicação", Toast.LENGTH_SHORT).show();
                lisUser.handle(user);
            }
        });
    }

    @Override
    public void createLogin(User user, Listener.Login lisUser) {

    }


    @Override
    public List<Note> getNotesServer(Listener.ResponseError responseError) {
        notes = new ArrayList<>();
        Call<List<Note>> noteResponses = apiRetrofit.getNotes(user);

        noteResponses.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(@NonNull Call<List<Note>> call,@NonNull Response<List<Note>> response) {
                if(response.body() !=null) {
                    notes.addAll(response.body());
                    responseError.handle(false);
                }else {
                    Toast.makeText(context, "Erro :" + response.toString(), Toast.LENGTH_SHORT).show();
                    responseError.handle(true);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Note>> call, @NonNull Throwable t) {
                Toast.makeText(context ,"Erro na comunicação", Toast.LENGTH_SHORT).show();
                responseError.handle(true);
            }
        });
        return notes;
    }



    @Override
    public void createNote(Note note, Listener.Note lisNote) {
        Call<Note> noteResponse = apiRetrofit.createNote(note);
        noteResponse.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(@NonNull Call<Note> call, @NonNull Response<Note> response) {
                if(response.body() != null)
                    lisNote.handle(response.body());
                else
                    Toast.makeText(context ,"Erro :"+response.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<Note> call, @NonNull Throwable t) {
                Toast.makeText(context ,"Erro na comunicação", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void updateNote(Note note, Listener.Note lisNote) {
        Call<Note> noteResponse = apiRetrofit.updateNote(note);
        noteResponse.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(@NonNull Call<Note> call, @NonNull Response<Note> response) {
                if(response.body() != null)
                    lisNote.handle(response.body());
                else
                    Toast.makeText(context ,"Erro :"+response.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<Note> call, @NonNull Throwable t) {
                Toast.makeText(context ,"Erro na comunicação", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void deleteNote(Note note, Listener.ResponseError responseError) {
        Call<Note> noteResponse = apiRetrofit.deleteNote(note);
        noteResponse.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(@NonNull Call<Note> call, @NonNull Response<Note> response) {
                if(response.body() != null)
                    responseError.handle(true);
                else {
                    Toast.makeText(context, "Erro :" + response.toString(), Toast.LENGTH_SHORT).show();
                    responseError.handle(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Note> call, @NonNull Throwable t) {
                Toast.makeText(context ,"Erro na comunicação", Toast.LENGTH_SHORT).show();
                responseError.handle(false);
            }
        });
    }


    public List<Note> getNotes() {
        return notes;
    }

    public User getUser() {
        return user;
    }
}
