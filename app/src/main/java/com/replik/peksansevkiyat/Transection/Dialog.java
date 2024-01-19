package com.replik.peksansevkiyat.Transection;


import android.app.ProgressDialog;
import android.content.Context;

public class Dialog {
    private static ProgressDialog nDialog = null;

    public static ProgressDialog getDialog(Context context, String message) {
        nDialog = new ProgressDialog(context);
        nDialog.setMessage(message);
        nDialog.setIndeterminate(false);
        nDialog.setCancelable(false);

        return nDialog;
    }
}
