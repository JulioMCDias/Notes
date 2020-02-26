package com.jlmcdeveloper.notes.data.network;

public class ApiEndPoint {
    static final String url = "http://192.168.0.195/webService/v1/";
    public static final String urlSetLogin = url + "package/ctrl/CtrlLogin.php";
    public static final String urlGetNotes = url + "package/ctrl/CtrlNotes.php";
    public static final String urlSetNotes = url + "package/ctrl/CtrlNotes.php";

    public static final String setLogin = "setLogin";
    public static final String createLogin = "createUser";
    public static final String createNote = "createNote";
    public static final String updateNote = "updateNote";
    public static final String deleteNote = "deleteNote";
    public static final String getAllNotes = "getNotes";
    public static final String method = "method";

    private ApiEndPoint(){ }
}
