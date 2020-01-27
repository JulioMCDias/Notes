package com.jlmcdeveloper.notes.ui.note;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jlmcdeveloper.notes.R;
import com.jlmcdeveloper.notes.ui.base.BaseActivity;

import javax.inject.Inject;

public class NoteActivity extends BaseActivity implements NoteMvpView{

    @Inject
    NoteMvpPresenter<NoteMvpView> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        getActivityComponent().inject(this);

        presenter.onAttach(this);
    }


    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }
}
