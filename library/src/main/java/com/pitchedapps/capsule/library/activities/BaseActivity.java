package com.pitchedapps.capsule.library.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;

import com.pitchedapps.capsule.library.interfaces.CActivityCore;
import com.pitchedapps.capsule.library.logging.CLog;
import com.pitchedapps.capsule.library.logging.CLogTree;

import java.util.Locale;

import timber.log.Timber;

/**
 * Created by Allan Wang on 2016-08-21.
 * <p/>
 * The core's core
 */
abstract class BaseActivity extends AppCompatActivity implements CActivityCore {

    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableCLog();
    }

    protected String s(@StringRes int id) {
        if (id == 0) return "placeholder";
        return getString(id);
    }

    protected String sf(@StringRes int id, Object... o) {
        return String.format(Locale.CANADA, s(id), o);
    }

    protected static String s(@NonNull Context context, @StringRes int id) {
        return context.getString(id);
    }

    private CLogTree cTree;

    protected void enableCLog() {
        if (cTree == null) {
            cTree = new CLogTree();
            Timber.plant(cTree);
        }
    }

    protected void disableCLog() {
        if (cTree != null) {
            Timber.uproot(cTree);
            cTree = null;
        }
    }
}
