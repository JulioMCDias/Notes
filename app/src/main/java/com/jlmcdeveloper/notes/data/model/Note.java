package com.jlmcdeveloper.notes.data.model;



public class Note {
    private Long noteID;
    private Long userID;
    private String title;
    private String description;

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
}
