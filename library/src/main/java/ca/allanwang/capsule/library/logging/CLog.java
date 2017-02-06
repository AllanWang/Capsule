package ca.allanwang.capsule.library.logging;

import timber.log.Timber;

/**
 * Created by Allan Wang on 2016-08-21.
 */
public class CLog {

    public static final int VERBOSE = 222, DEBUG = 333, INFO = 444, WARN = 555, ERROR = 666, ASSERT = 777;

    public static void v(String message, Object... o) {
        Timber.log(VERBOSE, message, o);
    }

    public static void d(String message, Object... o) {
        Timber.log(DEBUG, message, o);
    }

    public static void i(String message, Object... o) {
        Timber.log(INFO, message, o);
    }

    public static void w(String message, Object... o) {
        Timber.log(WARN, message, o);
    }

    public static void e(String message, Object... o) {
        Timber.log(ERROR, message, o);
    }

    public static void a(String message, Object... o) {
        Timber.log(ASSERT, message, o);
    }
}
