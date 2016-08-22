package com.pitchedapps.capsule.library.fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

/**
 * Created by Allan Wang on 2016-08-21.
 */
public class BaseFragment extends Fragment {

    protected String s(@StringRes int id) {
        return getString(id);
    }

    protected static String s(@NonNull Context context, @StringRes int id) {
        return context.getString(id);
    }
}
