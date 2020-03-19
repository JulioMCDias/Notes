package com.jlmcdeveloper.notes.data.network;

public class ApiEndPoint {
    public static final String url = "http://api-rnr.herokuapp.com/";
    static final String setLogin = "setLogin";
    public static final String createLogin = "createUser";
    static final String createNote = "createNote";
    static final String updateNote = "updateNote";
    static final String deleteNote = "deleteNote";
    static final String getAllNotes = "getNotes";

    private ApiEndPoint(){ }
}
