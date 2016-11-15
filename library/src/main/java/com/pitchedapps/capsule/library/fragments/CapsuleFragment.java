package com.pitchedapps.capsule.library.fragments;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mikepenz.iconics.typeface.IIcon;
import com.pitchedapps.capsule.library.R;
import com.pitchedapps.capsule.library.activities.CapsuleActivity;
import com.pitchedapps.capsule.library.event.CFabEvent;
import com.pitchedapps.capsule.library.event.SnackbarEvent;
import com.pitchedapps.capsule.library.logging.CLog;
import com.pitchedapps.capsule.library.permissions.CPermissionCallback;
import com.pitchedapps.capsule.library.utils.ViewUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Allan Wang on 2016-08-19.
 */
public abstract class CapsuleFragment extends BaseFragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        postEvent(updateFabShell());
    }

    /*
     * The following methods require a CapsuleActivity context
     */

    protected void postEvent(Object event) {
        if (event == null) return;
        CLog.e("New event");
        EventBus.getDefault().post(event);
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

    protected void showFab() {
        capsuleActivity().getFab().show();
    }

    protected void hideFab() {
        capsuleActivity().getFab().hide();
    }

    //To allow for override
    protected CFabEvent updateFabShell() {
        return updateFab();
    }

    protected abstract
    @Nullable
    CFabEvent updateFab();

    protected void getPermissions(@NonNull CPermissionCallback callback, @IntRange(from = 1, to = Integer.MAX_VALUE) int requestCode, @NonNull String... permissions) {
        capsuleActivity().requestPermission(callback, requestCode, permissions);
    }

    //EventBus method
    protected void snackbar(SnackbarEvent event) {
        postEvent(event);
    }

}
