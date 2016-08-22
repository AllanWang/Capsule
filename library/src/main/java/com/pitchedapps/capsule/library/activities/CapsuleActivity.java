package com.pitchedapps.capsule.library.activities;

import android.support.annotation.StringRes;
import android.support.v4.app.FragmentTransaction;

import com.pitchedapps.capsule.library.CapsuleFragment;

/**
 * Created by Allan Wang on 2016-08-19.
 */
public abstract class CapsuleActivity extends PermissionActivity {

    protected String s(@StringRes int id) {
        return getString(id);
    }

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
