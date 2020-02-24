package com.jlmcdeveloper.notes.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jlmcdeveloper.notes.AndroidApplication;
import com.jlmcdeveloper.notes.R;
import com.jlmcdeveloper.notes.di.component.ActivityComponent;
import com.jlmcdeveloper.notes.di.component.DaggerActivityComponent;
import com.jlmcdeveloper.notes.di.module.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    ActivityComponent activityComponent;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((AndroidApplication) getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();

    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }


    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }


    public ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }
}
