package com.jlmcdeveloper.notes.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.jlmcdeveloper.notes.data.model.Note;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface NoteDao {

    @Query(value = "SELECT * from notes WHERE userID=:userID")
    Maybe<List<Note>> getAllNotes(long userID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertNote(Note note);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAllNote(List<Note> notes);

    @Update
    Single<Integer> updateNote(Note note);

    @Delete
    Single<Integer> deleteNote(Note note);
}
