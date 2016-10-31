package com.pitchedapps.capsule.library.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Allan Wang on 2016-10-30.
 */

public class CPrefs {
    private static final String
            PREFERENCES_NAME = "CAPSULE_PREFERENCES",
            VERSION_CODE = "version_code";

    private final Context context;

    public CPrefs(Context context) {
        this.context = context;
    }

    private SharedPreferences prefs() {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }


    public void setVersionCode(int versionCode) {
        setInt(VERSION_CODE, versionCode);
    }

    public int getVersionCode() {
        return getInt(VERSION_CODE, 0);
    }

    //GENERICS

    protected void setInt(String s, int i) {
        prefs().edit().putInt(s, i).apply();
    }

    protected int getInt(String s, int i) {
        return prefs().getInt(s, i);
    }

    protected void setBoolean(String s, boolean b) {
        prefs().edit().putBoolean(s, b).apply();
    }

    protected boolean getBoolean(String s, boolean b) {
        return prefs().getBoolean(s, b);
    }

    protected void setString(String s, String ss) {
        prefs().edit().putString(s, ss).apply();
    }

    protected String getString(String s, String ss) {
        return prefs().getString(s, ss);
    }
}
