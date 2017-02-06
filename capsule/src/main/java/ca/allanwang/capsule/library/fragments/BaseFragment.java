package ca.allanwang.capsule.library.fragments;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

import ca.allanwang.capsule.library.interfaces.CFragmentCore;

/**
 * Created by Allan Wang on 2016-08-21.
 */
public abstract class BaseFragment extends Fragment implements CFragmentCore {

    protected String s(@StringRes int id) {
        return getString(id);
    }

}
