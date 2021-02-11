package com.yosefmoq.charlhie;

import android.content.Context;
import android.util.Log;

import androidx.multidex.MultiDexApplication;

import io.reactivex.plugins.RxJavaPlugins;


public class App extends MultiDexApplication {
    private static Context context;

    public static Context getmContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        Fabric.with(this, new Crashlytics());
        context = this;
        RxJavaPlugins.setErrorHandler(throwable -> {
            Log.v("ttt",throwable.getLocalizedMessage());
        });
/*
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        CommonUtils.changeLocale(this);
*/

    }
}
