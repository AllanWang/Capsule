package com.pitchedapps.capsule.library.activities;

import android.support.v4.app.FragmentTransaction;

import com.pitchedapps.capsule.library.fragments.CapsuleFragment;

/**
 * Created by Allan Wang on 2016-08-19.
 *
 * The activity that holds everything
 */
public abstract class CapsuleActivity extends PermissionActivity {

    protected void switchFragment(CapsuleFragment fragment) {
        switchFragmentCustom(fragment).commit();
    }

    protected FragmentTransaction switchFragmentCustom(CapsuleFragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(getFragmentId(), fragment, s(fragment.getTitleId()));
        return fragmentTransaction;
    }

}
