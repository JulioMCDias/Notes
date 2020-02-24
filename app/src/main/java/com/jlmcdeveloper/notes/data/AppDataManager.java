package com.jlmcdeveloper.notes.data;

import android.content.Context;
import android.widget.Toast;


import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.data.model.User;
import com.jlmcdeveloper.notes.data.network.ApiRetrofit;
import com.jlmcdeveloper.notes.data.network.AppApiRetrofit;
import com.jlmcdeveloper.notes.di.ContextApplication;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


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
        Observable<User> loginResponse = apiRetrofit.setLogin(user);
        loginResponse
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {

                               @Override
                               public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                               }

                               @Override
                               public void onNext(@io.reactivex.annotations.NonNull User user1) {
                                   if(user1 != null) {
                                       user.setId(user1.getId());
                                       user.setName(user1.getName());
                                       user.setEmail(user1.getEmail());
                                   }else
                                       Toast.makeText(context ,"Erro ", Toast.LENGTH_SHORT).show();
                                   lisUser.handle(user);
                               }

                               @Override
                               public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                                   Toast.makeText(context ,"Erro na comunicação", Toast.LENGTH_SHORT).show();
                                   lisUser.handle(user);
                               }

                               @Override
                               public void onComplete() {

                               }
                           });
    }

    @Override
    public void createLogin(User user, Listener.Login lisUser) {

    }


    @Override
    public List<Note> getNotesServer(Listener.ResponseError responseError) {
        notes = new ArrayList<>();
        Observable<List<Note>> noteResponses = apiRetrofit.getNotes(user);

        noteResponses
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Note>>() {
                            @Override
                            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@io.reactivex.annotations.NonNull List<Note> notes1) {
                                if(notes1 !=null) {
                                    notes.addAll(notes1);
                                    responseError.handle(false);
                                }else {
                                    Toast.makeText(context, "Erro" , Toast.LENGTH_SHORT).show();
                                    responseError.handle(true);
                                }
                            }

                            @Override
                            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                                Toast.makeText(context ,"Erro : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                responseError.handle(true);
                            }

                            @Override
                            public void onComplete() {

                            }
                        });

        return notes;
    }



    @Override
    public void createNote(Note note, Listener.Note lisNote) {
        Observable<Note> noteResponse = apiRetrofit.createNote(note);

        noteResponse
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Note>() {
                                @Override
                                public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                                }

                                @Override
                                public void onNext(@io.reactivex.annotations.NonNull Note note) {
                                    if(note != null)
                                        lisNote.handle(note);
                                    else
                                        Toast.makeText(context ,"Erro na comunicação", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                                    Toast.makeText(context ,"Erro :"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onComplete() {

                                }
                            });
    }


    @Override
    public void updateNote(Note note, Listener.Note lisNote) {
        Observable<Note> noteResponse = apiRetrofit.updateNote(note);
        noteResponse
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Note>() {
                            @Override
                            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                            }

                            @Override
                            public void onNext(@io.reactivex.annotations.NonNull Note note) {
                                if(note != null)
                                    lisNote.handle(note);
                                else
                                    Toast.makeText(context ,"Erro ", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                                Toast.makeText(context ,"Erro: "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
    }

    @Override
    public void deleteNote(Note note, Listener.ResponseError responseError) {
        Observable<Note> noteResponse = apiRetrofit.deleteNote(note);


        noteResponse
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Note>() {
                            @Override
                            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@io.reactivex.annotations.NonNull Note note) {
                                if(note != null)
                                    responseError.handle(true);
                                else {
                                    Toast.makeText(context ,"Erro na comunicação", Toast.LENGTH_SHORT).show();
                                    responseError.handle(false);
                                }
                            }

                            @Override
                            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                                Toast.makeText(context, "Erro :" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                responseError.handle(false);
                            }

                            @Override
                            public void onComplete() {

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
