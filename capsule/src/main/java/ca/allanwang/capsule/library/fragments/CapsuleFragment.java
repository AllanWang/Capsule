package ca.allanwang.capsule.library.fragments;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mikepenz.iconics.typeface.IIcon;

import ca.allanwang.capsule.library.R;
import ca.allanwang.capsule.library.activities.CapsuleActivity;
import ca.allanwang.capsule.library.event.CFabEvent;
import ca.allanwang.capsule.library.event.SnackbarEvent;
import ca.allanwang.capsule.library.permissions.CPermissionCallback;
import ca.allanwang.capsule.library.utils.EventUtils;

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

    public void postEvent(Object event) {
        EventUtils.post(event);
    }

    public CapsuleActivity capsuleActivity() {
        if (!(getActivity() instanceof CapsuleActivity))
            throw new RuntimeException(s(R.string.capsule_activity_context_error));
        return ((CapsuleActivity) getActivity());
    }

    public void setFabIcon(IIcon iicon) {
        postEvent(new CFabEvent(iicon, null));
    }

    public void showFab() {
        postEvent(new CFabEvent(true));
    }

    public void hideFab() {
        postEvent(new CFabEvent(false));
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
    public void snackbar(SnackbarEvent event) {
        postEvent(event);
    }

}
