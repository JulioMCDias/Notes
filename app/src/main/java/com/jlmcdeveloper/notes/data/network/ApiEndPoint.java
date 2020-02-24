package com.jlmcdeveloper.notes.data.network;

public class ApiEndPoint {
    static final String url = "http://192.168.0.193/webService/v1/";
    static final String setLogin = "setLogin";
    public static final String createLogin = "createUser";
    static final String createNote = "createNote";
    static final String updateNote = "updateNote";
    static final String deleteNote = "deleteNote";
    static final String getAllNotes = "getNotes";

    private ApiEndPoint(){ }
}
