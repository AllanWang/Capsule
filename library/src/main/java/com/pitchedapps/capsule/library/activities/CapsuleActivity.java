package com.pitchedapps.capsule.library.activities;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.CallSuper;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;


import com.pitchedapps.capsule.library.CapsuleFragment;
import com.pitchedapps.capsule.library.permissions.CPermissionCallback;
import com.pitchedapps.capsule.library.permissions.PermissionResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.jar.Manifest;

/**
 * Created by Allan Wang on 2016-08-19.
 */
public abstract class CapsuleActivity extends FrameActivity {

    private HashMap<Integer, CPermissionCallback> mCapsulePermissionCallbacks;

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

    public void requestPermission(@NonNull CPermissionCallback callback, @IntRange(from = 1, to = Integer.MAX_VALUE) int requestCode, @NonNull String... permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) { //Grant all permissions
            callback.onResult(new PermissionResult(permissions, PackageManager.PERMISSION_GRANTED));
            return;
        }
        List<String> missingPermissions = new ArrayList<>();
        for (String s : permissions) {
            if (ActivityCompat.checkSelfPermission(this, s) == PackageManager.PERMISSION_DENIED) {
                missingPermissions.add(s);
            }
        }
        if (mCapsulePermissionCallbacks == null) mCapsulePermissionCallbacks = new HashMap<>();
        mCapsulePermissionCallbacks.put(requestCode, callback);
        ActivityCompat.requestPermissions(this, missingPermissions.toArray(new String[missingPermissions.size()]), requestCode);
    }

    @CallSuper
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!mCapsulePermissionCallbacks.containsKey(requestCode)) return;
        mCapsulePermissionCallbacks.get(requestCode).onResult(new PermissionResult(permissions, grantResults));
        mCapsulePermissionCallbacks.remove(requestCode);
        if (mCapsulePermissionCallbacks.isEmpty()) mCapsulePermissionCallbacks = null;
    }


}
