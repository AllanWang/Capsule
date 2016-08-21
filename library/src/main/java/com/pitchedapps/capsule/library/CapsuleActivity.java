package com.pitchedapps.capsule.library;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.IntRange;
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


import com.pitchedapps.capsule.library.interfaces.ICapsulePermissions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Allan Wang on 2016-08-19.
 *
 */
public abstract class CapsuleActivity extends AppCompatActivity {

    protected FloatingActionButton mFab;
    protected Toolbar mToolbar;

    private HashMap<Integer, ICapsulePermissions> mCapsulePermissionCallbacks;

    public FloatingActionButton getFab() {
        if (mFab == null)
            throw new RuntimeException("Fab not set in CapsuleActivity; use setupFab method");
        return mFab;
    }

    /**
     * Gets the Layout ID of the view that will be replaced by Fragments with the SupportFragmentManager
     *
     * @return
     */
    protected abstract
    @IdRes
    int getFragmentId();

    private CapsuleFragment getCurrentBaseFragment() {
        Fragment current = getSupportFragmentManager().findFragmentById(getFragmentId());
        if (!(current instanceof CapsuleFragment))
            throw new RuntimeException("Fragment does not extend CapsuleFragment");
        return (CapsuleFragment) current;
    }

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

    protected void capsulateFab(@IdRes int id) {
        mFab = (FloatingActionButton) findViewById(id);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCurrentBaseFragment().onFabClick(view);
            }
        });
    }

    protected void capsulateToolbar(@IdRes int id) {
        mToolbar = (Toolbar) findViewById(id);
        setSupportActionBar(mToolbar);
    }

    public static void hideFab(Context context) {
        if (context instanceof CapsuleActivity) {
            ((CapsuleActivity) context).getFab().hide();
        } else {
            Log.e("hideFab", "context not instance of CapsuleActivity");
        }
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
