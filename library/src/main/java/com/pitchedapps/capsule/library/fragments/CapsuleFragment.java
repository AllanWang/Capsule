package com.pitchedapps.capsule.library.fragments;

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

import com.pitchedapps.capsule.library.R;
import com.pitchedapps.capsule.library.activities.CapsuleActivity;
import com.pitchedapps.capsule.library.interfaces.CFragmentCore;
import com.pitchedapps.capsule.library.logging.CLog;
import com.pitchedapps.capsule.library.permissions.CPermissionCallback;

import timber.log.Timber;

/**
 * Created by Allan Wang on 2016-08-19.
 */
public abstract class CapsuleFragment extends BaseFragment implements CFragmentCore{

    @Override
    public void showFab() {
        capsuleActivity().getFab().show();
    }

    @Override
    public void hideFab() {
        capsuleActivity().getFab().hide();
    }

    private CapsuleActivity capsuleActivity() {
        if (!(getActivity() instanceof CapsuleActivity)) {
            throw new RuntimeException(s(R.string.capsule_activity_context_error));
        }
        return ((CapsuleActivity) getActivity());
    }

    protected void setFabIcon(@DrawableRes int icon) {
        capsuleActivity().getFab().setImageResource(icon);
    }

    protected void snackbar(String text) {
        snackbar(text, Snackbar.LENGTH_LONG);
    }

    protected void snackbar(String text, int duration) {
        CLog.d("Making snackbar");
        Snackbar.make(capsuleActivity().getFab(), text, duration).show();
    }

    protected Snackbar snackbarCustom(String text, int duration) {
        CLog.d("Making custom snackbar, make sure you use .show()");
        return Snackbar.make(capsuleActivity().getFab(), text, duration);
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

    protected void getPermissions(@NonNull CPermissionCallback callback, @IntRange(from = 1, to = Integer.MAX_VALUE) int requestCode, @NonNull String... permissions) {
        capsuleActivity().requestPermission(callback, requestCode, permissions);
    }

}
