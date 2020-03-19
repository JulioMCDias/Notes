package com.jlmcdeveloper.notes.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.jlmcdeveloper.notes.R;
import com.jlmcdeveloper.notes.ui.base.BaseActivity;
import com.jlmcdeveloper.notes.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginMvpView {

    @Inject
    LoginMvpPresenter<LoginMvpView> presenter;


    @BindView(R.id.inputText_name)
    TextInputEditText inputTextName;

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
        presenter.setUser(inputTextName.getText().toString(), inputTextPassword.getText().toString());
    }


    @OnClick(R.id.btn_register)
    void btnRegister(){
        presenter.createUser(inputTextName.getText().toString(), inputTextPassword.getText().toString());
    }


    @Override
    public void finishedAnimation(String info) {
        hideLoading();
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
