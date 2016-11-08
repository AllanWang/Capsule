package com.pitchedapps.capsule.library.activities;

import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.pitchedapps.capsule.library.R;
import com.pitchedapps.capsule.library.fragments.CapsuleFragment;
import com.pitchedapps.capsule.library.interfaces.CFragmentCore;

/**
 * Created by Allan Wang on 2016-08-19.
 * <p>
 * The activity that holds everything
 */
public abstract class CapsuleActivity extends UtilsActivity {

    protected <T extends Fragment & CFragmentCore> void switchFragment(T fragment) {
        getSupportFragmentManager()
                .beginTransaction().replace(getFragmentId(), fragment, s(fragment.getTitleId())).commit();
    }

    protected <T extends Fragment & CFragmentCore> void switchFragment(T fragment, @AnimRes int enter,
                                                                       @AnimRes int exit) {
        getSupportFragmentManager()
                .beginTransaction().setCustomAnimations(enter, exit)
                .replace(getFragmentId(), fragment, s(fragment.getTitleId())).commit();
    }

    protected <T extends Fragment & CFragmentCore> void switchFragment(T fragment, @AnimRes int enter,
                                                                       @AnimRes int exit, @AnimRes int popEnter, @AnimRes int popExit) {
        getSupportFragmentManager()
                .beginTransaction().setCustomAnimations(enter, exit, popEnter, popExit)
                .replace(getFragmentId(), fragment, s(fragment.getTitleId())).commit();
    }

    /**
     * Gets current fragment and executes callback if type matches
     * @param clazz class of desired fragment
     * @param callback callback if match
     * @param <T> Type of class
     */
    @SuppressWarnings("unchecked")
    protected <T extends Fragment & CFragmentCore> void onCurrentFragment(Class<T> clazz, @NonNull Current<T> callback) {
        Fragment current = getSupportFragmentManager().findFragmentById(getFragmentId());
        if (clazz.isInstance(current.getClass())) {
            callback.onMatch((T) current);
        }
    }

    public interface Current<T extends Fragment & CFragmentCore> {
        void onMatch(T currentFragment);
    }
}
