package com.pitchedapps.capsule.library;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pitchedapps.capsule.library.activities.CapsuleActivity;
import com.pitchedapps.capsule.library.interfaces.ICapsulePermissions;

/**
 * Created by Allan Wang on 2016-08-19.
 *
 */
public abstract class CapsuleFragment extends Fragment {

    public abstract void onFabClick(View v);

    public abstract @StringRes int getTitleId();

    protected abstract
    @DrawableRes
    int getFabIcon();

    protected abstract boolean hasFab();

    protected void showFab() {
        capsuleActivity().getFab().show();
    }

    protected void hideFab() {
        capsuleActivity().getFab().hide();
    }

    private CapsuleActivity capsuleActivity() {
        if (!(getActivity() instanceof CapsuleActivity)) {
            throw new RuntimeException("Context is not an instance of CapsuleActivity");
        }
        return ((CapsuleActivity) getActivity());
    }

    protected void setFabIcon(@DrawableRes int icon) {
        capsuleActivity().getFab().setImageResource(icon);
    }

    protected void fabSnackbar(String text, int duration) {
        if (!hasFab()) {
            Log.d("Capsule", "fab not attached, stopping snackbar");
            return; //TODO log
        }
        Snackbar.make(capsuleActivity().getFab(), text, duration);
    }

    @CallSuper
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (hasFab()) {
            showFab();
            if (getFabIcon() != 0) {
                setFabIcon(getFabIcon());
            }
        } else {
            hideFab();
        }
        return null;
    }

    protected void getPermissions(@NonNull ICapsulePermissions callback, @IntRange(from = 1, to = Integer.MAX_VALUE) int requestCode, @NonNull String... permissions) {
        capsuleActivity().requestPermission(callback, requestCode, permissions);
    }

}
