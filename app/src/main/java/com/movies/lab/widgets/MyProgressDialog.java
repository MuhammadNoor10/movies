package com.movies.lab.widgets;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.movies.lab.R;

/**
 * Created by Noor aka Thor.
 * custom loading progress dialog
 */
public class MyProgressDialog {

    private Context context;
    private Dialog dialog;
    private boolean cancelable = false;
    private TextView tvTitle;

    public MyProgressDialog(Context context) {
        this.context = context;
        createDialog().setCancelable(cancelable);
    }

    private Dialog createDialog() {
        dialog = new Dialog(context, R.style.WrapDialogTheme);
        dialog.setContentView(R.layout.custom_progress_dialog);
        tvTitle = dialog.findViewById(R.id.tvTitle);

        return dialog;
    }

    public void dismissDialog() {
        if (getDialog().isShowing()) {
            getDialog().dismiss();
        }
    }

    public void showDialog() {
        if (!getDialog().isShowing()) {
            getDialog().show();
        }
    }

    private Dialog getDialog() {
        if (dialog == null) {
            createDialog();
        }
        return dialog;
    }

    public MyProgressDialog setTitle(String title) {
        getTvTitle().setText(title);
        return this;
    }

    public MyProgressDialog setTitle(int stringResId) {
        getTvTitle().setText(stringResId);
        return this;
    }

    private TextView getTvTitle() {
        if (tvTitle == null) {
            tvTitle = dialog.findViewById(R.id.tvTitle);
        }
        return tvTitle;
    }
}





























