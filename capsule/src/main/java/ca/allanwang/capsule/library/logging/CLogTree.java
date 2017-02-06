package ca.allanwang.capsule.library.logging;

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
            case CLog.VERBOSE:
                priority = Log.VERBOSE;
                break;
            case CLog.DEBUG:
                priority = Log.DEBUG;
                break;
            case CLog.INFO:
                priority = Log.INFO;
                break;
            case CLog.WARN:
                priority = Log.WARN;
                break;
            case CLog.ERROR:
                priority = Log.ERROR;
                break;
            case CLog.ASSERT:
                priority = Log.ASSERT;
                break;
            default:
                return;
        }
        super.log(priority, tag, "CLog: " + message, t);
    }

}
