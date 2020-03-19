package com.jlmcdeveloper.notes.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.jlmcdeveloper.notes.R;
import com.jlmcdeveloper.notes.data.model.Note;
import com.jlmcdeveloper.notes.ui.base.BaseActivity;
import com.jlmcdeveloper.notes.ui.note.NoteActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainMvpView{

    @Inject
    MainMvpPresenter<MainMvpView> presenter;

    private NoteAdapter noteAdapter;

    @BindView(R.id.recyclerView_note)
    RecyclerView recyclerView;

    @OnClick(R.id.btn_floating)
    public void btnNewNote(){
        presenter.createNote();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        ButterKnife.bind(this);

        presenter.onAttach(this);
        presenter.searchNotes();
        noteAdapter = new NoteAdapter(presenter, presenter.getNotes());
        recyclerView.setAdapter(noteAdapter);
    }


    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }


    @Override
    public void openNoteActivity(Long idNode) {
        Intent intent = new Intent(this, NoteActivity.class);
        intent.putExtra("id",idNode);
        startActivity(intent);
        finish();
    }

    @Override
    public void updateNotes() {
        noteAdapter.notifyDataSetChanged();
    }
}
