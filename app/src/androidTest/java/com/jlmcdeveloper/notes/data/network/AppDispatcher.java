package com.jlmcdeveloper.notes.data.network;

import android.util.Log;

import com.google.gson.Gson;
import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.data.model.User;

import org.jetbrains.annotations.NotNull;


import java.util.Objects;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import okio.Buffer;

public class AppDispatcher extends Dispatcher {

    @NotNull
    @Override
    public MockResponse dispatch(@NotNull RecordedRequest recordedRequest) {
        Buffer buffer;
        User user;
        Note note;
        switch (Objects.requireNonNull(recordedRequest.getPath())) {

            case "/peoples/?method=" + ApiEndPoint.setLogin:
                buffer = recordedRequest.getBody().clone();
                 user = new Gson().fromJson(buffer.readUtf8(), User.class);
                if(user.getName().equals("test"))
                    return new MockResponse().setResponseCode(200).setBody(recordedRequest.getBody());
                return new MockResponse().setResponseCode(404);

            case "/peoples/?method=" + ApiEndPoint.createLogin:
                buffer = recordedRequest.getBody().clone();
                user = new Gson().fromJson(buffer.readUtf8(), User.class);
                if(user.getName().equals("test"))
                    return new MockResponse().setResponseCode(201).setBody("{\"email\":\"test@test\",\"id\":\"1\", \"name\":\"test\", \"password\":\"test123\"}");
                return new MockResponse().setResponseCode(404);




            case "/notes/?method=" + ApiEndPoint.createNote:
                buffer = recordedRequest.getBody().clone();
                note = new Gson().fromJson(buffer.readUtf8(), Note.class);
                if(note.getTitle().equals("Title"))
                    return new MockResponse().setResponseCode(201).setBody(
                        "{ \"noteID\":\"1\", \"userID\":\"1\", \"title\":\"Title\", \"description\":\"Description Test\"}");
                return new MockResponse().setResponseCode(404);


            case "/notes/?method=" + ApiEndPoint.updateNote:
            case "/notes/?method=" + ApiEndPoint.deleteNote:
                buffer = recordedRequest.getBody().clone();
                note = new Gson().fromJson(buffer.readUtf8(), Note.class);
                if(note.getTitle().equals("Title"))
                    return new MockResponse().setResponseCode(200).setBody(recordedRequest.getBody());
                return new MockResponse().setResponseCode(404);


            case "/notes/?method=" + ApiEndPoint.getAllNotes:
                buffer = recordedRequest.getBody().clone();
                user = new Gson().fromJson(buffer.readUtf8(), User.class);
                if(user.getName().equals("test"))
                    return new MockResponse().setResponseCode(200).setBody(
                        "[ " +
                            "{ \"noteID\":\"1\", \"userID\":\"1\", \"title\":\"Title\", \"description\":\"Description Test\"}, " +
                            "{ \"noteID\":\"2\", \"userID\":\"1\", \"title\":\"Title2\", \"description2\":\"Description Test2\"} " +
                        "]"
                    );
                return new MockResponse().setResponseCode(404);

        }
        return new MockResponse().setResponseCode(404);
    }
}
