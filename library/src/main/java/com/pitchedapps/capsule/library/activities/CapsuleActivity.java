package com.pitchedapps.capsule.library.activities;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;


import com.pitchedapps.capsule.library.CapsuleFragment;
import com.pitchedapps.capsule.library.interfaces.ICapsulePermissions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Allan Wang on 2016-08-19.
 *
 */
public abstract class CapsuleActivity extends FrameActivity {

    private HashMap<Integer, ICapsulePermissions> mCapsulePermissionCallbacks;

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

    public void requestPermission(@NonNull ICapsulePermissions callback, @IntRange(from = 1, to = Integer.MAX_VALUE) int requestCode, @NonNull String... permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            callback.onSuccess();
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResult) {
        if (!mCapsulePermissionCallbacks.containsKey(requestCode)) return;
        for (int i : grantResult) {
            if (i == PackageManager.PERMISSION_DENIED) {
                mCapsulePermissionCallbacks.get(requestCode).onDenied();
                return;
            }
        }
        mCapsulePermissionCallbacks.get(requestCode).onSuccess();
        mCapsulePermissionCallbacks.remove(requestCode);
        if (mCapsulePermissionCallbacks.isEmpty()) mCapsulePermissionCallbacks = null;
    }


}
