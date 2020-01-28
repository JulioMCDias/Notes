package com.jlmcdeveloper.notes.data;

import android.content.Context;

import com.jlmcdeveloper.notes.data.model.Note;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class AppDataManager implements DataManager {


    @Inject
    public AppDataManager(){

    }


    @Override
    public List<Note> getAllNotes() {
        List<Note> list = new ArrayList<>();
        list.add(new Note("trabalho", "tenho que fazer o trabalho da empresa"));
        list.add(new Note("casa", "tenho que fazer o trabalho "));
        return list;
    }
}
