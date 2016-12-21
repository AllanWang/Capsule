package com.pitchedapps.capsule.library.utils;

import android.os.Build;

/**
 * Created by Allan Wang on 2016-12-21.
 */

public class Utils {

    public static boolean isKitkatPlus() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean isLollipopPlus() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}
