package com.pitchedapps.capsule.library.fragments;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.iconics.IconicsDrawable;
import com.pitchedapps.capsule.library.interfaces.CFab;

/**
 * Created by Allan Wang on 2016-08-19.
 */
public abstract class CapsuleFragment extends BaseFragment implements CFab {

    @CallSuper
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (hasFab() != null) {
            if (hasFab()) {
                showFab();
                if (getFabIcon() != null) {
                    setFabIcon(getFabIcon());
                }
            } else {
                hideFab();
            }
        }
        return null;
    }

}
