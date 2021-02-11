package com.yosefmoq.charlhie.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.ContextThemeWrapper;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.yosefmoq.charlhie.R;


/**
 * Gps location tracker class
 * to get users location and other information related to location
 */
public class GpsLocationTracker {

    public static final int REQUEST_CHECK_SETTINGS = 100;
    private static final String TAG = "GpsLocationTracker";
    // location updates interval - 10sec
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 3000;

    // fastest updates interval - 5 sec
    // location updates will be received if another app is requesting the locations
    // than your app can handle
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 1000;
    public static boolean isStart = false;
    public static GpsLocationTracker instance;
    /**
     * context of calling class
     */
    private Context mContext;
    // bunch of location related apis
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest mLocationRequest;
    private LocationCallback mLocationCallback;

    /**
     * @param mContext constructor of the class
     */
    @SuppressLint("InvalidWakeLockTag")
    public GpsLocationTracker(Context mContext, LocListener locListener) {

        this.mContext = mContext;


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext);

        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS); // 10 seconds
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS); // 5 seconds
        // mLocationRequest.setSmallestDisplacement(1); // 5 seconds


        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null || locationResult.getLastLocation() == null) {
                    return;
                }

                locListener.onChange(locationResult.getLastLocation());

            }
        };


    }

    public static GpsLocationTracker getInactance(Context mContext, LocListener locListener) {

        if (instance == null) {

            instance = new GpsLocationTracker(mContext, locListener);
        }
        return instance;


    }

    public void startUsingGps() {

        Log.d(TAG, "startUsingGps:isStart " + isStart);
        if (!isStart) {
            new GpsUtils(mContext).turnGPSOn(isGPSEnable -> {
                        // turn on GPS
                        Log.d(TAG, "startUsingGps: " + isGPSEnable);
                        isStart = isGPSEnable;
                        if (isGPSEnable) {

                            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                                    && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                            } else {
                                Log.d(TAG, "startUsingGps: " + mFusedLocationClient);
                                mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.getMainLooper());
                            }
                        }
                    }
            );
        } else {
            mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, null);
        }

    }

    /**
     * call this function to stop using gps in your application
     */
    public void stopUsingGps() {

        if (mFusedLocationClient != null) {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        }
    }

    /**
     * function to prompt user to open
     * settings to enable gps
     */
    public void showSettingsAlert(Activity activity) {

        AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(new ContextThemeWrapper(mContext, R.style.AppTheme));

        mAlertDialog.setTitle("Gps Disabled");

        mAlertDialog.setMessage("gps is not enabled . do you want to enable ?");

        mAlertDialog.setPositiveButton("settings", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

                activity.startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), REQUEST_CHECK_SETTINGS);


            }
        });

        mAlertDialog.setNegativeButton("cancle", (dialog, which) -> {
            // TODO Auto-generated method stub
            dialog.cancel();

        });

        final AlertDialog mcreateDialog = mAlertDialog.create();
        mcreateDialog.show();
    }


}