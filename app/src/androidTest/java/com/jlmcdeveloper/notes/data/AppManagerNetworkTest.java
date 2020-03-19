package com.jlmcdeveloper.notes.data;

import androidx.test.platform.app.InstrumentationRegistry;

import com.jlmcdeveloper.notes.TestComponentRule;
import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.data.model.User;
import com.jlmcdeveloper.notes.data.network.AppDispatcher;
import com.jlmcdeveloper.notes.data.network.ManagerNetwork;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;

import java.io.IOException;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.mockwebserver.MockWebServer;

import static org.mockito.Mockito.doReturn;

public class AppManagerNetworkTest {

    public final TestComponentRule component =
            new TestComponentRule(InstrumentationRegistry.getInstrumentation().getTargetContext());

    @Inject ManagerNetwork dataManagerNetwork;
    @Inject CompositeDisposable compositeDisposable;
    @Inject MockWebServer mockWebServer;

    @Rule public TestRule chain = RuleChain.outerRule(component);


    @Before
    public void setUp() {
        component.getTestComponent().inject(this);
        mockWebServer.setDispatcher(new AppDispatcher());
    }

    @After
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }





    @Test
    public void testSetLoginSuccess() {
        User user = new User(1L, "test", "test@test", "test123");

        dataManagerNetwork.setLoginRemote(user)
                .test()
                .assertValue(user1 -> user1.getId().equals(1L));
    }

    @Test
    public void testSetLoginErrorDatabase() {
        User user = new User("test", "test123");

        dataManagerNetwork.setLoginRemote(user)
                .test()
                .assertValue(user1 -> user1.getId().equals(-1L));
    }

    @Test
    public void testSetLoginErrorNetwork() {
        User user = new User("error", "test123");

        dataManagerNetwork.setLoginRemote(user)
                .test()
                .assertError(throwable -> Objects.equals(throwable.getMessage(), "HTTP 404 Client Error"));
    }






    @Test
    public void testCreateLoginSuccess() {
        User user = new User(1L, "test", "test@test", "test123");

        dataManagerNetwork.createLoginRemote(user)
                .test()
                .assertValue(user1 -> user1.getId().equals(1L));
    }


    @Test
    public void testCreateLoginErrorNetwork() {
        User user = new User("error", "test123");

        dataManagerNetwork.createLoginRemote(user)
                .test()
                .assertError(throwable -> Objects.equals(throwable.getMessage(), "HTTP 404 Client Error"));
    }





    @Test
    public void testCreateNoteSuccess() {
        Note note = new Note(1L,1L,"Title","description test");

        dataManagerNetwork.createNoteRemote(note)
                .test()
                .assertValue(note1 -> note1.getNoteID().equals(1L));
    }

    @Test
    public void testCreateNoteError() {
        Note note = new Note(-1L,1L,"Tess","description test");

        dataManagerNetwork.createNoteRemote(note)
                .test()
                .assertError(throwable -> Objects.equals(throwable.getMessage(), "HTTP 404 Client Error"));
    }


    @Test
    public void testUpdateNoteSuccess() {
        Note note = new Note(1L,1L,"Title","description test");

        dataManagerNetwork.updateNoteRemote(note)
                .test()
                .assertValue(note1 -> note1.getNoteID().equals(1L));
    }

    @Test
    public void testUpdateNoteError() {
        Note note = new Note(-1L,1L,"Tess","description test");

        dataManagerNetwork.updateNoteRemote(note)
                .test()
                .assertError(throwable -> Objects.equals(throwable.getMessage(), "HTTP 404 Client Error"));
    }


    @Test
    public void testDeleteNoteSuccess() {
        Note note = new Note(1L,1L,"Title","description test");

        dataManagerNetwork.deleteNoteRemote(note)
                .test()
                .assertValue(note1 -> note1.getNoteID().equals(1L));
    }

    @Test
    public void testDeleteNoteError() {
        Note note = new Note(-1L,1L,"Tess","description test");

        dataManagerNetwork.deleteNoteRemote(note)
                .test()
                .assertError(throwable -> Objects.equals(throwable.getMessage(), "HTTP 404 Client Error"));
    }





    @Test
    public void testGetAllNoteSuccess() {
        testSetLoginSuccess();  //define usuario no datamanager


        dataManagerNetwork.getAllNotesRemote()
                .test()
                .assertValue(notes -> notes.get(1).getTitle().equals("Title2"));
    }

    @Test
    public void testGetAllNoteError() {
        User user = new User("error", "test123");

        dataManagerNetwork.setLoginRemote(user)
                .test().onComplete();

        dataManagerNetwork.getAllNotesRemote()
                .test()
                .assertError(throwable -> Objects.equals(throwable.getMessage(), "HTTP 404 Client Error"));

    }

}