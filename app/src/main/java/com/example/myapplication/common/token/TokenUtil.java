package com.example.myapplication.common.token;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenUtil {
    private static final String PREFS = "prefs";
    private static final String Access_Token = "access_token";
    private static final String Refresh_Token = "refresh_token";
    private Context mContext;
    private static SharedPreferences prefs;
    private static SharedPreferences.Editor prefsEditor;
    private static TokenUtil instance;

    public static synchronized TokenUtil init(Context context) {
        if(instance == null)
            instance = new TokenUtil(context);
        return instance;
    }

    private TokenUtil(Context context) {
        mContext = context;
        prefs = mContext.getSharedPreferences(PREFS,Context.MODE_PRIVATE);
        prefsEditor = prefs.edit();
    }

    public static void setAccessToken(String value) {
        prefsEditor.putString(Access_Token, value).commit();
    }

    public static String getAccessToken(String defValue) {
        return "Bearer "+prefs.getString(Access_Token,defValue);
    }

    public static void setRefreshToken(String value) {
        prefsEditor.putString(Refresh_Token, value).commit();
    }

    public static String getRefreshToken(String defValue) {
        return prefs.getString(Refresh_Token,defValue);
    }

    public static void clearToken() {
        prefsEditor.clear().apply();
    }
}
