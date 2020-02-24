package com.jlmcdeveloper.notes.data;

import com.jlmcdeveloper.notes.data.model.User;

public interface Listener {

    interface Note{
        void handle (com.jlmcdeveloper.notes.data.model.Note note);
    }

    interface Login{
        void handle (User user);
    }

    interface ResponseError {
        void handle (boolean error);
    }
}
