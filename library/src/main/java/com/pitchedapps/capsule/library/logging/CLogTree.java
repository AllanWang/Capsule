package com.pitchedapps.capsule.library.logging;

import android.util.Log;

import timber.log.Timber;

/**
 * Created by Allan Wang on 2016-08-21.
 */
public class CLogTree extends Timber.DebugTree {

    /**
     * Special logging tree that only logs Capsule related logs
     *
     * @param priority
     * @param tag
     * @param message
     * @param t
     */
    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        switch (priority) {
            case CLog.DEBUG:
                priority = Log.DEBUG;
                break;
            case CLog.ERROR:
                priority = Log.ERROR;
                break;
            default:
                return;
        }
        super.log(priority, tag, message, t);
    }

}
