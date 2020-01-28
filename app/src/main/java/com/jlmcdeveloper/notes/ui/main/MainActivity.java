package com.jlmcdeveloper.notes.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jlmcdeveloper.notes.R;
import com.jlmcdeveloper.notes.ui.base.BaseActivity;
import com.jlmcdeveloper.notes.ui.note.NoteActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainMvpView{

    @Inject
    MainMvpPresenter<MainMvpView> presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        presenter.onAttach(this);
    }

    public void btnNewNote(View view){
        presenter.createNote();
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }


    @Override
    public void openNoteActivity(int idNode) {
        Intent intent = new Intent(this, NoteActivity.class);
        intent.putExtra("id",idNode);
        startActivity(intent);
        finish();
    }



}
