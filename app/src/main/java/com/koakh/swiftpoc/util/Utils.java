package com.koakh.swiftpoc.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.koakh.swiftpoc.R;

/**
 * Created by mario on 21/02/2015.
 */
public class Utils {

  public static void dialogBox(Context context, String title, String message) {
    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
    alertDialogBuilder.setTitle(title);
    alertDialogBuilder.setMessage(message);

    alertDialogBuilder.setPositiveButton("Ok",
      new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface arg0, int arg1) {
        }
      });

    alertDialogBuilder.setNegativeButton("cancel",
      new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface arg0, int arg1) {

        }
      });

    AlertDialog alertDialog = alertDialogBuilder.create();
    alertDialog.show();
  }
}
