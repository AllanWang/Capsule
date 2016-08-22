package com.pitchedapps.capsule.library.activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;

import com.pitchedapps.capsule.library.logging.CLog;
import com.pitchedapps.capsule.library.logging.CLogTree;

import timber.log.Timber;

/**
 * Created by Allan Wang on 2016-08-21.
 * <p/>
 * The core's core
 */
public class BaseActivity extends AppCompatActivity {

    protected String s(@StringRes int id) {
        return getString(id);
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
