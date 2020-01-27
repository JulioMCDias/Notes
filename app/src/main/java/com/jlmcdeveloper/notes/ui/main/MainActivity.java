package com.jlmcdeveloper.notes.ui.main;

import android.os.Bundle;

import com.jlmcdeveloper.notes.R;
import com.jlmcdeveloper.notes.ui.base.BaseActivity;

import javax.inject.Inject;

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

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }
}
