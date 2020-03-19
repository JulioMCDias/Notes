package com.jlmcdeveloper.notes.data.model;


import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "noteID")
    private Long noteID;
    @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userID")
    @ColumnInfo(name = "userID")
    private Long userID;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "description")
    private String description;



    public Note(Long noteID, Long userID, String title, String description) {
        this.noteID = noteID;
        this.userID = userID;
        this.title = title;
        this.description = description;
    }

    @Ignore
    public Note() {
    }

    public Long getNoteID() {
        return noteID;
    }

    public void setNoteID(Long noteID) {
        this.noteID = noteID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserID() {
        return userID;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }


    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj == this) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        return ((Note) obj).getNoteID().equals(noteID);
    }
}
