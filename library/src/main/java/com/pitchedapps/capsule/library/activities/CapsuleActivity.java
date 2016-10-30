package com.pitchedapps.capsule.library.activities;

import android.support.annotation.AnimRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.pitchedapps.capsule.library.fragments.CapsuleFragment;
import com.pitchedapps.capsule.library.interfaces.CFragmentCore;

/**
 * Created by Allan Wang on 2016-08-19.
 * <p>
 * The activity that holds everything
 */
public abstract class CapsuleActivity extends ViewActivity {

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

}
