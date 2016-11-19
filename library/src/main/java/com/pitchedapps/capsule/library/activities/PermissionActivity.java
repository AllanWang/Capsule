package com.pitchedapps.capsule.library.activities;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.CallSuper;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.util.SparseArrayCompat;
import android.util.SparseArray;

import com.pitchedapps.capsule.library.logging.CLog;
import com.pitchedapps.capsule.library.permissions.CPermissionCallback;
import com.pitchedapps.capsule.library.permissions.PermissionResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Allan Wang on 2016-08-21.
 *
 * Handles permissions
 */
abstract class PermissionActivity extends BaseActivity {

    private SparseArrayCompat<PermissionHolder> cPermissionMap = new SparseArrayCompat<>();;

    private class PermissionHolder {

        private final String[] mAlreadyAccepted;
        private final CPermissionCallback mCallback;

        private PermissionHolder(List<String> accepted, CPermissionCallback callback) {
            mAlreadyAccepted = accepted.toArray(new String[accepted.size()]);
            mCallback = callback;
        }
    }

    /**
     * Request permissions; returns all granted automatically if SDK Version is below M
     * @param callback called once request is finished
     * @param requestCode unique code to keep track of which callback to use
     * @param permissions one or many permission names to check for
     */
    @Override
    public void requestPermission(@NonNull CPermissionCallback callback, @IntRange(from = 1, to = Integer.MAX_VALUE) int requestCode, @NonNull String... permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) { //Grant all permissions
            callback.onResult(new PermissionResult(permissions, PackageManager.PERMISSION_GRANTED));
            return;
        }
        List<String> missingPermissions = new ArrayList<>();
        List<String> acceptedPermissions = new ArrayList<>();
        for (String s : permissions) {
            if (ActivityCompat.checkSelfPermission(this, s) == PackageManager.PERMISSION_GRANTED) {
                acceptedPermissions.add(s);
            } else {
                missingPermissions.add((s));
            }
        }
        if (missingPermissions.isEmpty()) {
            callback.onResult(new PermissionResult(permissions, PackageManager.PERMISSION_GRANTED));
            return;
        }
        cPermissionMap.put(requestCode, new PermissionHolder(acceptedPermissions, callback));
        ActivityCompat.requestPermissions(this, missingPermissions.toArray(new String[missingPermissions.size()]), requestCode);
    }

    @CallSuper
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (cPermissionMap.get(requestCode) == null) return;
        CLog.d("Permission request finished for #%d", requestCode);
        cPermissionMap.get(requestCode).mCallback.onResult(new PermissionResult(permissions, grantResults, cPermissionMap.get(requestCode).mAlreadyAccepted));
        cPermissionMap.remove(requestCode);
    }


}
