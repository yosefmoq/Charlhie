package com.yosefmoq.charlhie.utils;


import com.yosefmoq.charlhie.ui.Auth.SignInFragment;
import com.yosefmoq.charlhie.ui.Auth.SignUpFragment;

public final class AppConstants {


    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";
    public final static String AUTHORIZATION = "Authorization";
    public final static String FACEBOOK = "facebook";
    public final static String GOOGLE = "google";
    public final static String FAIL = "fail";
    public final static String SUCCESS = "success";

    public static final int    Latest_Ads = 1;
    public static final int    MOST_View= 2;
    public static final int    Recommended= 3;
    public static final int    CATEGORY   = 4;

    public static final int GPS_REQUEST = 57;

    public static final SignInFragment signInFragment = new SignInFragment();
    public static final SignUpFragment signUpFragment = new SignUpFragment();
}
