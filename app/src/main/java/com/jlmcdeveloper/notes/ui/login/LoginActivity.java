package com.jlmcdeveloper.notes.ui.login;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.jlmcdeveloper.notes.R;
import com.jlmcdeveloper.notes.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginMvpView {

    @Inject
    LoginMvpPresenter<LoginMvpView> presenter;


    @BindView(R.id.inputText_email)
    TextInputEditText inputTextEmail;

    @BindView(R.id.inputText_password)
    TextInputEditText inputTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getActivityComponent().inject(this);

        ButterKnife.bind(this);

        presenter.onAttach(this);
    }


    @OnClick(R.id.btn_login)
    void btnLogin(){

    }


    @OnClick(R.id.btn_register)
    void btnRegister(){

    }
}
