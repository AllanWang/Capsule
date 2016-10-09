package com.pitchedapps.capsule.library.interfaces;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.pitchedapps.capsule.library.permissions.CPermissionCallback;

/**
 * Created by Allan Wang on 2016-10-09.
 * <p>
 * Contains list of methods called from CapsuleActivity
 */

public interface CActivityCore {

    /**
     * Modified permission request method
     * @param callback callback for fragment
     * @param requestCode unique request code
     * @param permissions array of permissions
     */
    void requestPermission(@NonNull CPermissionCallback callback, @IntRange(from = 1, to = Integer.MAX_VALUE) int requestCode, @NonNull String... permissions);

    FloatingActionButton getFab();
}
