package com.movies.lab.ui.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.movies.lab.R;
import com.movies.lab.widgets.MyProgressDialog;

import dagger.android.support.DaggerAppCompatActivity;

public class BaseActivity extends DaggerAppCompatActivity {

    protected MyProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        if (progressDialog != null) {
            progressDialog.dismissDialog();
        }
        super.onDestroy();
    }

    protected void showProgressDialog(Context context) {
        showProgressDialog(context, getResources().getString(R.string.loading));//default string
    }

    protected void showProgressDialog(Context context, int titleRes) {
        showProgressDialog(context, getResources().getString(titleRes));
    }

    protected void showProgressDialog(Context context, String title) {
        progressDialog = new MyProgressDialog(context);
        progressDialog
                .setTitle(title)
                .showDialog();
    }

    protected void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismissDialog();
        }
    }
}
