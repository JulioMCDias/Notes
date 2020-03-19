package com.jlmcdeveloper.notes.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.data.model.User;

@Database(entities = {User.class, Note.class}, version = 1, exportSchema = false)
public abstract class NotesRoomDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();
    public abstract UserDao userDao();

    private static NotesRoomDatabase instance;

    public static NotesRoomDatabase getDatabase(Context context, String databaseName) {
        if (instance == null) {
            synchronized (NotesRoomDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            NotesRoomDatabase.class, databaseName)
                            .build();
                }
            }
        }
        return instance;
    }
}
