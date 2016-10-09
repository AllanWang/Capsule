package com.pitchedapps.capsule.library.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.pitchedapps.capsule.library.fragments.CapsuleFragment;
import com.pitchedapps.capsule.library.interfaces.CFragmentCore;

/**
 * Created by Allan Wang on 2016-08-19.
 * <p>
 * The activity that holds everything
 */
public abstract class CapsuleActivity extends PermissionActivity {

    protected <T extends Fragment & CFragmentCore> void switchFragment(T fragment) {
        switchFragmentCustom(fragment).commit();
    }

    protected <T extends Fragment & CFragmentCore> FragmentTransaction switchFragmentCustom(T fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(getFragmentId(), fragment, s(fragment.getTitleId()));
        return fragmentTransaction;
    }

}
