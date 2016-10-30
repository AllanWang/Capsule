package com.pitchedapps.capsule.library.fragments;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.IIcon;
import com.pitchedapps.capsule.library.R;
import com.pitchedapps.capsule.library.activities.CapsuleActivity;
import com.pitchedapps.capsule.library.interfaces.CActivityCore;
import com.pitchedapps.capsule.library.interfaces.CFragmentCore;
import com.pitchedapps.capsule.library.logging.CLog;
import com.pitchedapps.capsule.library.permissions.CPermissionCallback;
import com.pitchedapps.capsule.library.utils.ViewUtils;

/**
 * Created by Allan Wang on 2016-08-21.
 */
abstract class BaseFragment extends Fragment implements CFragmentCore {

    protected String s(@StringRes int id) {
        return getString(id);
    }

    protected static String s(@NonNull Context context, @StringRes int id) {
        return context.getString(id);
    }

    protected void showFab() {
        capsuleActivity().getFab().show();
    }

    protected void hideFab() {
        capsuleActivity().getFab().hide();
    }

    protected CapsuleActivity capsuleActivity() {
        if (!(getActivity() instanceof CapsuleActivity)) {
            throw new RuntimeException(s(R.string.capsule_activity_context_error));
        }
        return ((CapsuleActivity) getActivity());
    }

    protected void setFabIcon(IIcon iicon) {
        capsuleActivity().getFab().setImageDrawable(ViewUtils.iconDrawable(getContext(), iicon));
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

    protected void getPermissions(@NonNull CPermissionCallback callback, @IntRange(from = 1, to = Integer.MAX_VALUE) int requestCode, @NonNull String... permissions) {
        capsuleActivity().requestPermission(callback, requestCode, permissions);
    }
}
