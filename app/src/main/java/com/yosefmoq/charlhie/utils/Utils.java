package com.yosefmoq.charlhie.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Patterns;

import java.text.DecimalFormat;

public class Utils {
    public static int dpToPx(int dp) {
        return (int) (((float) dp) * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px) {
        return (int) (((float) px) / Resources.getSystem().getDisplayMetrics().density);
    }

/*
    public static boolean isInternetConnected(Context context) {
        NetworkInfo connection = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (connection == null || !connection.isConnectedOrConnecting()) {
            return false;
        }
        return true;
    }
*/

    public static void showInternetAlertDialog(final Activity context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("You are not connected to internet!").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            /* class com.yosefmoq.charlhie.utils.Utils.AnonymousClass2 */

            public void onClick(DialogInterface dialog, int id) {
                context.moveTaskToBack(true);
            }
        }).setNegativeButton("Settings", new DialogInterface.OnClickListener() {
            /* class com.yosefmoq.charlhie.utils.Utils.AnonymousClass1 */

            public void onClick(DialogInterface dialog, int id) {
                context.startActivity(new Intent("android.settings.WIRELESS_SETTINGS"));
            }
        });
        builder.create().show();
    }

    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    public static String displayDoubleValue(double d) {
        return new DecimalFormat("#.00").format(d);
    }
}
