package com.yosefmoq.charlhie.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.yosefmoq.charlhie.R;
import com.yosefmoq.charlhie.listener.DialogListener;


/**
 * Created by zaid on 19/05/2015.
 */
public class DialogUtils {

    public static void showInputPasswordDialogWithCallback(final Context context, final String title, final String message, final String preFilledText, final DialogListener listener) {
        int style = Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT ?
                R.style.SelectionDialog :
                R.style.SelectionDialog_PreL;
        AlertDialog.Builder alert = new AlertDialog.Builder(context, style);

        alert.setTitle(title);
        alert.setMessage(message);
        alert.setCancelable(false);

        // Set an EditText view to get user input
        final EditText input = new EditText(context);
        input.setText(preFilledText);
//        input.setHint(preFilledText);
        input.setTransformationMethod(PasswordTransformationMethod.getInstance());
        input.setSelection(preFilledText.length());
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                if (listener != null && !TextUtils.isEmpty(value)) {
                    listener.onPositiveButtonClicked(value);
                } else {
                    listener.onPositiveButtonClicked("");
                }
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if (listener != null) {
                    listener.onNegativeButtonClicked();
                }
            }
        });

        Dialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    public static void showConfirmationDialog(final Context context, final String title, final String message, final DialogListener listener) {
        showConfirmationDialog(context, title, message, "Yes", listener);
    }

    public static void showConfirmationDialog(final Context context, final String title, final String message, String positiveButtonText, final DialogListener listener) {
        int style = Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT ?
                R.style.SelectionDialog :
                R.style.SelectionDialog_PreL;
        AlertDialog.Builder alert = new AlertDialog.Builder(context, style);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
                if (listener != null) {
                    listener.onPositiveButtonClicked("success");
                }
            }
        });

        Dialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    public static void showConfirmationDialog(final Context context, final String title, final String message, String pos9tveButtonText, String negativeButtonText, final DialogListener listener) {
        int style = Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT ?
                R.style.SelectionDialog :
                R.style.SelectionDialog_PreL;
        AlertDialog.Builder alert = new AlertDialog.Builder(context, style);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setCancelable(false);
        alert.setPositiveButton(pos9tveButtonText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
                if (listener != null) {
                    listener.onPositiveButtonClicked("success");
                }
            }
        });

        alert.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
                if (listener != null) {
                    listener.onNegativeButtonClicked();
                }
            }
        });

        Dialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    public static void showDialog(final Context context, final String title, final String message, final DialogListener listener) {
        showConfirmationDialog(context, title, message, "Yes", "Cancel", listener);
    }

    public static void showCancellableDialog(final Context context, final String title, final String message, final DialogListener listener, String positiveButton, String negativeButton) {
        int style = Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT ?
                R.style.SelectionDialog :
                R.style.SelectionDialog_PreL;
        AlertDialog.Builder alert = new AlertDialog.Builder(context, style);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
                if (listener != null) {
                    listener.onPositiveButtonClicked("success");
                }
            }
        });

        alert.setNegativeButton(negativeButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
                if (listener != null) {
                    listener.onNegativeButtonClicked();
                }
            }
        });

        Dialog alertDialog = alert.create();
        alertDialog.setCancelable(true);
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();

    }

    public static void showDialog(final Context context, final String title, final String message, String positiveButton, String negativeButton, final DialogListener listener) {
        int style = Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT ?
                R.style.DialogTheme :
                R.style.SelectionDialog_PreL;
        AlertDialog.Builder alert = new AlertDialog.Builder(context, style);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
                if (listener != null) {
                    listener.onPositiveButtonClicked("success");
                }
            }
        });

        alert.setNegativeButton(negativeButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
                if (listener != null) {
                    listener.onNegativeButtonClicked();
                }
            }
        });

        Dialog alertDialog = alert.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    public static void showDialogOneOption(final Context context, final String title, final String message, String positiveButton, String negativeButton, final DialogListener listener) {
        int style = Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT ?
                R.style.DialogTheme :
                R.style.SelectionDialog_PreL;
        AlertDialog.Builder alert = new AlertDialog.Builder(context, style);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
                if (listener != null) {
                    listener.onPositiveButtonClicked("success");
                }
            }
        });

        alert.setNegativeButton(negativeButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
                if (listener != null) {
                    listener.onNegativeButtonClicked();
                }
            }
        });

        Dialog alertDialog = alert.create();

        ((AlertDialog) alertDialog).getButton(AlertDialog.BUTTON_NEGATIVE).setVisibility(View.GONE);

        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    public static void showDialog(final Context context, final String title, final String message) {
        int style = Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT ?
                R.style.SelectionDialog :
                R.style.SelectionDialog_PreL;
        AlertDialog.Builder alert = new AlertDialog.Builder(context, style);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });


        Dialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public enum TOAST_TYPE {
        WARNING,
        SUCCESS,
        ERROR,
        INFO
    }

}
