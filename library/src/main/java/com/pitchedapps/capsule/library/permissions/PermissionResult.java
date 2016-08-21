package com.pitchedapps.capsule.library.permissions;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allan Wang on 2016-08-21.
 */
public class PermissionResult {

    private final CPR[] mResults;

    public PermissionResult(@NonNull String[] permissions, int grantResult) {
        mResults = new CPR[permissions.length];
        for (int i = 0; i < permissions.length; i++)
            mResults[i] = new CPR(permissions[i], grantResult);
    }

    public PermissionResult(@NonNull String[] permissions, @NonNull int[] grantResults) {
        mResults = new CPR[permissions.length];
        for (int i = 0; i < permissions.length; i++)
            mResults[i] = new CPR(permissions[i], grantResults[i]);
    }

    public String[] getPermissions() {
        String[] perms = new String[mResults.length];
        for (int i = 0; i < perms.length; i++)
            perms[i] = mResults[i].getPermission();
        return perms;
    }

    public String[] getGrantedPermissions() {
        return getByGrant(true);
    }

    public String[] getDeniedPermissions() {
        return getByGrant(false);
    }

    public boolean isAllGranted() {
        for (CPR c : mResults) {
            if (!c.isGranted()) return false;
        }
        return true;
    }

    private String[] getByGrant(final boolean b) {
        List<String> result = new ArrayList<>();
        for (CPR c : mResults) {
            if (c.isGranted() == b) result.add(c.getPermission());
        }
        return result.toArray(new String[result.size()]);
    }

    private class CPR {

        private String mPermission;
        private boolean mGranted;

        private CPR(@NonNull String permission, int granted) {
            mPermission = permission;
            mGranted = granted == PackageManager.PERMISSION_GRANTED;
        }

        @NonNull
        private String getPermission() {
            return mPermission;
        }

        private boolean isGranted() {
            return mGranted;
        }
    }

}
