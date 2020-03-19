package com.jlmcdeveloper.notes.data;

import androidx.test.platform.app.InstrumentationRegistry;

import com.jlmcdeveloper.notes.TestComponentRule;
import com.jlmcdeveloper.notes.data.local.ManagerLocal;
import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.data.model.User;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class AppManagerLocalTest {
    @Inject
    ManagerLocal dataManager;


    public final TestComponentRule component =
            new TestComponentRule(InstrumentationRegistry.getInstrumentation().getTargetContext());
    @Rule
    public TestRule chain = RuleChain.outerRule(component);


    @Before
    public void setUp() {
        component.getTestComponent().inject(this);

    }



    @Test
    public void testCrudLogin() {
        User userTest = new User( "test", "test123");
        // createLoginLocal
        dataManager.createLoginLocal(userTest)
                .test()
                .onComplete();

        dataManager.setLoginLocal(userTest)
                .test()
                .assertValue(user -> user.getName().equals(userTest.getName()) &&
                        user.getPassword().equals(userTest.getPassword()));

        dataManager.getAllUserLocal()
                .test()
                .assertValue(users -> {
                    for (User u : users)
                        dataManager.deleteUserLocal(u).test().onComplete();

                    return users.size() == 1;
                });
    }



    @Test
    public void testCrudNote() {
        User user = new User(1L, "test", "", "test123");
        dataManager.createLoginLocal(user)
                .test()
                .onComplete();



        Note note = new Note(30L, user.getId(), "Test", "isto é um test");
        // criar
        dataManager.createNoteLocal(note)
                .test()
                .onComplete();

        // validar
        dataManager.getAllNotesLocal(user)
                .test()
                .assertValue(notes -> {
                    for (Note n : notes) {
                        if(n.equals(note))
                            return true;
                    }
                    return false;
                });


        note.setTitle("new title");
        // atualizar
        dataManager.updateNoteLocal(note)
                .test()
                .onComplete();

        // validar
        dataManager.getAllNotesLocal(user)
                .test()
                .assertValue(notes -> {
                    for (Note n : notes) {
                        if(n.equals(note))
                            return true;
                    }
                    return false;
                });



        // deletar
        dataManager.deleteNoteLocal(note)
                .test()
                .onComplete();

        // validar
        dataManager.getAllNotesLocal(user)
                .test()
                .assertValue(notes -> {
                    // limpar dados
                    for (Note n: notes) {
                        dataManager.deleteNoteLocal(n)
                                .test()
                                .onComplete();
                    }


                    for (Note n : notes) {
                        if(n.equals(note))
                            return false;
                    }
                    return true;
                });

        // removendo usuario
        dataManager.getAllUserLocal().test().assertValue(users -> {
                    for (User u : users)
                        dataManager.deleteUserLocal(u).test().onComplete();
                    return users.size() > 0;
                });
    }


    @Test
    public void testCrudAllNote() {
        User user = new User(1L, "test", "", "test123");
        dataManager.createLoginLocal(user)
                .test()
                .onComplete();


        List<Note> notesTest = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Note note = new Note(null,
                    user.getId(),
                    "Test: "+ i,
                    "isto é um test: "+ i);
            notesTest.add(note);
        }

        // criar
        dataManager.createAllNoteLocal(notesTest)
                .test()
                .onComplete();

        // validar
        dataManager.getAllNotesLocal(user)
                .test()
                .assertValue(notes -> {

                    // limpar dados
                    for (Note note: notes) {
                        dataManager.deleteNoteLocal(note)
                                .test()
                                .onComplete();
                    }

                    return notes.size() == 10 &&
                            notes.get(0).getUserID().equals(user.getId()) &&
                            notes.get(0).getTitle().equals("Test: 0") &&
                            notes.get(0).getDescription().equals("isto é um test: 0");
                });



        // removendo usuario
        dataManager.getAllUserLocal().test().assertValue(users -> {
            for (User u : users)
                dataManager.deleteUserLocal(u).test().onComplete();
            return users.size() > 0;
        });
    }
}