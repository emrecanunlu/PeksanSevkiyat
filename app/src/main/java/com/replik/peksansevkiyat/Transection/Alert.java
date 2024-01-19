package com.replik.peksansevkiyat.Transection;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.replik.peksansevkiyat.R;

public class Alert {
    private static AlertDialog alertDialog = null;

    public static AlertDialog getAlert(Context context, String title, String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setNegativeButton(context.getString(R.string.close), (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });

        alertDialog = alertDialogBuilder.create();

        return alertDialog;
    }
}
