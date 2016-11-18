package com.pitchedapps.capsule;

import android.content.Context;

import com.pitchedapps.capsule.library.utils.CPrefs;

/**
 * Created by Allan Wang on 2016-11-18.
 */

public class SamplePrefs extends CPrefs {
    private static final String THEME = "theme";

    public SamplePrefs(Context context) {
        super(context);
    }

    public boolean switchTheme() {
        boolean newTheme = !getBoolean(THEME, true);
        setBoolean(THEME, newTheme);
        return newTheme;
    }

    public boolean isLightTheme() {
        return getBoolean(THEME, true);
    }
}
