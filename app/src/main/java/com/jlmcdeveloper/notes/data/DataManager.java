package com.jlmcdeveloper.notes.data;


import com.jlmcdeveloper.notes.data.local.ManagerLocal;
import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.data.model.User;
import com.jlmcdeveloper.notes.data.network.ManagerNetwork;

import java.util.List;


public interface DataManager extends ManagerLocal, ManagerNetwork {

    User getUser();
    List<Note> getNotes();
    void setUser(User user);
    void setNotes(List<Note> notes1);
}
