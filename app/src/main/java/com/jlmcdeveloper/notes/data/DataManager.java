package com.jlmcdeveloper.notes.data;


import com.jlmcdeveloper.notes.data.model.Note;

import java.util.List;

import io.objectbox.Box;

public interface DataManager {

    Box<Note> getAllNotes();

}
