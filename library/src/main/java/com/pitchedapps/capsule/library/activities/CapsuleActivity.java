package com.pitchedapps.capsule.library.activities;

import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;

import com.pitchedapps.capsule.library.interfaces.CFragmentCore;

/**
 * Created by Allan Wang on 2016-08-19.
 * <p>
 * The activity that holds everything, with a few extra helper methods
 */
public abstract class CapsuleActivity extends EventActivity {

    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        capsuleOnCreate(savedInstanceState);
    }

    protected void switchFragment(Fragment fragment) {
        String tag = null;
        if (fragment instanceof CFragmentCore && ((CFragmentCore) fragment).getTitleId() > 0) {
            tag = s(((CFragmentCore) fragment).getTitleId());
        }
        getSupportFragmentManager()
                .beginTransaction().replace(getFragmentId(), fragment, tag).commit();
    }

    protected void switchFragment(Fragment fragment, @AnimRes int enter,
                                  @AnimRes int exit) {
        String tag = null;
        if (fragment instanceof CFragmentCore && ((CFragmentCore) fragment).getTitleId() > 0) {
            tag = s(((CFragmentCore) fragment).getTitleId());
        }
        getSupportFragmentManager()
                .beginTransaction().setCustomAnimations(enter, exit)
                .replace(getFragmentId(), fragment, tag).commit();
    }

    protected void switchFragment(Fragment fragment, @AnimRes int enter,
                                  @AnimRes int exit, @AnimRes int popEnter, @AnimRes int popExit) {

        String tag = null;
        if (fragment instanceof CFragmentCore && ((CFragmentCore) fragment).getTitleId() > 0) {
            tag = s(((CFragmentCore) fragment).getTitleId());
        }
        getSupportFragmentManager()
                .beginTransaction().setCustomAnimations(enter, exit, popEnter, popExit)
                .replace(getFragmentId(), fragment, tag).commit();
    }

}
