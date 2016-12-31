package com.pitchedapps.capsule.library.logging;

import android.support.annotation.NonNull;
import android.util.Log;

import timber.log.Timber;

/**
 * Created by Allan Wang on 2016-12-30.
 */

public class CallbackLogTree extends Timber.Tree {

    private int VERBOSE, DEBUG, INFO, WARN, ERROR, ASSERT;

    public CallbackLogTree(@NonNull Callback callback) {
        mCallback = callback;
        VERBOSE = CLog.VERBOSE;
        DEBUG = CLog.DEBUG;
        INFO = CLog.INFO;
        WARN = CLog.WARN;
        ERROR = CLog.ERROR;
        ASSERT = CLog.ASSERT;
    }

    public CallbackLogTree(@NonNull Callback callback, int v, int d, int i, int w, int e, int a) {
        mCallback = callback;
        VERBOSE = v;
        DEBUG = d;
        INFO = i;
        WARN = w;
        ERROR = e;
        ASSERT = a;
    }

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        if (priority == VERBOSE) {
            priority = Log.VERBOSE;
        } else if (priority == DEBUG) {
            priority = Log.DEBUG;
        } else if (priority == INFO) {
            priority = Log.INFO;
        } else if (priority == WARN) {
            priority = Log.WARN;
        } else if (priority == ERROR) {
            priority = Log.ERROR;
        } else if (priority == ASSERT) {
            priority = Log.ASSERT;
        } else {
            return;
        }
        mCallback.log(priority, tag, message, t);
    }

    public interface Callback {
        void log(int priority, String tag, String message, Throwable t);
    }

    private Callback mCallback;


}
