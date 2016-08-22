package com.pitchedapps.capsule.library.logging;

import timber.log.Timber;

/**
 * Created by Allan Wang on 2016-08-21.
 */
public class CLog {

    public static final int DEBUG = 111, ERROR = 666;

    public static void d(String message, Object... o ) {
        Timber.log(DEBUG, message, o);
    }

    public static void e(String message, Object... o ) {
        Timber.log(ERROR, message, o);
    }
}
