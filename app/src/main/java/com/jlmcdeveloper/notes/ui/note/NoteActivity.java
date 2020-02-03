package com.jlmcdeveloper.notes.ui.note;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MultiAutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.jlmcdeveloper.notes.R;
import com.jlmcdeveloper.notes.ui.base.BaseActivity;
import com.jlmcdeveloper.notes.ui.main.MainActivity;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteActivity extends BaseActivity implements NoteMvpView{

    @Inject
    NoteMvpPresenter<NoteMvpView> presenter;

    @BindView(R.id.textInput_title)
    TextInputEditText etTitle;

    @BindView(R.id.multiAutoCompleteTextView_descrip)
    MultiAutoCompleteTextView etDescription;

    @BindView(R.id.textInputLayout_title)
    TextInputLayout textInputLayoutTitle;

    @BindView(R.id.textInputLayout_descri)
    TextInputLayout textInputLayoutDescri;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_note);

        getActivityComponent().inject(this);

        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        presenter.onAttach(this);
        presenter.setIdNote(getIntent().getLongExtra("id",-1));
    }


    // animação para nova nota
    @Override
    protected void onResume() {
        super.onResume();
        textInputLayoutTitle.setHintAnimationEnabled(true);
        textInputLayoutDescri.setHintAnimationEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_save:
                presenter.save();
                break;
            case R.id.menu_delete:
                presenter.delete();
                break;
            case android.R.id.home:
                presenter.cancel();
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        presenter.cancel();
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }



    @Override
    public String getNoteTitle() {
        return Objects.requireNonNull(etTitle.getText()).toString();
    }

    @Override
    public String getNoteDescription() {
        return etDescription.getText().toString();
    }

    @Override
    public void setNoteTitle(String title) {
        etTitle.setText(title);
    }

    @Override
    public void setNoteDescription(String description) {
        etDescription.setText(description);
    }

    @Override
    public void close() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
