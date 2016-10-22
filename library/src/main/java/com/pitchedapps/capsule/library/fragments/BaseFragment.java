package com.pitchedapps.capsule.library.fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

import com.pitchedapps.capsule.library.interfaces.CFragmentCore;

/**
 * Created by Allan Wang on 2016-08-21.
 */
abstract class BaseFragment extends Fragment implements CFragmentCore {

    protected String s(@StringRes int id) {
        return getString(id);
    }

    protected static String s(@NonNull Context context, @StringRes int id) {
        return context.getString(id);
    }
}
